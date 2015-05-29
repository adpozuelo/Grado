
ifneq ($(KERNELRELEASE),)
	obj-m := newsyscall2.o 
else
	include ../Makefile
	KERNELDIR := ../../$(KERNEL_VERSION)
	PWD := $(shell pwd)

all: modules testnew

modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) modules

testnew: testnew.c
	gcc -static -D__KERNEL__ -I../../$(KERNEL_VERSION)/include testnew.c -o testnew
	
clean:
	rm -rf *.[oas] .*.flags *.ko .*.cmd .*.d .*.tmp *.mod.c .tmp_versions Module.symvers *.order testnew
endif


