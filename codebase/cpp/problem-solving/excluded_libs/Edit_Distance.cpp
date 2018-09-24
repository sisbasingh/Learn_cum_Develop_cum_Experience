#include <iostream>
using namespace std;

int min(int a, int b, int c){
	return a<b?(a<c?a:c):b<c?b:c;
}

int editDistance(string str1, string str2, int m, int n){
	if(m == 0)  return n;
	if(n == 0)  return m;

	int table [m+1][n+1];
	for(int i=0;i<=m;i++){
		for(int j=0;j<=n;j++){
			if(i == 0) table[i][j] = j;
			else if(j == 0) table[i][j] = i;
			else {
				if(str1[i-1] == str2[j-1])
					table[i][j] = table[i-1][j-1];
				else
					table[i][j] = 1 + min(table[i-1][j], table[i][j-1], table[i-1][j-1]);
			}
		}
	}
	return table[m][n];
}

int main() {
	//code

	int t,m,n;
	string str1, str2;
	cin>>t;

	for(int i=1;i<=t;i++){
		cin>>m;
		cin>>n;
		cin>>str1;
		cin>>str2;
		cout<<editDistance(str1, str2, m, n)<<endl;
	}

	return 0;
}
