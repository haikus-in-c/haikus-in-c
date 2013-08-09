/* Triangle Maximum Path Solver 
 * by Ruchir Khaitan - Sophomore at Columbia University eagerly seeking job
 * will work for food 
 * and drinks 
 */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

long recursivePathSum(int* valuePointer, int rowNum, int triangleSize, long currentSum);
long iterativePathSum(int* valuePointer, int triangleSize);

int main(int argc, char** argv) {

    if(argc !=2) {
        fprintf(stderr, "Usage: %s <file_name>\n", argv[0]);
        exit(1);
    }

    char* fileName = argv[1]; 
    FILE* file = fopen(fileName, "r");
    FILE* fp = fopen(fileName, "rb"); 
    
    if(file ==NULL) {
	fprintf(stderr, "File not found or unable to open");
	exit(1);
	}
    //Count number of lines in triangle
    int c;
    int numLine = 0;
    do {
        c = fgetc(file);
        if(c =='\n' ) numLine++;
    } while (c!=EOF);
 
   //Check if last character is not a newline
    fseek(fp, -1,SEEK_END); 
    c = fgetc(file);
    if (c != '\n') numLine++;
    fclose(fp); 
    rewind(file);

    /* Create array to store the numbers in the triangle - I am assumin that the triangle is well formed
     * such that a triangle with n rows will have n elements per row, and n*(n+1)/2 elements total
     */

    int* treeData = malloc(sizeof(int) * (numLine * (numLine+1))/2 + 1);
    char buf[4096];
    int num;
    char* numberEnd;
    int j = 0;
    while(fgets(buf, sizeof(buf), file)) {
	//test buffer here
	//printf("test buffer: %s", buf);
 	        
 	num =(int)  strtol(buf, &numberEnd, 10);
        do {
		treeData[j] = num;
                j++;
	} while(num = (int) strtol(numberEnd, &numberEnd, 10) );
    }
    fclose(file);
    int i = 0;
    /* To test that the array is formed correctly
     * while(i <((numLine *(numLine+1))/2))	{
     *		printf("The %dth element  %ld\n", i, treeData[i]);
     *		i++;
     *}
     */

    /* Using the recursive method to find the sum of a tree 
     * works fine, but is really much slower
     *long maxPathSum = recursivePathSum(treeData, 1, numLine, 0L);
     *printf("the sum is %ld\n", sum);
     */

     long maxPathSum = iterativePathSum(treeData, numLine);
     printf("The sum is %d\n", maxPathSum);

     free(treeData);
}


/* Recursive Max Path Sum Implementation 
 * rowNum is the row on the triangle that the function is currently loking at
 * valuePointer points to value function is currently looking at
 * triangleSize is the number of triangles in the function
 * currentSum is the running current sum
 */
long recursivePathSum(int* valuePointer, int rowNum, int triangleSize, long currentSum ) {

	if(rowNum == triangleSize) return currentSum + *(valuePointer);
	int* child1 = valuePointer + rowNum;
        int* child2 = child1+ 1;
	currentSum += *valuePointer;
	/* For testing purposes 
         *printf("sum  before recursing from %ld in %d is %ld\n", *valuePointer, rowNum, currentSum);
	 */

 
	long leftSum = recursivePathSum(child1, rowNum+ 1, triangleSize, currentSum);
        long rightSum = recursivePathSum(child2, rowNum + 1, triangleSize, currentSum);

	if (leftSum > rightSum) return leftSum;

        return rightSum;
	
	}
	
/*Iterative Path Sum Implementation 
 *Changes the array (triangle) being called upon 
 *Works much faster than fully recursive/brute force methods
 */
long iterativePathSum(int* valuePointer, int triangleSize) { 
	int i, j , k;
	for (i = triangleSize - 1; i>0; i--) {
		int* offsetPointer = valuePointer + (i*(i-1))/2; 
		for(j = 0; j<i; j++) {
			/*here I modify the original array
			 *I could have made a duplicate, or zeroed out the unnecessary rows to modify the array less haphazardly 
			 *as I went along, but I figured for the scope of this problem it was not specified
                         *so this function isn't reusable on the same array, but it is faster than the other options
			 */   
			*(offsetPointer + j) = (int) recursivePathSum((offsetPointer + j), i, i + 1, 0); 
		}
	}
	
	return *(valuePointer);

} 
