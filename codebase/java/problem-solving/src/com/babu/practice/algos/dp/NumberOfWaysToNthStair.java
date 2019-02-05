package com.babu.practice.algos.dp;

import java.util.Scanner;

public class NumberOfWaysToNthStair {
	
	
	/**
	 * TODO : this method is still under progress
	 * @param n
	 * @param k
	 * @return
	 */
	private static int usingFibbonacciSequence(int n, int k) {
		return 0;
	}
	
	private static int spaceEfficientUsingDP(int n, int k) {
		int [] DP = new int[n+1];
		//For 0th stair only one way : don't climb any stair, we are already there
		DP[0] = 1;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=k && i-j>=0 ;j++) {
				DP[i] += DP[i-j];
			}
		}
		return DP[n];
	}
	
	
	public static void main(String [] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		System.out.println(usingFibbonacciSequence(n, k));
		System.out.println(spaceEfficientUsingDP(n, k));
	}

}
