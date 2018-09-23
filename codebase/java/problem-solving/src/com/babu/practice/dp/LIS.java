package com.babu.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LIS {
	
	private static int getInsertionPositionUsingBinarySearch(int [] A, int l, int n) {
		int s = 0, e = l;
		while(e-s > 1) {
			int mid = s + (e-s)/2;
			if(A[mid] == n) return mid;
			else if(A[mid] < n) {
				s = mid;
			} else {
				e = mid;
			}
		}
		return e;
	}
	
	private static int lisLengthUsingOnlogn(int n, int [] A) {
		int curIndex = 0;
		int[] list = new int[n];
		list[0] = A[0];
		for(int i=1;i<n;i++) {
			if(A[i] > list[curIndex])
				list[++curIndex] = A[i];
			else if(A[i] == list[curIndex]) ; //Do nothing
			else if(A[i] <= list[0])
				list[0] = A[i];
			else {
				int pos = getInsertionPositionUsingBinarySearch(list, curIndex, A[i]);
				list[pos] = A[i];
			}
		}
		
		for(int i=0;i<=curIndex;i++)
			System.out.print(list[i] + " ");
		System.out.println();
		return curIndex + 1;
	}
	
	private static int lisLengthUsingOn2(int n, int [] A) {
		int [] dp = new int[n];
		Arrays.fill(dp, 1);
		int maxLength = 0;
		for(int i=1;i<n;i++) {
			for(int j=i-1;j>=0;j--) {
				if(A[i] > A[j] && dp[j] >= dp[i])
					dp[i] = 1 + dp[j];
			}
			maxLength = Math.max(maxLength, dp[i]);
		}
		return maxLength;
	}
	
	
	private static boolean tripletSumOn2(int [] a, int n) {
		Arrays.sort(a);
		int len = a.length;
		for(int i=0;i<len-2;i++) {
			int j = i+1, k = len-1;
			int sum = n-a[i];
			while(j<k) {
				if(a[j] + a[k] == sum)
					return true;
				else if(a[j]+a[k] < sum)
					j++;
				else
					k--;
			}
		}
		return false;
	}
	
	private static void testTripletSum() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(br.readLine());
			for(int i=1;i<=t;i++) {
				StringTokenizer tokenizer = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(tokenizer.nextToken());
				int sum = Integer.parseInt(tokenizer.nextToken());
				int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
				System.out.println(tripletSumOn2(array, sum));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] argss) {
		
		//testTripletSum();
		
		/*Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for(int i=1;i<=t;i++) {
			int n = scanner.nextInt();
			int [] array = new int[n];
			for(int j=0;j<n;j++)
				array[j] = scanner.nextInt();
			System.out.println("1: " + lisLengthUsingOn2(n, array));
			System.out.println("2: " + lisLengthUsingOnlogn(n, array));
		}
		scanner.close();*/
	}

}
