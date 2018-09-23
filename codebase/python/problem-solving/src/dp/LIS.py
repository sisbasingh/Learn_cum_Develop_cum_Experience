'''
Created on Oct 9, 2017

@author: sisba01
'''

def binarySearchForGreaterOrEqualTo(arr, l, h, item):
    while(l<h):
        mid = int(l+(h-l)/2);
        if(arr[mid] == item):
            return mid;
        elif(arr[mid] < item):
            if(mid < h and arr[mid+1] > item):
                return mid+1;
            l = mid+1;
        else:
            if(mid > l and arr[mid-1] < item):
                return mid;
            h = mid-1;
            
    ## If not found
    return l;

def printLISUsingDPON2(arr, n):
    if(n == 0 or n == 1):
        return n;
    parent = [-1] * n;
    LIS = [1] * n;
    maxLength = 0;
    lastIndex = -1;
    for i in range(1, n):
        for j in range(0, i):
            if(arr[i] > arr[j] and LIS[i] < LIS[j]+1):
                LIS[i] = LIS[j]+1;
                parent[i] = j;
        if(maxLength < LIS[i]):
            maxLength = LIS[i];
            lastIndex = i;
            
    #Print LIS
    print ("Lenght of LIS: %d" % maxLength);
    while(lastIndex != -1):
        print("%d" % arr[lastIndex], end=', ');
        lastIndex = parent[lastIndex];
    return maxLength;


def printLISUsingDPNlogN(arr, n):
    if(n == 0 or n == 1):
        return n;
    LIS = [-1]*n;
    lastIndex = 0;
    LIS[lastIndex] = arr[0];
    for i in range(1, n):
        if(arr[i] > LIS[lastIndex]):
            lastIndex += 1;
            LIS[lastIndex] = arr[i];
        else:
            swapIndex = binarySearchForGreaterOrEqualTo(LIS, 0, lastIndex, arr[i]);
            LIS[swapIndex] = arr[i];
    
    print("Length of LIS: %d" % int(lastIndex+1));
#     for i in range(0, lastIndex+1):
#         print("%d" % LIS[i]);
    print (*LIS[0:lastIndex+1], sep=', ');
    return lastIndex+1;
        

## Driver
## Number of tests
t = 1;
while(t > 0):
    arr = list(map(int, input().split()));
    #print("%d" % greaterOrEqualToBinarySearch(arr, 0, int(len(arr)-1), 7));
    print("Using DP On2:");
    printLISUsingDPON2(arr, int(len(arr)));
    print("\nUsing DP Onlogn:");
    printLISUsingDPNlogN(arr, int(len(arr)));
    t -= 1;
    