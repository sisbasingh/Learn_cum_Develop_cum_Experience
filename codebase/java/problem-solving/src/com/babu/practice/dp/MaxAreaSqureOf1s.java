package com.babu.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaxAreaSqureOf1s {
	
	public static void main(String[] args) {
		MaxAreaSqureOf1s maxAreaSqureOf1s = new MaxAreaSqureOf1s();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(br.readLine());
			while(t > 0) {
				String mn[] = br.readLine().split("\\s+");
				int m = Integer.parseInt(mn[0]);
				int n = Integer.parseInt(mn[1]);
				int[] temp = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
				int k=0;
				int [][] mat = new int[m][n];
				for(int i=0;i<m;i++) {
					for(int j=0;j<n;j++) {
						mat[i][j] = temp[k++];
					}
				}
				System.out.println(maxAreaSqureOf1s.maxAreaSqureUtil(mat, m, n));
				t--;
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private int maxAreaSqureUtil(int a[][], int m, int n) {
		int [][] dp = new int[m][n];
		int max_area = 1;
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(i == 0 || j == 0) {
					dp[i][j] = a[i][j];
				} else if(a[i][j] == 1) {
					dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);
					max_area = Math.max(max_area, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}
		
		return max_area;
	}
	
	int min(int a, int b, int c) {
		return (a<b)?(a<c?a:c):(b<c?b:c);
	}

}
