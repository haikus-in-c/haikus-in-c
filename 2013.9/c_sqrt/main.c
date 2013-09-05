/* main.c for c_sqrt: find the sqrt of x naively
   by Ian Zapolsky (09/05/13) */

#include <stdio.h>
#include <stdlib.h>

double backup(double square, double start) {
    double cur = start-.01;
    while (cur > start-1 && cur*cur > square) {
        printf("now checking: %f.\n", cur);
        if ((cur*cur) == square)
            return cur;
        cur -= .0001;
    }
    return cur;
}

double search_root(double square) {
    double i;
    for (i = 1; i < square/2; i++) {
        printf("now checking: %f.\n", i);
        if (i*i >= square) {
            if (i*i == square)
                return i;
            else
                return backup(square, i);
        }
    }
}

int main() {
    double root = search_root(1021);
    printf("root is %f.\n", root);  
    return 0;
}

