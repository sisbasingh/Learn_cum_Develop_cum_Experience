package com.babu.practice.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class SortingUtil {
	
	private static void swap(int[] A, int i, int j) {
		/*A[i] = A[i] ^ A[j];
		A[j] = A[i] ^ A[j];
		A[i] = A[i] ^ A[j];*/
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
	
	private static int partition(int[] A, int low, int high) {
		int pivot = A[high];
		int i = low-1;
		int j;
		for(j=low;j<high;j++) {
			if(A[j] < pivot) {
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i+1, j);
		return i+1;
	}
	
	private static void quicksort(int[] A, int low, int high) {
		if(low < high) {
			int pi = partition(A, low, high);
			quicksort(A, low, pi-1);
			quicksort(A, pi+1, high);
		}
	}
	
	private static void merge(int[] A, int low, int mid, int high) {
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
	}
	
	private static void mergesort(int[] A, int low, int high) {
		if(low < high) {
			int mid = low + (high-low)/2;
			mergesort(A, low, mid);
			mergesort(A, mid+1, high);
			merge(A, low, mid, high);
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int[] array = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			//quicksort(array, 0, array.length-1);
			mergesort(array, 0, array.length-1);
			System.out.println(Arrays.toString(array));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
