#include <bits/stdc++.h>

using namespace std;

long max(long a, long b, long c)
{
    return a>b?(a>c?a:c):(b>c?b:c);
}

long min(long a, long b, long c) {
    return a<b?(a<c?a:c):(b<c?b:c);
}

long max_Product_SubArray(int* arr, int n) {
	long prev_max = arr[0], prev_min = arr[0], cur_max, cur_min, max_prod = arr[0];
	int i, cur_min_start = 0, cur_max_start = 0, start = 0, end = 0;
	    for(i=1;i<n;i++) {
	        cur_max = max(prev_max*arr[i], prev_min*arr[i], arr[i]);
	        if(cur_max == 0)
	        	cur_max = 1;
	        else if(cur_max == arr[i])
	        	cur_max_start = i;
	        else if(cur_max == prev_min*arr[i])
	        	cur_max_start = cur_min_start;
	        cur_min = min(prev_max*arr[i], prev_min*arr[i], arr[i]);
	        if(cur_min == 0)
	        	cur_min = 1;
	        else if(cur_min == arr[i])
	        	cur_min_start = i;
	        if(max_prod < cur_max) {
	        	max_prod = cur_max;
	        	start = cur_max_start;
	        	end = i;
			}
	        prev_max = cur_max;
	        prev_min = cur_min;
	    }
	    printf("Max product found between %d to %d is : %ld\n", start, end, max_prod);
}

int main() {
	int a[] = {6, -3, -10, 0, 2};
	int len = sizeof(a)/sizeof(a[0]);
	max_Product_SubArray(a, len);
	return 0;
}
