Problem: print the first n rows of Pascal's Triangle using only O(n) memory 

Solution: 

pascal.c solves this problem using O(n) space and 0(n^2) time 

Outputs n rows  (technically n+1) to stdout 
Takes as input 1 command line integer for number of rows

Test Run:
$ gcc pascal.c -o pascal
$ ./pascal 7
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1
1 6 15 20 15 6 1
1 7 21 35 35 21 7 1
