'''
Created on Oct 26, 2017

@author: sisba01
'''

'''
    This method computes the longest proper prefix that is also a suffix for the pattern to be matched for each i from
    0 to m indexes and returns it
'''
def computeLPS(pattern, m):
    lps = [0]*m;
    length, i = 0, 1;
    while(i<m):
        if(pattern[length] == pattern[i]):
            length += 1;
            lps[i] = length;
            i += 1;
        else:
            if(length > 0):
                length = lps[length-1];
            else:
                lps[i] = 0;
                i += 1;
    return lps;

'''
    This method matches the pattern in the given string if present and prints
    the starting index of the pattern in the given string
'''
def KMPPatternMatch(string, pattern):
    n = int(len(string));
    m = int(len(pattern));
    lps = computeLPS(pattern, m);
    i, j = 0, 0;
    while(i < n):
        if(string[i] == pattern[j]):
            i += 1;
            j += 1;
        if(j == m):
            print("Pattern Match found at index: " + str(i-j));
            j = lps[j-1];
        elif(i<n and string[i] != pattern[j]):
            if(j > 0):
                j = lps[j-1];
            else:
                i += 1;

'''
    Driver module
'''
t = int(input());
while(t > 0):
    txt = input();
    pattern = input();
    KMPPatternMatch(txt, pattern);
    t -= 1;
