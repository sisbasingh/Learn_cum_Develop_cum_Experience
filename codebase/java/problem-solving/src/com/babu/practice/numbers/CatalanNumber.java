package com.babu.practice.numbers;

public class CatalanNumber {
	
	/*
	 * Formula:
	 * https://wikimedia.org/api/rest_v1/media/math/render/svg/58374aa2b2e2c016a5b313e2bbd59940a2e1a5f9
	 * 
	 * Applications: 
	 * https://en.wikipedia.org/wiki/Catalan_number#Applications_in_combinatorics
	 * 
	 * Can be computed easily using formula: Cn = 2nCn/n+1 or using DP 
	 * 
	 * Total number of structurally unique BSTs on 1..N is = Cn (Catalan Number)
	 * 
	 * Total number of structurally unique BTs on 1..N is = Cn * !n
	 */
	
	/**
	 * Recursive function to count total number of structurally unique BSTs from 1..N
	 * @param low -> the lower number of the range, usually 1
	 * @param high -> the upper number of the range, usually N
	 * @return total number of structurally unique BSTs possible from low..high
	 */
	private static int countBsts(int low, int high) {
	    if(low >= high) {
	        return 1;
	    }
	    int sum = 0;
	    for(int i=low;i<=high;i++) {
	        int lcount = countBsts(low, i-1);
	        int rcount = countBsts(i+1, high);
	        sum += lcount*rcount;
	    }
	    return sum;
	}

}
