/*
 * adpozuelo's inodes driver
 *
 */

#include <asm/unistd.h>
#include <asm/uaccess.h>
#include <linux/module.h>
#include <linux/proc_fs.h>
#include <linux/sched.h>

#define DRIVER_MAJOR 231
#define DRIVER_NAME "inodes"

MODULE_LICENSE ("GPL");
MODULE_DESCRIPTION ("Inodes driver");
MODULE_AUTHOR ("Antonio Diaz Pozuelo");

static int d_open = 0; // Open file control variable (0 = file not opened <--> 1 = file opened)

// college course inode_get function begin
extern struct inode *ext2_iget (struct super_block *sb, unsigned long ino);
struct inode *
inode_get (int num_inode)
{
  struct super_block *sb;
  struct inode *inode;

  list_for_each_entry (sb, &super_blocks, s_list)
  {
    if (strcmp ("ext2", sb->s_type->name) == 0)
      break;
  }
  inode = ext2_iget (sb, num_inode);

  if (IS_ERR (inode))
    return (NULL);
  else
    return (inode);
}
// college course inode_get function end

int
do_open (struct inode *inode, struct file *filp)
{
  if (filp->f_flags != O_RDWR) // if opening file is not in O_RDWR mode
    return -EACCES; // return error
  
  if (d_open) // if file is open yet
    return -EBUSY; // return error
    
  d_open++; // file control variable update
  return 0; // return success
}

ssize_t
do_read (struct file * filp, char *buf, size_t count, loff_t * f_pos)
{
  if (count != 1) // if we have more/less than 1 inode
    return -EINVAL; // return error
  
  struct inode *inode; // pointer to file inode 
  inode = inode_get (*f_pos); // call to inode_get function
  
  if (inode == NULL) { // if pointer is equal to NULL (inode not found)
    return -ENOENT; // return error
  }
  
  long l_inode = inode->i_mode; // get inode's access permissions
  // copy inode's access permissions from kernel space to user space
  int k = copy_to_user (buf, &l_inode , sizeof(l_inode));
  
  if (k == 0) { // if copy operation successful
    (*f_pos)++; // update R/W pointer's position
    return 1; // return success
  } else { // else, operation copy operation fails
    return -1; // return error
  }
}

ssize_t
do_write (struct file * filp, const char *buf, size_t count, loff_t * f_pos)
{
  if (count != 1) // if we have more/less than 1 inode
    return -EINVAL; // return error
   
  struct inode *inode; // pointer to file inode 
  inode = inode_get (*f_pos); // call to inode_get function
  
  if (inode == NULL) { // if pointer is equal to NULL (inode not found)
    return -ENOENT; // return error
  } 
  
  long l_inode; // buffer in kernel space to store inode's access permissions
  // copy inode's access permissions from user space to kernel space
  int k = copy_from_user(&l_inode, buf, sizeof(l_inode));
  
  if (k == 0) { // if copy operation successful
    inode->i_mode = l_inode; // asign inode's access permissions from buffer
    (*f_pos)++; // update R/W pointer's position
    return 1; // return success
  } else { // else, operation copy operation fails
    return -1; // return error
  }  
}

int
do_release (struct inode *inode, struct file *filp)
{
  d_open--; // file control variable update
  return 0; // return success
}

// Only disable SEEK_END from college course function, no more changes.
static loff_t
do_llseek (struct file *file, loff_t offset, int orig)
{
  loff_t ret;
  switch (orig)
    {
    case SEEK_SET:
      ret = offset;
      break;
    case SEEK_CUR:
      ret = file->f_pos + offset;
      break;
    case SEEK_END: // disable SEEK_END
    default:
      ret = -EINVAL;
    }

  if (ret >= 0)
    file->f_pos = ret;
  else
    ret = -EINVAL;

  return ret;
}

// Delete unlocked_ioctl from college course struct and maked syntactical changes, no more adjustments. 
struct file_operations inodes_op = { // rename abc_op to inodes_op (syntactical change) 
open:do_open,
read:do_read,
write:do_write,
release:do_release,
llseek:do_llseek
};

static int __init
driver_init (void) // rename abc_init to driver_init (syntactical change) 
{
  int result;

  result = register_chrdev (DRIVER_MAJOR, DRIVER_NAME, &inodes_op); // rename abc_op to inodes_op (syntactical change) 
  if (result < 0)
    {
      printk ("Unable to register device");
      return result;
    }

  printk (KERN_INFO "Correctly installed\n Compiled at %s %s\n", __DATE__,
          __TIME__);
  return (0);
}

static void __exit
driver_cleanup (void) // rename abc_cleanup to driver_cleanup (syntactical change) 
{
  unregister_chrdev (DRIVER_MAJOR, DRIVER_NAME);

  printk (KERN_INFO "Cleanup successful\n");
}

module_init (driver_init); // rename abc_init to driver_init (syntactical change)
module_exit (driver_cleanup); // rename abc_cleanup to driver_cleanup (syntactical change)
