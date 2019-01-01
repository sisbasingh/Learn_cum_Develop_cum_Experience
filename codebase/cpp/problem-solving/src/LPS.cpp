#include <bits/stdc++.h>
using namespace std;

string lps(string s){
    string s1 = s;
    string s2 = s;
    reverse(s2.begin(), s2.end());

    int n = s.length();
    int dp[n+1][n+1] = {0};
    int maxLen = 0;
    int endI = 0;

    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(s1[i-1] == s2[j-1]){
                dp[i][j] = dp[i-1][j-1]+1;
                if(dp[i][j] > maxLen){
                    maxLen = dp[i][j];
                    endI = i-1;
                }
            } else {
                dp[i][j] = 0;
            }
        }
    }
    return s.substr(endI-maxLen+1, maxLen);
}

int main() {
	//code
	int t;
	cin>>t;
	while(t--){
	    string str;
	    cin>>str;
	    cout<<lps(str)<<endl;
	}
	return 0;
}
