/* Code to solve toggling doors problem   
 * Uses elements of dynamic programming to efficiently simulate game, 
 * but is really unneccessary as this problem can be solved with
 * a bit of math and logic
 */
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
int toggle_doors(int doors){
	char* door_array = (char*) calloc(sizeof(char), doors+ 1); //init to zero
	//zero == open && one == open
	int i, j, num;
	for(i = 2; i<doors+ 1; i++){
		for(j = 1; i*j<doors+ 1; j++){
			door_array[i*j] = !(door_array[i*j]);
		}
	}
	num = 0;
	for(i = 1; i<doors+ 1; i++) {
		if(!door_array[i]){
			printf("Door %d is open\n", i);
			num++;
		}
	}
	free(door_array);
	return num;
}
int main(int argc, char** argv) {
	int answer = toggle_doors(100);
	printf("The answer is %d doors\n", answer);

}


