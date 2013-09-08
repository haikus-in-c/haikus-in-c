#include <stdio.h>
#include <stdlib.h>
#include <string.h>

double newton_sqrt(double input, double error) {
    double approx_answer = input/2.0;
    while(((approx_answer* approx_answer) -input) > error){
        printf("Now checking %lf\n", approx_answer);
        approx_answer = (approx_answer + input/approx_answer) * .5;
    }
    return approx_answer;


}

int main(int argc, char** argv) {
    double val, ans;
    printf("Enter desired square root value: ");
    scanf("%lf", &val);
    ans = newton_sqrt(val, .000001);
    printf("The square root is: %lf\n", ans);
}
            
