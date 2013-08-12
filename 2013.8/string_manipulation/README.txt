Problem: perform some commn operations on character strings in-place (using constant space) 

Solution

string_functions.c contains code for several string transformations

reverse(char* string) - reverses the string 
reverseBound(char* start, char* end) - reverses the string from start to end -
ot including the character pointed to by end 

rotate(char* string, int n) - rotates right by n values 

stringlen(char * s) - returns length of string not including the '\0' same as
strlen() 

reverseWords(char* s) reverses the words in a string 

Note - all of these functions assume that inputs are NULL terminated. A safer
method would be to require a length be passed as an argument for each in case
the string is incorrectly formatted, but this addition is fairly easy to
implement and does not change the underlying idea behind these functions,
which is what we hoped to connvey. So please, don't use in the real world. `
