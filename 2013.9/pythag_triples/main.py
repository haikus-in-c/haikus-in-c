# find pythagorean triples in array of integers
# by Ian Zapolsky (09/03/13)

from collections import defaultdict

# gen array of squares
def gen_squares(array):
    squares = []
    for integer in array:
        squares.append(integer*integer)
    return squares

# subclass dict so we can define the behavior of dict when key is not found
class QuietDict(defaultdict):
    
    def __missing__(self, key):
        return None

# gen python dict (basic equiv. of hash) of squares
def gen_squares_dict(array):
    squares = QuietDict()
    for i in array:
        squares[(i*i)] = i
    return squares

# use O(1) dict lookup to check for target
def return_triples_fast(array):
    triples = []
    squares = gen_squares_dict(array)
    for i in range(len(array)-1):
        for j in range((i+1), len(array)):
            target = ((array[i]*array[i])+(array[j]*array[j])) 
            if squares[target] != None:
                triples.append([array[i], array[j], squares[target]])
    return triples

# use O(n) array search to check for target
def return_triples(array):
    triples = []
    squares = gen_squares(array) 
    for i in range(len(array)-1):
        for j in range((i+1), len(array)):
            try:
                triples.append([array[i], array[j], array[squares.index(squares[i]+squares[j])]])
            except:
                pass
    return triples


""" main """    
array = [1,3,4,5,6,7,8,10,11]
print return_triples_fast(array)
