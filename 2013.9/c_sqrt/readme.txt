Question:
=========

Write a program in C to find the square root of an integer without using builtin functions.

Solution:
=========

This solution is self-admittedly 'janky' but I originally wrote this algorithm on paper, and
didn't know any algorithms for finding square roots.

1.  Beginning at 1, check every integer multiplied by itself against the target square,
    incrementing after each check.
    1.  If at any point we get the square, return the integer that, when multiplied by
        itself, yielded it. This is the square root. We are done.
    2.  If at any point we get a number that is bigger than the square we're looking for,
        stop this incrementing loop.
        1.  Begin counting backwards from the integer we stopped on (the first integer whose
            square gave us something bigger than what we're looking for) by some small
            interval (.0001 in the test program).
        2.  Check the square of this *rewinding* value after each decrement.
            1.  If at any point we get the square we're looking for, return the 
                *rewinding* value. This is the square root. We are done.
            2.  If at any point we get a square that is smaller than the square we're
                looking for, just return the *rewinding* value. When this happens it
                means that the interval we chose was too big to find the exact square
                root, which is OK. This is a naive method.

big-O Time Complexity:
======================


