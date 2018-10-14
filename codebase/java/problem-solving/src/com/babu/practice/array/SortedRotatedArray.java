package com.babu.practice.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SortedRotatedArray {
	
	/**
	 * This function is used to search an element in sorted and rotated array
	 * using binary search O(logN)
	 * NOTE: Use the half (either left or right) which is sorted using mid position
	 * @param A
	 * @param element
	 * @return
	 */
	private static int findInSortedRotatedArray(int[] A, int element) {
        int startIndex = 0, endIndex = A.length-1;
        while(startIndex <= endIndex) {
            int mid = startIndex + (endIndex-startIndex)/2;
            if(A[mid] == element)
                return mid;
            /**
             * Check if right half is sorted
             */
            if(A[mid] <= A[endIndex]) {
                if(element > A[mid] && element <= A[endIndex])
                    startIndex = mid+1;
                else
                    endIndex = mid-1;
            } else {  // else the left half is sorted
                if(element >= A[startIndex] && element < A[mid])
                    endIndex = mid-1;
                else
                    startIndex = mid+1;
            }
        }
        return -1;
    }
    
	public static void main (String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			int t = Integer.parseInt(reader.readLine());
			for(int i=0;i<t;i++) {
			    int n = Integer.parseInt(reader.readLine());
			    int[] A = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			    int e = Integer.parseInt(reader.readLine());
			    System.out.println(findInSortedRotatedArray(A, e));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
