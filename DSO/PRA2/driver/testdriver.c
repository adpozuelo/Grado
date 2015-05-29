#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <errno.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/stat.h>

#define DEVICE_NAME "/dev/inodes"

#define NUM_TESTS   20

char *filenames[] = { "/", "/bin", ".", "/bin/ls", "/etc/passwd", "/home" };

int
main (int argc, char *argv[])
{
  unsigned nfiles = sizeof (filenames) / sizeof (char *);
  unsigned nfile_idx;
  unsigned i, r;
  unsigned short tmp;
  int fd, fd2;
  struct stat buf;


  /* Init random generator */
  srand (getpid ());

  /* Open device file O_WRONLY */  /*OK*/
  fd = open (DEVICE_NAME, O_WRONLY);
  assert (fd == -1);
  assert (errno == EACCES);
  
  /* Open device file O_RDONLY */   /*OK*/
   fd = open (DEVICE_NAME, O_WRONLY);
   assert (fd == -1);
   assert (errno == EACCES);

  /* Open device file */
   fd = open (DEVICE_NAME, O_RDWR);
   assert (fd > 0);

  /* Try to open again the device file */
   fd2 = open (DEVICE_NAME, O_RDWR);
   assert (fd2 == -1);
   assert (errno == EBUSY);

  /* Testing third parameter of read system call */
   r = read(fd, &tmp, 2);
   assert (r==-1);
   assert (errno == EINVAL);

  /* Reading several files */
   for (i = 0; i < NUM_TESTS; i++)
     {
      /* Picks a filename index */
       nfile_idx = rand () % nfiles;

      /* Obtains its inode */
       assert (stat (filenames[nfile_idx], &buf) != -1);

       printf ("Testing %20s (inode %6ld) ", filenames[nfile_idx], buf.st_ino);

      /* Move pointer to our file */
       r = lseek(fd, buf.st_ino, SEEK_SET);
       assert (r==buf.st_ino);

	/* Read data */
       r = read(fd, &tmp, 1);
       assert (r==1);

       printf("%o %o\n", tmp, buf.st_mode);

       assert (tmp == buf.st_mode);
     }

  /* Modify protections */
    {
     char tmpfile[80];
     int h;
     unsigned short new_protections;

     sprintf(tmpfile, "/tmp/test_driver.%d", getpid());
     printf("Testing write syscall on file %s ... ", tmpfile);

    /* Create tmp file */
     h = open(tmpfile, O_RDWR|O_TRUNC|O_CREAT, 0600);
     assert (h>=0);
     close(h);

    /* Get inode number */
     assert (stat (tmpfile, &buf) != -1);

    /* Move pointer to tmp file */
     r = lseek(fd, buf.st_ino, SEEK_SET);
     assert (r==buf.st_ino);

    /* Modify protections */ 
     new_protections = rand() % 0777; 
     r = write(fd, &new_protections, 1);
     assert( r == 1);
      
    /* Get data */
     assert (stat (tmpfile, &buf) != -1);

     printf("%o %o\n", new_protections, buf.st_mode);
     assert (new_protections == buf.st_mode);

    /* Check pointer update */
     r = lseek(fd, 0, SEEK_CUR);
     assert (r==(buf.st_ino+1));

    /* Check parameters */
     r = write(fd, &new_protections, 2);
     assert (r==-1);
     assert (errno == EINVAL);
   }
    
  /* Closing file descriptor */
  assert(close(fd) == 0);
  
  

  /* If program reaches this point, all tests passed OK */
  printf ("All tests seem to be OK!!!\n");

  return (0);
}
