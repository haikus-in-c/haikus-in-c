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

big-O Time Complexity: 
======================

My original python implementation returned a list of pythagorean triples in an ugly
O(n^3) because it used the python builtin search function on a list to check for the
presence of a square. 

My java implementation changes this and uses a HashMap to store the squares instead,
cutting out the last looping step and making the square-search a O(1) operation, thus
improving the overall time complexity to O(n^2).

Then I dove back into the python and experimented a little with the versatile python
dictionary, which for basic purposes behaves and functions like a HashMap. However,
by default when a key is found missing a python dictionary throws a KeyError, so I
subclassed the python dictionary and added a __missing__() method so that it would
keep its mouth shut. Now my python implemention is O(n^2) as well. 
