#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
unsigned long comb_pascal(int rowNum,int r, int x){

    rowNum +=1;
    unsigned long * array = (unsigned long *) malloc(sizeof(unsigned long) *(rowNum));

    int i;
    unsigned long* curr;
    for(i = 0; i<rowNum; i++) {
        curr = array + i;
        *curr = 1.0;
        while (--curr > array) {
            *curr += (*(curr-1)%x);
        }
    }


    unsigned long ans= array[r]%x;
    free(array);
    return ans;
}

long double comb_rec(int n, int r) {
        if (r == 0 || n == 0)
                return 1.0;
        long double ndub = (long double) n;
        long double rdub = (long double) r;
        return (ndub/rdub) * comb_rec(n-1, r-1);

}

double comb_hack (int n , int r) {
        double real_ans = comb_rec(n, r);
        //real_ans = fmod(real_ans, 142857.0);
        return real_ans;
}

unsigned long int comb2(int n, int r){
	unsigned long int ans = 1; 
	int i, j;
	i = n; j = 2; 
	while(i > r) {
		ans = ans * i--;
		if(ans%j == 0 && j<= (n-r) ) {
			ans = ans/j++;
		}
	}
	while (j <= (n-r)) {
		ans = ans/j++;
	}
	return ans%142857;
}
		

unsigned long int combination(int n, int r){
    //printf(" in combinatiion with %d and %d\n", n, r);
    //if(!r){ return -5;} 
    unsigned long int answer = 1; 
    int i, temp; 
    for (i = n; i>r; i--){
	printf("in first for loop %d\n", i);
        answer = answer* i;
	printf("%lu\n", answer);
    }
    for(i = (n-r); i> 1; i--){
        answer = answer/i;
	printf("second for loop %d  %lu\n", i, answer);
    }
    return answer;
}

int main() {
    int times, n, r, i;
    long unsigned int answer; 
    long unsigned int ans2;
    char buf[256]; 
    fgets(buf, 256, stdin);
    sscanf(buf, "%d", &times);
    //printf("%d\n", times);
    for(i = 0; i<times; i++){
        fgets(buf, 256, stdin);
	sscanf(buf, "%d %d", &n, &r);
            printf("%d %d\n", n, r);
            answer = comb_pascal(n,r, 142857);
            ans2 = comb2(n, r);
            printf("%lu\n", answer);        
    }
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */    
    return 0;
}



