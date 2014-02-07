var fibonacci = function () {

  // keep track of how many times we call fib
  var times = 0;

  // memoization cache to keep track of calculated values
  var memoization_cache = [0, 1];
  
  var fib = function (n) {
    times = times+1;
    var result = memoization_cache[n];
    if (typeof(result) !== 'number') {
      result = fib(n-1) + fib(n-2);
      memoization_cache[n] = result;
    } 
    return result;
  };

  var get_times = function () {
    return times;
  };

  return { fib : fib,
           get_times : get_times };

}();

var fibonacci_slow = function () {

  // keep track of how many times we call fib
  var times = 0;

  var fib = function (n) {
    times = times+1;
    if (n === 0 || n === 1) {
      return n;
    } else {
      return fib(n-1) + fib(n-2);
    }
  };

  var get_times = function () {
    return times;
  };

  return { fib : fib,
           get_times : get_times };

}();

  
