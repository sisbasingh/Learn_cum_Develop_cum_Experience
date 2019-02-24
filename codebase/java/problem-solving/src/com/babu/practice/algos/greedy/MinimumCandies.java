package com.babu.practice.algos.greedy;

import java.util.ArrayList;
import java.util.Arrays;

public class MinimumCandies {
	
	/**
	 * There are N children standing in a line. Each child is assigned a rating value.
	 * You are giving candies to these children subjected to the following requirements:
	 * 1. Each child must have at least one candy.
	 * 2. Children with a higher rating get more candies than their neighbors.
	 * @param a
	 * @return
	 */
	 public int candy(ArrayList<Integer> a) {
	        int[] c = new int[a.size()];
	        Arrays.fill(c, 1);
	        for(int i=1;i<a.size();i++) {
	            if(a.get(i) > a.get(i-1)) {
	                c[i] = c[i-1] + 1;
	            }
	        }
	        
	        for(int i=a.size()-2;i>=0;i--) {
	            if(a.get(i) > a.get(i+1)) {
	                c[i] = Math.max(c[i+1]+1, c[i]);
	            }
	        }
	        
	        int minCandies = 0;
	        for(int n : c) {
	            minCandies += n;
	        }
	        
	        return minCandies;
	    }

}
