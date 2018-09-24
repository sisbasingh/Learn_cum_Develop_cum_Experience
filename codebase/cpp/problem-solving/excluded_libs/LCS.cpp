#include<iostream>

using namespace std;

int max(int a, int b){
	return a>b?a:b;
}

int LCS(string s1, int m, string s2, int n){
	int table [m+1][n+1];
	for(int i=0;i<=m;i++) {
		for(int j=0;j<=n;j++){
			//CASE : when any of the string is empty, LCS length will be 0
			if(i == 0 || j == 0)
				table[i][j] = 0;
			else if(s1[i-1] == s2[j-1]) //CASE : when current characters of both strings are same
				table[i][j] = 1 + table[i-1][j-1];
			else //CASE : take the max LCS by ignoring characters from either string
				table[i][j] = max(table[i-1][j], table[i][j-1]);
		}
	}

	//Get the LCS using the table computed earlier
	int lcsLength = table[m][n];
	string lcs = "";
	int i=m, j=n;
	while(i>0 && j>0) {
		if(table[i][j-1] == table[i][j])
			j--;
		else if(table[i-1][j] == table[i][j])
			i--;
		else {
			//Prepend character at position i-1 from s1
			lcs.insert(0, 1, s1[i-1]);
			i--;
			j--;
		}
	}

	cout<<"LCS: "<<lcs<<endl;
	return lcsLength;
}

int main() {
	string s1 ("ABCDGH");
	string s2 ("AEDFHR");
	cout<<"LCS Length : "<<LCS(s1, s1.length(), s2, s2.length())<<endl;
}
