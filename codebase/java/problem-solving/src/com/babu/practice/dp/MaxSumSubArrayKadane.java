package com.babu.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaxSumSubArrayKadane {
	
	/**
	 * This API is used to print max sum of contiguous sub array with max sum and elements of the sub array
	 * It also handles negative elements in the array
	 * @param A
	 * @param n
	 */
	private static void maxSumSubArrayKadane(int[] A, int n) {
		if(n<=0) {
			//Array is empty, no max sum subarray
			return;
		}
		else if(n==1) {
			System.out.println("Max sum is: " + A[0]);
			return;
		}
		int currSum = A[0], maxSumSoFar = A[0];
		int prevStartIndex = 0, startIndex = 0, endIndex = 0;
		for(int i=1;i<n;i++) {
			if(A[i] < currSum+A[i]) {
				//Continue adding to sub array sum
				currSum += A[i];
			} else {
				//break point, start new sub array sum
				currSum = A[i];
				prevStartIndex = i;
			}
			
			if(maxSumSoFar < currSum) {
				//update start and end indexes
				maxSumSoFar = currSum;
				startIndex = prevStartIndex;
				endIndex = i;
			}
		}
		
		//Print results
		System.out.println("Max sub array sum is: " + maxSumSoFar);
		System.out.print(Arrays.toString(Arrays.copyOfRange(A, startIndex, endIndex+1)));
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			maxSumSubArrayKadane(array, array.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
