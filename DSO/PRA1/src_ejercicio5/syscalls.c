/*
 * System Call Catching Module
 */

#include <asm/unistd.h>
#include <asm/asm-offsets.h>    /* NR_syscalls */
#include <asm/uaccess.h>
#include <linux/module.h>
#include <linux/proc_fs.h>
#include <linux/sched.h>
#include <linux/seq_file.h>

#include "init_names.h"

MODULE_LICENSE ("GPL");
MODULE_DESCRIPTION ("System Call Catching");

/* /proc interface to the module */
#define PROC_NAME "syscalls"    /* File name */

// start adpozuelo modification.
#define WRITE_SYSCALL_ID 4	/* Write syscall ID */
#define N_CHANNELS 10		/* Number of channels */
unsigned calls_channels[N_CHANNELS]; /* Table to count calls to channels */
size_t nbytes_channels[N_CHANNELS]; /* Table to count nbytes per channel */
// end adpozuelo modification.

pid_t traced_pid = 0;           /* Process to be traced ( 0 means all processes) */
unsigned calls[NR_syscalls];    /* Table for accounting syscalls */

/* Code added to the syscall handler */
void
do_added_syscall_handler (unsigned syscall_id, size_t nbytes, int fd) // adpozuelo modification
{
  pid_t pid;

  if (syscall_id > NR_syscalls)
    printk (KERN_INFO "Invalid syscall number %d\n", syscall_id);

  /* Trick to obtain pid of current process... thread_info struct is in the bottom of kernel stack */
  pid =
    ((struct thread_info *) ((unsigned long) (&syscall_id) &
                             0xffffe000))->task->pid;
  
// start adpozuelo modification
  if ((!traced_pid || (pid == traced_pid)) && syscall_id == WRITE_SYSCALL_ID) { // new condition only write write sys_call
    calls[syscall_id]++; 	// total write sys_call
    calls_channels[fd]++; 	// calls for channels
    nbytes_channels[fd]+=nbytes;	// bytes for channels
  }
  // end adpozuelo modification
}

/* Proctest_read serves "read" operations over /proc file using "seq_printf" function */
static int
proctest_read (struct seq_file *m, void *p)
{
  //unsigned total = 0, i = 0; 
  int i = 0;

  if (traced_pid)
    seq_printf (m, "Traced pid %u\n", traced_pid);

  // start adpozuelo modification
  for (; i < N_CHANNELS; i++) // iterate throght channels and nbytes_channels tables
	seq_printf (m, "Channel %2d, calls %4u, nbytes %6zu\n", i, calls_channels[i],nbytes_channels[i]);	// print values into tables
  //seq_printf (m, "%4u %s\n", calls[WRITE_SYSCALL_ID], syscall_names[WRITE_SYSCALL_ID]);  // print total calls to write sys_call
  // end adpozuelo modification
  
  // Commented by adpozuelo
/*  for (i = 0; i < NR_syscalls; i++)
    {
      if (calls[i])
        seq_printf (m, "%4u %s\n", calls[i], syscall_names[i]);
      total += calls[i];
    }
  seq_printf (m, "              Total %4u\n", total);
*/
  // Commented by adpozuelo
  
  return 0;
}

/* Write: gets the pid written into the /proc file. 
The module tracks just the syscalls made by this process */
ssize_t
proctest_write (struct file * f, const char __user * buff, size_t len,
                loff_t * o)
{
#define MAX_LEN 9
  char c[MAX_LEN + 1];

  if (len > MAX_LEN)
    return -EINVAL;

  if (copy_from_user (c, buff, len))
    return -EFAULT;

  c[len] = 0;
  if (!sscanf (c, "%d\n", &traced_pid))
    return -EINVAL;

  printk (KERN_INFO "Traced pid %u\n", traced_pid);
  memset (calls, 0, sizeof (calls));
  
  // start adpozuelo modification
  memset (calls_channels, 0, sizeof (calls_channels));
  memset (nbytes_channels, 0, sizeof (nbytes_channels));
  // end adpozuelo modification
  
  return len;
}

