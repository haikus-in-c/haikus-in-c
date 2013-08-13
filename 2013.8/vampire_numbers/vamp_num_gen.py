for i in range (1000, 10000):
    first = i/1000
    second = i/100 - 10* first
    third = i/10 - 100* first - 10* second
    fourth = int(str(i)[3]) 
    #print(str(first)+str(second)+str(third)+str(fourth))    
    a = [first, second, third, fourth] 
    a.sort() 
    if (a[1] > 0 and a[2] > 0): 
        largest = a[3] * 10 + a[1]
	largest2 = a[3]*10 + a[0]
        test = i/largest;
        test2 = i/largest2
        if(test <= a[2]*10 + a[0] or test2 <= a[2]*10 + a[1]):
             print(str(first)+str(second)+str(third)+str(fourth))
       
