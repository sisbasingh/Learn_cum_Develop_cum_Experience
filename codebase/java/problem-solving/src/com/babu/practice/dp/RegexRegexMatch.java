package com.babu.practice.dp;

import java.util.Arrays;

public class RegexRegexMatch {
	
	private boolean isRegexMatch(String rx1, String rx2) {
		//Remove consecutive start from both the patterns for optimizations
		rx1 = removeConsecutiveStars(rx1);
		rx2 = removeConsecutiveStars(rx2);
		
		System.out.println("rx1: " + rx1 + "\nrx2: " + rx2);
		
		int m = rx1.length();
		int n = rx2.length();
		boolean [][] dp = new boolean[m+1][n+1];
		
		dp[0][0] = true;
		
		//Fill first row
		for(int j=1;j<=n;j++) {
			if(rx2.charAt(j-1) == '*') {
				dp[0][j] = dp[0][j-1];
			}
		}
		//Fill first column
		for(int i=1;i<=m;i++) {
			if(rx1.charAt(i-1) == '*') {
				dp[i][0] = dp[i-1][0];
			}
		}
		//Compute using DP
		for(int i=1;i<=m;i++) {
			for(int j=1;j<=n;j++) {
				if(rx1.charAt(i-1) == rx2.charAt(j-1) && rx1.charAt(i-1) == '*') {
					dp[i][j] = dp[i-1][j-1] || dp[i-1][j] || dp[i][j-1];
				} else if(rx1.charAt(i-1) == '*' || rx2.charAt(j-1) == '*') {
					dp[i][j] = dp[i][j-1] || dp[i-1][j];
				} else if((rx1.charAt(i-1) == rx2.charAt(j-1)) || (rx1.charAt(i-1) == '?' || rx2.charAt(j-1) == '?')) {
					dp[i][j] = dp[i-1][j-1];
				}
			}
		}
		
		printDP(dp, m, n);
		
		return dp[m][n];
		
	}
	
	private String removeConsecutiveStars(String str) {
		char[] sarr = str.toCharArray();
		int i=0, j=1;
		while(j<sarr.length) {
			if(sarr[i] == '*' && sarr[j] == '*');
			else {
				sarr[++i] = sarr[j];
			}
			j++;
		}
		return new String(Arrays.copyOfRange(sarr, 0,i+1));
	}
	
	private void printDP(boolean [][] dp, int m, int n) {
		for(int i=0;i<=m;i++) {
			for(int j=0;j<=n;j++) {
				char op = dp[i][j]?'T':'F';
				System.out.print(op + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		RegexRegexMatch regexMatch = new RegexRegexMatch();
		String rx1 = "*****b?";
		String rx2 = "b***?c";
		
		System.out.println(regexMatch.isRegexMatch(rx1, rx2));
	}

}
