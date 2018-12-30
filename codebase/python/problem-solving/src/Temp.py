'''
Created on Dec 20, 2018

@author: sisba01
'''

t = int(input())
while(t>0):
    nk = input().split(' ')
    n = int(nk[0])
    k = int(nk[1])
    cost = list(map(int, input().split(' ')))
    
    i = 0
    j = 0
    numFlower = 0
    minCost = 0
    
    cost.sort(reverse = True)
    while i < len(cost):
        j = 0
        while j < k and i < len(cost):
            minCost += (numFlower + 1)*cost[i]
            i += 1
            j += 1
        numFlower += 1
        
    print("Min Cost is %d" % minCost)
    
    t -= 1
