#include <bits/stdc++.h>

using namespace std;

int max(int a, int b) {
	return a>b?a:b;
}

int max_SubArray_Sum(int* a, int n) {
	if(n==0)
		return INT_MIN;
	int curr_sum=a[0], max_sum=a[0], i, cur_start = 0, start = 0, end = 0;
	for(i=1;i<n;i++) {
		curr_sum = max(a[i], a[i]+curr_sum);
		if(curr_sum == a[i])
			cur_start = i;
		if(max_sum < curr_sum) {
			max_sum = curr_sum;
			start = cur_start;
			end = i;
		}
	}
	printf("\nMax sub array sum between indexes: %d to %d is: ", start, end);
	return max_sum;
}

int main() {
	int a[] = {-2, 0, 3, -4, 2, -1, 3};
	int len = sizeof(a)/sizeof(a[0]);
	printf("%d\n", max_SubArray_Sum(a, len));
	return 0;
}
