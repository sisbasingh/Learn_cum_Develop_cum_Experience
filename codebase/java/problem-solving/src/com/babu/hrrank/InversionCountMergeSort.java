package com.babu.hrrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class InversionCountMergeSort {
	
	static int merge(int[] A, int low, int mid, int high) {
		int inversionCount = 0;
		int n1 = mid-low+1;
		int n2 = high-mid;
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		for(int i=0;i<n1;i++)
			L[i] = A[low+i];
		for(int i=0;i<n2;i++)
			R[i] = A[mid+1+i];
		
		//Merge
		int i=0,j=0,k=low;
		while(i<n1 && j<n2) {
			if(L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				inversionCount += (n1-i);
				j++;
			}
			k++;
		}
		
		//Check for remaining elements
		while(i<n1) {
			A[k] = L[i];
			i++;
			k++;
		}
		
		while(j<n2) {
			A[k] = R[j];
			j++;
			k++;
		}
		return inversionCount;
	}
	
	static int mergeSort(int[] A, int l, int h) {
		if(l<h) {
			int lic = 0, ric = 0;
			int m = l+(h-l)/2;
			lic = mergeSort(A, l, m);
			ric = mergeSort(A, m+1, h);
			return lic + ric + merge(A, l, m, h);
		}
		return 0;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			System.out.println(mergeSort(array, 0, array.length-1));
			//System.out.println(iCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
