#include <stdio.h>

int main(int argc, char* argv[])
{
	if(argv[0]=="1")
		printf("%s", argv[1]);
	else
		printf("%s",argv[2]);
	
	return 0;
}
