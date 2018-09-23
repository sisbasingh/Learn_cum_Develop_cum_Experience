def binarySearch(arr, l, h, k):
    while(l<=h):
        mid = l + (h-l)//2;
        if(arr[mid] == k):
            return mid+1;
        elif(arr[mid] > k):
            h = mid-1;
        else:
            l = mid+1;
    return -1;
    
#s = input();
n =   int(input());
a = list(map(int, input().split()));
a.sort();
# for i in range(1, n):
#     t = input();
#     a.append(int(t));
m = input();
for i in range(0, int(m)):
    t = input();
    op = binarySearch(a, 0, int(len(a)-1), int(t));
    print("%d" % op);