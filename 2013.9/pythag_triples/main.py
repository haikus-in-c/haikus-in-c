# find pythagorean triples in array of integers
# by Ian Zapolsky (09/03/13)

def gen_squares(array):
    squares = []
    for integer in array:
        squares.append(integer*integer)
    return squares

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
print return_triples(array)
