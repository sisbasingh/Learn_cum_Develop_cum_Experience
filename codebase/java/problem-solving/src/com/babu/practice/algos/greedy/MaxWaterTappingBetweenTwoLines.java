package com.babu.practice.algos.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MaxWaterTappingBetweenTwoLines {
	
	/*
	 * NOTEME : The time complexity is O(n)
	 * NOTEME : Variant: Maximum area between horizontal bars (graph) with heights given
	 */
	private static int maxWaterTapped(int [] heights, int n) {
		int start = -1, end = -1, maxWaterTapped = 0;
		int i = 0, j = n-1;
		while(i<j) {
			int curWaterTapped = (j-i) * (Math.min(heights[i], heights[j]));
			if(maxWaterTapped < curWaterTapped) {
				maxWaterTapped = curWaterTapped;
				start = i;
				end = j;
			}
			
			if(heights[i] == heights[j]) {
				i++; j--;
			} else if(heights[i] < heights[j]) 
				i++;
			else 
				j--;
		}
		System.out.println("Start: " + start + "\tEnd: " + end);
		return maxWaterTapped;
	}
	
	public static void main(String [] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(br.readLine());
			for(int i=1;i<=t;i++) {
				StringTokenizer tokenizer = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(tokenizer.nextToken());
				int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
				System.out.println(maxWaterTapped(array, n));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
