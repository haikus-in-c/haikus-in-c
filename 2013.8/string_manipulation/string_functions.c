/* Set of functions for basic string manipulation
 * including in place rotation, reversal, word reversal
 * by Ruchir Khaitan 
 * main() just includes random test cases
 */
#include <stdio.h>
#include <stdlib.h>

void reverse(char* input);
int stringlen(char* input);
void reverseBound(char* start, char* end);
void rotate (char* input, int k);
void reverseWords(char* input);
int main(int argc, char** argv) {
  char one[]= "madam i'm adam";
  char test[] = "abcdefg";

  reverse(one);
  printf("%s\n", one);
  reverse(one);
  printf("%s\n", one);
  printf("%s\n", test);
  rotate(test, 3);
  printf("%s\n", test);

  
  reverseWords(one);
  printf("%s\n", one);
  }

void reverse(char* input) {
  int i = 0;
  while(*(input + i)) {
      i++;

    }
    char *end = input + --i;
    char swap;
    while(end>input){
      swap = *end;
      *end-- = *input;
      *input++ = swap;
    }
}

int stringlen(char* input){
    int i = 0;
    while(*(input+i)) { i++; }
        return i;
  }
void reverseBound(char* start, char*end){
    char swap;
    if (start == NULL || end ==NULL || start > end) {
        return;
    }
    while(end > start) {
        swap = *end;
        *end-- = *start;
        *start++ = swap;
    }

  }

void rotate(char* input, int k) {
    reverse(input);
    reverseBound(input, input + k - 1);
    reverseBound(input+k, input + stringlen(input) - 1);

}

void reverseWords(char* input) {
    reverse(input);
    int i;
    char* curr = input;
    for(i = 0; i<stringlen(input); i++){
        if(*(input + i) == ' '){
            reverseBound(curr, input + i-1);
            curr = input + i + 1;
        }
    }
    if(*(input + stringlen(input) - 1) != ' ')
        reverseBound(curr, input + stringlen(input) - 1);
}

