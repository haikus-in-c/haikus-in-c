import random
times = raw_input()
f = open('input.txt', 'w')
f.write(times + '\n')
for j in range(int(times)):

    cats = str(random.randint(1,100))
    dogs = str(random.randint(0,100))
    voters = str(random.randint(0, 500))
    f.write(cats+' '+ dogs+ ' ' + voters+ '\n')
    for i in range(int(voters)):
        decider = random.random()
        cat = random.randint(1,int(cats))
        dog = random.randint(1, int(dogs))
        if (decider > .51):
            f.write('C' + str(cat) + " " + 'D'+ str(dog)+ '\n')
        else:
            f.write('D' +str(dog) + " " + 'C' + str(cat)+'\n')

f.close()
