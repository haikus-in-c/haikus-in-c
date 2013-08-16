Problem: Given three integers n,r, and p where p is always prime, return nCr % p 
The true test here is that even though the algorithm to generate combinations based on the formula :

nCr = n!/r!(n-r)! the numbers n and r can be very large - so that values frequently exceed 64 bit CPU's limits of 2^64 - 1 ~ 2 * 10^19 

One workaround is to use the distributive property of the modulus operator:
(a + b) % n ={ (a%n) + (b%n) } % n and build the nth row of pascal's triangle - but values are so large that they will regularly cause segmentation fault. 

 
