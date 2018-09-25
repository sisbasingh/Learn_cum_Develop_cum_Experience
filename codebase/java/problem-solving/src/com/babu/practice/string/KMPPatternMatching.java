package com.babu.practice.string;

import java.util.Arrays;

public class KMPPatternMatching {
	
	private static int[] computeLPS(char[] pattern) {
		int len = 0;
		int i = 1;
		int[] lps = new int[pattern.length];
		lps[0] = 0;
		while(i < pattern.length) {
			if(pattern[i] == pattern[len]) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if(len > 0)
					len = lps[len-1];
				else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}
	
	/**
	 * This is the implementation of KMP pattern matching algorithm
	 * the worst case time complexity is O(n), where n is the length of text to be searched for 
	 * the pattern
	 * @param text
	 * @param pattern
	 */
	private static void KMPStringSearch(char[] text, char[] pattern) {
		int m = pattern.length;
		int n = text.length;
		
		int i = 0, j = 0;
		int[] lps = computeLPS(pattern);
		
		System.out.println("LPS: " + Arrays.toString(lps));
		
		while(i < n) {
			if(text[i] == pattern[j]) {
				i++;
				j++;
			}
			//Chekc if match found
			if(j == m) {
				System.out.println("Match found from: " + (i-j) + " to: " + (i-1));
				j = lps[j-1];
			} else if(i < n && text[i] != pattern[j]) {
				if(j != 0)
					j = lps[j-1];
				else
					i++;
			}
		}
	}
	
	public static void main(String[] args) {
		String text = "ABABDABACDABABCABAB";
		String pat = "ABABCABAB";
		KMPStringSearch(text.toCharArray(), pat.toCharArray());
	}

}
