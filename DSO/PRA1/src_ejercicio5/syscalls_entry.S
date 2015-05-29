#include <linux/linkage.h>
#include <asm/segment.h>

#define SAVE_ALL \
	cld;\
	pushl	%es;\
	pushl	%ds;\
	pushl	%eax;\
	pushl %ebp;\
	pushl	%edi;\
	pushl %esi;\
	pushl %edx;\
	pushl %ecx;\
	pushl %ebx;\
	movl 	$__KERNEL_DS,%esi;\
	movl	%esi,%ds;\
	movl	%esi,%es;\
	movl	%ebx,%ecx;
	# Last 4 lines adpozuelo modification. 
	# Change edx (2nd parameter) to esi to avoid rewrite of usefull data.
	# Move ebx (file descriptor) to ecx (3rd parameter).


#define RESTORE_ALL \
	popl	%ebx;\
	popl	%ecx;\
	popl	%edx;\
	popl	%esi;\
	popl	%edi;\
	popl	%ebp;\
	popl	%eax;\
	popl	%ds;\
	popl	%es;


ENTRY(new_syscall_handler)
	# Saves registers
	SAVE_ALL

	# Since version 2.6.20, Linux is compiled with -mregparm=3 parameter,
	# which means that as long as a function uses 3 or less arguments, 
	# GCC will automatically use registers to pass its parameters.
	# eax=1st parameter, edx=2nd parameter, ecx=3rd parameter
	# http://kernelnewbies.org/Linux_2_6_20

	# In this case, as eax already contains the syscall identifier
	# (the parameter for do_added_syscall_handler), we can call the
	# function directly. We do not need to push the parameter onto the stack.

	call	do_added_syscall_handler

	# Restores registers
	RESTORE_ALL

	# Pushes address of original handler into the stack
	pushl original_syscall_handler

	# "ret" transfer control to the original handler
	# The original handler will execute "iret" to end interruption
	ret
