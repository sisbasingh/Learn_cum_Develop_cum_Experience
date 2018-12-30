'''
Created on Dec 26, 2018

@author: sisba01
'''

def riddle(arr):
    mdict = {}
    mdict[arr[0]] = 1
    result = []
    maxMin = arr[0]
    mlist = []
    i = 1
    for i in range(1, len(arr)):
        if(arr[i] in mdict):
            mdict[arr[i]] += 1
        else:
            mlist.append((arr[i-1], mdict[arr[i-1]]))
            mdict.clear()
            mdict[arr[i]] = 1
        if(maxMin < arr[i]):
            maxMin = arr[i]
    # Add the last element
    mlist.append((arr[i], mdict[arr[i]]))
    
    maxMin = mlist[0][0]
    n = len(mlist)
    
    while n > 0:
        maxMin = mlist[0][0]
        for i in range(1, n):
            if(mlist[i][0] > maxMin):
                maxMin = mlist[i][0]
            if(mlist[i][0] < mlist[i-1][0]):
                mlist[i-1] = mlist[i]
        result.append(maxMin)
        n -= 1
        
    print(result)
    
    
n = int(input())
arr = list(map(int, input().split(" ")))

riddle(arr)

    
    
        
        
