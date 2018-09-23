'''
Created on Oct 5, 2017

@author: sisba01
'''
from random import randint
import math;

def swap(arr, i, j):
    temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
    
def partition(arr, l, h):
    pivot = arr[h];
    pi = l;
    for i in range(l, h):
        if(arr[i] <= pivot):
            swap(arr, i, pi);
            pi += 1;
    swap(arr, pi, h);
    return pi;

def findMedian(arr, l, h):
    mi = int((h-l)/2);
    isNumOfElemEven = True if(h%2 != 0) else False;
    while(l<=h):
        curr_pi = randint(l, h);
        swap(arr, curr_pi, h);
        next_pi = partition(arr, l, h);
        if(next_pi == mi):
            if(isNumOfElemEven):
                smallest = arr[mi+1];
                for j in range(mi+2, h+1):
                    if(smallest > arr[j]):
                        smallest = arr[j];
                return (arr[mi] + smallest)/2;
            else:
                return arr[mi];
        elif(mi < next_pi):
            while(next_pi > mi+1 and arr[next_pi] == arr[next_pi-1]):
                next_pi -= 1;
            h = next_pi-1;
        else:
            while(next_pi < mi+1 and arr[next_pi] == arr[next_pi+1]):
                next_pi += 1;
            l = next_pi+1;
    return -1;

def findMedianUtil(arr, l, h):
    return findMedian(arr, l, h-1);

# Code snippets
t = int(input());
while(t > 0):
    n = int(input());
    a = list(map(int, input().split()));
    ans = findMedianUtil(a, 0, n);
    print("%d" % math.floor(ans));
    t -= 1;
               
        