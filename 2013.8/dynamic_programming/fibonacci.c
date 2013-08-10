#include <stdlib.h>
#include <string.h>
#include <stdio.h>

int fib_rec(int n){
	if (n == 0)
		return 0;
	if (n == 1 ) 
		return 1; 
	
	return fib_rec(n-1) + fib_rec(n-2);

}

void print_fib_rec(int n) {
	int i = 0;
	int fib_n;
	for(i = 0; i<n; i++){
		fib_n = fib_rec(i); 
		printf("%d\n", fib_n);
	}

}	

void print_fib_dynamic(int n){
	int fib_val[2];
	fib_val[0] = 0;
	fib_val[1] = 1;

	int i;
	for (i = 0; i<n; i++) {
		printf("%d\n", fib_val[0]);
		
		fib_val[1] += fib_val[0]; 
		fib_val[0] = fib_val[1] - fib_val[0];
	}

}

int main(int argc, char** argv){
	print_fib_rec(10);
	print_fib_dynamic(10);

}
	
 		
