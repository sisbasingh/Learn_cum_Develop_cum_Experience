package com.babu.practice.string;

import java.util.Arrays;

public class Permutations {
	
	private static void swap(char[] A, int i, int j) {
		char c = A[i];
		A[i] = A[j];
		A[j] = c;
	}
	
	private static void permuteUnOrdered(char[] s, int i) {
		if(i == s.length) {
			System.out.println(Arrays.toString(s));
		} else {
			for(int j=i;j<s.length;j++) {
				swap(s, i, j);
				permuteUnOrdered(s, i+1);
				swap(s, i, j);
			}
		}
	}
	
	private static void permuteUnOrdered2(String s, String prefix) {
		if(s.length() == 0) {
			System.out.println(prefix);
			return;
		}
		for(int i=0;i<s.length();i++) {
			permuteUnOrdered2(s.substring(0, i) + s.substring(i+1), s.substring(i,i+1) + prefix);
		}
	}
	
	/**
	 * API to get the next greater char index (ceil char)
	 * @param A
	 * @param c
	 * @param l
	 * @param h
	 * @return
	 */
	private static int ceilIndex(char[] A, char c, int l, int h) {
		int ceilIndex = l;
		for(int i=l+1;i<=h;i++) {
			if(A[i] > c && A[i] < A[ceilIndex]) {
				ceilIndex = i;
			}
		}
		return ceilIndex;
	}
	
	private static void permuteSortedOrder(char[] s) {
		Arrays.sort(s);
		int i;
		boolean isFinished = false;
		while(!isFinished) {
			System.out.println(Arrays.toString(s));
			//Find the char index to be swaped
			//Find rightmost char who is greater then its left char
			for(i=s.length-2;i>=0;i--) {
				if(s[i] < s[i+1])
					break;
			}
			if(i == -1)
				isFinished = true;
			else {
				//Find the ceil of s[i] from i+1 to s.lenght to do the swap
				int ceilIndex = ceilIndex(s, s[i], i+1, s.length-1);
				//Swap the s[i] with s[ceilIndex]
				swap(s, i, ceilIndex);
				//Reverse the rest of the char array
				int l = i+1;
				int h = s.length-1;
				while(l<h) {
					swap(s, l++, h--);
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		char[] a = {'B', 'C', 'A'};
		//permuteUnOrdered(a, 0);
		//permuteSortedOrder(a);
		permuteUnOrdered2("abcd", "");
	}

}
