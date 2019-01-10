'''
Created on Jan 10, 2019

@author: sisba01
'''

from collections import Counter

'''
    This code currently applicable for a single string only
'''

s = input();
countMap = Counter(s);
# print(countMap);
'''
    First sort by value(in descending order) and then by key (in ascending order)
'''
sortedItems = sorted(countMap.items(), key=lambda x:(-x[1], x[0]));

for i in range(0, 3):
    print("%c %d"% (sortedItems[i][0], sortedItems[i][1]));
