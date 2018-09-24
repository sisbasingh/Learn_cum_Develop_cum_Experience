#include <iostream>
#include <vector>

using namespace std;
using std::vector;

/*
 * Function to count number of ways to reach bottom right corner of an m*n matrix from top left corner
 */
int numOfPathsRecur(int m, int n) {
	/*
	 * if it is first row or first column there is only one way to reach to it
	 */
	if(m == 0 || n == 0)
		return 1;
	/*
	 * else the number of ways to reach it are: from top + from left
	 */
	else return numOfPathsRecur(m-1, n) + numOfPathsRecur(m, n-1);
}

int numOfPathsUsingDP(int m, int n) {
	int DP[m][n];
	for(int i=0;i<m;i++) {
		for(int j=0;j<n;j++){
			if(i == 0 || j == 0)
				DP[i][j] = 1;
			else
				DP[i][j] = DP[i-1][j] + DP[i][j-1];
		}
	}
	return DP[m-1][n-1];
}

int numOfPathsSpaceEfficient(int m, int n) {
	if(m>n){
		//Swap to get min value in m
		m = m^n;
		n = m^n;
	}
	vector<int> rowOrColumn (m, 1);
	for(int i=1;i<n;i++) {
		for(int j=1;j<m;j++)
			rowOrColumn[j] += rowOrColumn[j-1];
	}
	return rowOrColumn[m-1];
}

int numOfPathsUsingCombination(int m, int n) {
	/**
	 * Number of paths will be: (m+n-2)C(m-1) OR (m+n-2)C(n-1)
	 */
	int numerator = m+n-2;
	int count = m<n?m-1:n-1;
	int numOfWays = 1;
	for(int i=0; i<count; i++){
		numOfWays *= (numerator - i);
		numOfWays /= (i+1);
	}
	return numOfWays;
}


int main() {
	int m,n;
	cin>>m>>n;
	cout<<"Using Recursion      :"<<numOfPathsRecur(m-1, n-1)<<endl;
	cout<<"Using DP             :"<<numOfPathsUsingDP(m, n)<<endl;
	cout<<"Using Combination    :"<<numOfPathsUsingCombination(m, n)<<endl;
	cout<<"Using Space Efficient:"<<numOfPathsSpaceEfficient(m, n)<<endl;
	return 0;
}
