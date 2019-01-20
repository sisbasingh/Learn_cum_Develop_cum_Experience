package com.babu.practice.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetWithoutDuplicates {
	
	private static void generatePowerSet(int idx, List<Integer> inputSet, List<Integer> curSet, List<List<Integer>> resultSets) {
		if(idx >= inputSet.size()) {
			resultSets.add(new ArrayList<>(curSet));
			return;
		}
		//Consider current element as part of the result
		curSet.add(inputSet.get(idx));
		generatePowerSet(idx+1, inputSet, curSet, resultSets);
		//Don't consider current element as part of the result
		//Remove all duplicates present in the resultset
		curSet.remove(curSet.size()-1);
		/**
		 * To get all the sets with duplicates just comment the following while loop
		 */
		while(idx < inputSet.size()-1 && inputSet.get(idx) == inputSet.get(idx+1)) {
			idx++;
		}
		generatePowerSet(idx+1, inputSet, curSet, resultSets);
	}
	
	private static List<List<Integer>> generatePowerSet(List<Integer> inputSet){
		List<Integer> curSet = new ArrayList<>();
		List<List<Integer>> resultSets = new ArrayList<>();
		generatePowerSet(0, inputSet, curSet, resultSets);
		return resultSets;
	}
	
	public static void main(String[] args) {
		List<Integer> inputSet = Arrays.asList(new Integer[] {1, 2, 2, 3});
		List<List<Integer>> results = generatePowerSet(inputSet);
		for(List<Integer> set : results) {
			System.out.println(set);
		}
	}

}