static int
proctest_open (struct inode *inode, struct file *file)
{
  return single_open (file, proctest_read, NULL);
}

static struct file_operations proc_operations = {
  .owner = THIS_MODULE,
  .open = proctest_open,
  .read = seq_read,
  .llseek = seq_lseek,
  .write = proctest_write,
  .release = single_release
};

/* Register /proc entry for our module */
static int
register_proc (void)
{
  struct proc_dir_entry *new_entry;

  new_entry = create_proc_entry (PROC_NAME, S_IFREG, NULL);
  if (!new_entry)
    return -1;

  /* Defines read/write functions for this proc entry */
  new_entry->proc_fops = &proc_operations;
  return 0;
}

/* Unregister /proc entry for our module */
static void
unregister_proc (void)
{
  remove_proc_entry (PROC_NAME, NULL);
}

static void syscall_cleanup (void);
static void capture_syscall_handler (void);
static void restore_syscall_handler (void);

static int __init
syscall_init (void)
{
  capture_syscall_handler ();
  if (register_proc () < 0)
    {
      syscall_cleanup ();
      return (-1);
    }
  /* Initializes data structures */
  init_syscall_names ();
  traced_pid = 0;
  memset (calls, 0, sizeof (calls));
  
  // start adpozuelo modification
  memset (calls_channels, 0, sizeof (calls_channels));
  memset (nbytes_channels, 0, sizeof (nbytes_channels));
  // end adpozuelo modification

  printk (KERN_INFO "Correctly installed\n Compiled at %s %s\n", __DATE__,
          __TIME__);
  return (0);
}

static void
syscall_cleanup (void)
{
  restore_syscall_handler ();
  unregister_proc ();
  printk (KERN_INFO "Cleanup successful\n");
}

#pragma pack(1)
struct desc_reg
{
  unsigned short limit;
  unsigned long base;
};

struct interrupt_gate
{
  unsigned short offset_low;
  unsigned short segment;
  unsigned char type;
  unsigned char dpl;
  unsigned short offset_high;
};
#pragma pack(4)

unsigned original_syscall_handler;      /* Original Syscall Handler */
extern void *new_syscall_handler (void);        /* Pointer to the entry point for the new syscall handler (defined in entry.S) */
static struct interrupt_gate *syscall_gate;     /* Pointer to the Syscall Interrupt Gate */

void
capture_syscall_handler (void)
{
  static struct desc_reg idtr;  /* IDT Register */

  /* Get the base address of the IDT (interrupt vector) */
  __asm__ __volatile__ ("sidt %0\n"::"m" (idtr));

  /* Gets a pointer to the original syscall interrupt gate (0x80) */
  syscall_gate =
    (struct interrupt_gate *) (idtr.base +
                               0x80 * sizeof (struct interrupt_gate));

  /* Retrieves the original syscall handler */
  original_syscall_handler =
    ((syscall_gate->offset_high << 16) | syscall_gate->offset_low);

  /* Disable interrupts */
  local_irq_disable ();
  /* Change the syscall handler */
  syscall_gate->offset_low = (unsigned) (new_syscall_handler) & 0xFFFF;
  syscall_gate->offset_high =
    ((unsigned) (new_syscall_handler) >> 16) & 0xFFFF;
  /* Enable interrupts */
  local_irq_enable ();
  printk (KERN_INFO "Old syscall handler at 0x%x\n",
          original_syscall_handler);
  printk (KERN_INFO "New syscall handler at 0x%p\n", new_syscall_handler);
}

void
restore_syscall_handler (void)
{
  /* Restore the syscall handler */
  local_irq_disable ();
  syscall_gate->offset_low = original_syscall_handler & 0xFFFF;
  syscall_gate->offset_high = (original_syscall_handler >> 16) & 0xFFFF;
  local_irq_enable ();
}

module_init (syscall_init);
module_exit (syscall_cleanup);
