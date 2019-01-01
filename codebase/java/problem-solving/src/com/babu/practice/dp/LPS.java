package com.babu.practice.dp;

public class LPS {
	
	private String lpsOn2UsingLCS(String s) {
		/**
		 * Approach:
		 * 1. Reverset the given string say s2
		 * 2. Compute the LCS of s and s2 that is the desired result
		 * NOTE: This approach requires extra space O(n2)
		 */
		return null;
	}
	
	private String lpsOn2SpaceEfficient(String s) {
		if(s.isEmpty()){
            return s;
        }
		int maxLen = 1;
		int maxStart = 0;
		
		for(int i=0;i<s.length()-1;i++) {
			//Odd length LPS
			int j=i-1, k=i+1;
			while(j>=0 && k<s.length() && s.charAt(j) == s.charAt(k)) {
				j--;
				k++;
			}
			if(k-j-1 > maxLen) {
				maxLen = k-j-1;
				maxStart = j+1;
			}
			
			//Even length LPS
			j = i;
			k = i+1;
			while(j>=0 && k<s.length() && s.charAt(j) == s.charAt(k)) {
				j--;
				k++;
			}
			if(k-j-1 > maxLen) {
				maxLen = k-j-1;
				maxStart = j+1;
			}
		}
		
		return s.substring(maxStart, maxStart+maxLen);
	}
	
	public static void main(String[] args) {
		LPS lps = new LPS();
		System.out.println(lps.lpsOn2SpaceEfficient("aaaabbaa"));
	}

}
