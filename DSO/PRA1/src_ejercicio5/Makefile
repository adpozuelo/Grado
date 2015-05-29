ifneq ($(KERNELRELEASE),)

ksyscalls-objs := syscalls.o syscalls_entry.o
obj-m := ksyscalls.o 
 
else
	include ../Makefile
	KERNELDIR := ../../$(KERNEL_VERSION)
	PWD := $(shell pwd)

modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules
endif

clean:
	rm -rf *.[oas] .*.flags *.ko .*.cmd .*.d .*.tmp *.mod.c .tmp_versions
