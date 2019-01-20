package com.babu.practice.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermutationsWithoutDuplicates {
	
	private static boolean skipIndex(List<Integer> input, int s, int curr) {
		for(int i=s;i<curr;i++) {
			if(input.get(i) == input.get(curr))
				return true;
		}
		return false;
	}
	
	private static void generatePermuation(int idx, List<Integer> input, List<List<Integer>> results) {
		if(idx == input.size()) {
			results.add(new ArrayList<>(input));
			return;
		}
		for(int i=idx;i<input.size();i++) {
			if(!skipIndex(input, idx, i)) {
				Collections.swap(input, i, idx);
				generatePermuation(idx+1, input, results);
				Collections.swap(input, i, idx);
			}
		}
	}
	
	private static List<List<Integer>> generatePermutations(List<Integer> input) {
		List<List<Integer>> results = new ArrayList<>();
		generatePermuation(0, input, results);
		return results;
	}
	
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(new Integer[] {1, 2, 2, 1} );
		List<List<Integer>> results = generatePermutations(input);
		for(List<Integer> perm : results) {
			System.out.println(perm);
		}
	}

}
