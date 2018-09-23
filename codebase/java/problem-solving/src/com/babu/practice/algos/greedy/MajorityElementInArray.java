package com.babu.practice.algos.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MajorityElementInArray {
	
	private static String majorityElement(int [] A, int n) {
		if(n == 0) {
			return "NO Majority Element";
		}
		int majorElement = A[0], count = 1;
		for(int i=1;i<n;i++) {
			if(A[i] == majorElement)
				count++;
			else
				count--;
			if(count == 0) {
				majorElement = A[i];
				count = 1;
			}
		}
		
		//IMP : This is important
		//Check whether the majority element really exists or not
		count = 0;
		for(int i=0;i<n;i++) {
			if(A[i] == majorElement)
				count++;
		}
		return count>n/2?majorElement + "" : "NO Majority Element";
	}
	
	
	public static void main(String [] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(br.readLine());
			for(int i=1;i<=t;i++) {
				StringTokenizer tokenizer = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(tokenizer.nextToken());
				int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
				System.out.println(majorityElement(array, n));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
