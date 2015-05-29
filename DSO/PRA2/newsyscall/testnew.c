#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <assert.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <asm-generic/types.h> /* umode_t */

#define __NR_newsyscall 17
#define NUM_TESTS   20

char *filenames[] = { "/", "/bin", ".", "/bin/ls", "/etc/passwd", "/home" };

int
main (int argc, char *argv[])
{
  unsigned nfiles = sizeof (filenames) / sizeof (char *);
  unsigned nfile_idx;
  unsigned i;
  struct stat buf;
  int r;

  /* Init random generator */
  srand (getpid ());

  for (i = 0; i < NUM_TESTS; i++)
    {
      /* Picks a filename index */
      nfile_idx = rand () % nfiles;

      /* Obtains its inode */
      assert (stat (filenames[nfile_idx], &buf) != -1);

      printf ("Testing %20s (inode %6ld) ", filenames[nfile_idx], buf.st_ino);

      /* Calling our systemcall */
      r = syscall (__NR_newsyscall, buf.st_ino);
      assert (r != -1);

      printf("%o %o\n", r, buf.st_mode);

      /* Checking result */
      assert (r == buf.st_mode);
    }

  /* Testing an unused inode */
  r = syscall (__NR_newsyscall, 0);
  assert (r == -1);
  assert (errno == ENOENT);

  /* If program reaches this point, all tests passed OK */
  printf ("All tests seem to be OK!!!\n");

  return (0);
}
