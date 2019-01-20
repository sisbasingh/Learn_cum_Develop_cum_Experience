package com.babu.practice.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllPermutationsOfSizeK {
	
	private static void generateAllPermuatations(int idx, List<Integer> input, List<List<Integer>> results) {
		if(idx == input.size()) {
			results.add(new ArrayList<>(input));
			return;
		}
		for(int i=idx;i<input.size();i++) {
			if(!skipIndex(input, idx, i)) {
				Collections.swap(input, idx, i);
				generateAllPermuatations(idx+1, input, results);
				Collections.swap(input, idx, i);
			}
		}
	}
	
	/**
	 * API to avoid duplicates in permutations
	 * @param input
	 * @param i
	 * @param j
	 * @return
	 */
	private static boolean skipIndex(List<Integer> input, int i, int j) {
		for(int k=i;k<j;k++) {
			if(input.get(k) == input.get(j)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * API to get all subsets of size K
	 * @param idx
	 * @param input
	 * @param curSet
	 * @param results
	 * @param k
	 */
	private static void generateSubsetOfSizeK(int idx, List<Integer> input, List<Integer> curSet, List<List<Integer>> results, int k) {
		if(curSet.size() == k) {
			results.add(new ArrayList<>(curSet));
			return;
		}
		int remainingSize = k-curSet.size();
		for(int i=idx;i<input.size() && remainingSize <= input.size()-i+1; i++) {
			curSet.add(input.get(i));
			generateSubsetOfSizeK(i+1, input, curSet, results, k);
			curSet.remove(curSet.size()-1);
			/**
			 * To avoid sets with duplicate/same elements
			 */
			while(i < input.size()-1 && input.get(i) == input.get(i+1)) {
				i++;
			}
		}
	}
	
	private static List<List<Integer>> generateAllPermutationsOfSizeK(List<Integer> input, int k) {
		List<List<Integer>> results = new ArrayList<>();
		if(k > input.size()) {
			return results;
		}
		List<Integer> curSet = new ArrayList<>();
		generateSubsetOfSizeK(0, input, curSet, results, k);
		List<List<Integer>> perms = new ArrayList<>();
		
		for(List<Integer> set : results) {
			generateAllPermuatations(0, set, perms);
		}
		
		return perms;
	}
	
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(new Integer[] {1, 2, 2} );
		List<List<Integer>> results = generateAllPermutationsOfSizeK(input, 2);
		
		for(List<Integer> result : results) {
			System.out.println(result);
		}
	}

}
