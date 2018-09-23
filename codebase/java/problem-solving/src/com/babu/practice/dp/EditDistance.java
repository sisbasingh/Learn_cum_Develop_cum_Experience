package com.babu.practice.dp;

import java.util.Scanner;

public class EditDistance {
	
	public static int min(int a, int b, int c) {
		return (a<b)?((a<c)?a:c):(b<c)?b:c;
	}
	
	public static int editDistRecur(char[] s1, char[] s2, int m, int n) {
		if(m == 0) return n;
		else if(n == 0) return m;
		else if(s1[m-1] == s2[n-1]) 
			return editDistRecur(s1, s2, m-1, n-1);
		else
			return 1 + min(editDistRecur(s1, s2, m-1, n), editDistRecur(s1, s2, m, n-1), editDistRecur(s1, s2, m-1, n-1));
	}
	
	public static int editDistDP(char[] s1, char[] s2, int m, int n) {
		int[][] table = new int[m+1][n+1];
		
		for(int i=0;i<=m;i++) {
			for(int j=0;j<=n;j++) {
				if(i == 0)
					table[i][j] = j;
				else if(j == 0)
					table[i][j] = i;
				else if(s1[i-1] == s2[j-1])
					table[i][j] = table[i-1][j-1];
				else
					table[i][j] = 1 + min(table[i-1][j], table[i][j-1], table[i-1][j-1]);
			}
		}
		return table[m][n];
	}
	
	public static void main (String[] args) {
	    Scanner in = new Scanner(System.in);
		int t;
		t = in.nextInt();
		for(int i=0;i<t;i++) {
		    int m, n;
		    m = in.nextInt();
		    n = in.nextInt();
		    String s1, s2;
		    s1 = in.next();
		    s2 = in.next();
		    System.out.println("Using Recurrence:: " + editDistRecur(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
			System.out.println("Using Recurrence:: " + editDistDP(s1.toCharArray(), s2.toCharArray(), s1.length(), s2.length()));
		}
		
	}
}
