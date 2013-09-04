Question:
=========

Given an array of integers write an algorithm to find all the pythagorean triples.

Solution:
=========

1.  Create an array of all the squares of the numbers in the input array. For example,
    given an initial array of [3,4,5] we make [9,16,25]. 
2.  Loop though the array of squares, summing every possible pair and checking to see
    if the result exists in the array.
    1.  If the result does exist, we know that there is a pythagorean triple in the
        original array (a^2 + b^2 = c^2). We create an array to represent the triple
        and then add that to a final array of triples meant to be returned at the end.
    2.  If the result does not exist, pass on the pair and continue to the next. 
3.  Return the array of triples.

big-O time complexity = O(n^2)
