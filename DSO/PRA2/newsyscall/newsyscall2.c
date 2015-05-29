/*
 * New system call
 */

#include <asm/unistd.h>
#include <linux/module.h>
#include <linux/syscalls.h>
#include <linux/thread_info.h>
#include <asm/asm-offsets.h>   /* NR_syscalls */

//#include <linux/ext2.h>

MODULE_LICENSE ("GPL");
MODULE_DESCRIPTION ("New system call");
MODULE_AUTHOR ("Enric Morancho");

unsigned int free_ident = -1;

extern unsigned sys_call_table[];
extern struct inode *ext2_iget (struct super_block *sb, unsigned long ino);

struct inode *
inode_get (int num_inode)
{
  struct super_block *sb;
  struct inode *inode;

  list_for_each_entry (sb, &super_blocks, s_list)
  {
//    if (list_empty (&sb->s_instances))
//      continue;
    if (strcmp ("ext2", sb->s_type->name) == 0)
      break;
  }
  inode = ext2_iget (sb, num_inode);

  if (IS_ERR (inode))
    return (NULL);
  else
    return (inode);
}

asmlinkage long
sys_newsyscall (int parameter)
{
  // Begin adpozuelo's modifications
  struct inode *inode; // pointer to file inode 
  inode = inode_get (parameter); // call to inode_get function
  
  if (inode == NULL) { // if pointer is equal to NULL (inode not found)
    return -ENOENT; // return -ENOENT
  } else { // else (inode found)
    return inode->i_mode;  // return inode's i_mode field
  }
  // End adpozuelo's modifications
}

static int __init
newsyscall_init (void)
{
  unsigned int i;

  /* Look for an unused sys_call_table entry */
  for (i = 0; i < NR_syscalls; i++)
    if (sys_call_table[i] == (unsigned) sys_ni_syscall)
      break;

  /* Found? */
  if (i == NR_syscalls)
    {
      printk ("No free entry available");
      return (-1);
    }

  /* Use this sys_call_entry */
  free_ident = i;
  sys_call_table[free_ident] = (unsigned) sys_newsyscall;

  printk (KERN_INFO "New syscall installed. Identifier = %d\n", free_ident);
  return (0);
}

static void __exit
newsyscall_exit (void)
{
  /* Restore previous state */
  if (free_ident != -1)
    sys_call_table[free_ident] = (unsigned) sys_ni_syscall;

  printk (KERN_INFO "New syscall removed\n");
}

module_init (newsyscall_init);
module_exit (newsyscall_exit);
