'''
Created on Oct 20, 2017

@author: sisba01
'''
from builtins import range

def max(a, b):
    return a if a>b else b;

def printLCSOn2(s1, s2, m, n):
    if(m == 0 or n == 0):
        return 0;
    LCS = [[0 for i in range(n+1)] for j in range(m+1)];
    for i in range(0, m+1):
        for j in range(0, n+1):
            if(i == 0 or j == 0):
                LCS[i][j] = 0;
            elif(s1[i-1] == s2[j-1]):
                LCS[i][j] = LCS[i-1][j-1]+1;
            else:
                LCS[i][j] = max(LCS[i-1][j], LCS[i][j-1]);
    
    return LCS[m][n];



## Driver
## Number of tests
t = int(input());
while(t > 0):
    m, n = input().split();
    s1 = input();
    s2 = input();
    print("%d" % printLCSOn2(s1, s2, int(m), int(n)));
    t -= 1;
    
    