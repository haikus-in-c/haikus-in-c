Problem: Given three integers n,r, and p where p is always prime, return nCr % p 
The true test here is that even though the algorithm to generate combinations based on the formula :

nCr = n!/r!(n-r)! the numbers n and r can be very large - so that values frequently exceed 64 bit CPU's limits of 2^64 - 1 ~ 2 * 10^19 

One workaround is to use the distributive property of the modulus operator:
(a + b) % n ={ (a%n) + (b%n) } % n and build the nth row of pascal's triangle - but values are so large that they will regularly cause segmentation fault. 

Is very clear after doing some more research that this problem requires
advanced mathematics

Potential option: Lucas's Theorem which states that

nCr mod p - p is prime 
where n = kn * p^n + k(n-1) p^(n-1) .. + .. k1p + k0p^0 or knk(n-1)...k1k0 is
n expressed in base p 
and r is the same way, bnb(n-1)...b1b0 

Then nCr mod p = 

(knCbn * k(n-1)Cb(n-1) .. * .. k1Cb1 * k0Cb0 ) mod p 

So this shifts the problem to one of:
translating a number from decimal to arbitrary base
performing combinations on it 

how would this approach work on integers << than p but still pretty big, for
example 1437 C 236 - which still crashes out, but be unaffected by using the
above computation, since neither n or k are multiples of p? idk
