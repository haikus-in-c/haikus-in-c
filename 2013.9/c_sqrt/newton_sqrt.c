#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

double newton_sqrt(double input, double error) {
    double approx_answer = input/2.0;
    while(((approx_answer* approx_answer) -input) > error){
        //printf("Now checking %lf\n", approx_answer);
        approx_answer = (approx_answer + input/approx_answer) * .5;
    }
    return approx_answer;


}
void test_loop(double start_val,double end_val, double error) {
  time_t t0, t1;
  clock_t c0, c1;
  double val, ans;
  val = start_val;
  printf("Taking square roots from %lf to %lf with error %lf\n", start_val, end_val, error);

  c0 = clock();
  t0 = time(NULL);
  while(val < end_val){
      ans = newton_sqrt(val, error);
      val = val + 1;
  }
  c1 = clock();
  t1 = time(NULL);
  printf("Took %f clock ticks or %ld wall-clock seconds\n", (float) (c1-c0), (long) (t1 - t0));
}

void test_sqrt(){
    double error = .000001;
    double start, end;
    start = 2.0;
    end = 10.0; 
    while (end < 10000000.0) {
      test_loop(start, end, error);
      end = end* 2.0;
    }
    end = 1000000;
    error = .01;
    while (error > .00000001){
        test_loop(start, end, error);
        error = error * .5;
    }
}


int main(int argc, char** argv) {
    double val, ans;
    printf("Enter number to take square root of: ");
    scanf("%lf", &val);
  
    ans = newton_sqrt(val, .000001);
    printf("The square root is: %lf\n",ans);
    //test_sqrt();
}



            
