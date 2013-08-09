/* Code to print the first n rows of pascals triangle
 * also stores the nth roow in memory as an array 
 * requires 1 command line argument for number of rows and doesn't contain error checking
 * by Ruchir Khaitan
 */

#include <stdlib.h>
#include <stdio.h> 

int main(int argc, char** argv ) {
    int rowNum = atoi(argv[1]) + 1;
    unsigned long* array = malloc(sizeof(long) *rowNum);

    int i, j;
    unsigned long* curr;
    for(i = 0; i<rowNum; i++) { 
        curr = array + i;
        *curr = 1; 
        while (--curr > array) {
            *curr += *(curr-1);
        }
        //printf("[");
        for(j = 0; j<i; j++) {
            printf("%u ", *(array+j));
        }
        printf("%u\n", *(array+ i));
  }

    free(array);

}



