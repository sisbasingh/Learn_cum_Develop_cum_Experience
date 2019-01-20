package com.babu.practice.recursion;

import java.util.ArrayList;
import java.util.List;

public class DecodingMessages {

	/**
	 * Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the number of possible decodings of the given digit sequence
	 * @param num
	 * @param idx
	 * @param curStr
	 * @param results
	 */
	private static void encodeRecur(String num, int idx, String curStr, List<String> results) {
		if (idx >= num.length()) {
			results.add(curStr);
			return;
		}
		if(num.charAt(idx) > '0') {
			encodeRecur(num, idx+1, curStr + (char)(num.charAt(idx) + 16), results);
		}
		if(idx < num.length()-1 && (num.charAt(idx) == '1' || (num.charAt(idx) == '2' && num.charAt(idx+1) < '7'))) {
			int n = Integer.parseInt(num.substring(idx, idx+2));
			encodeRecur(num, idx+2, curStr + (char)(n + 'A' -1), results);
		}
	}
	
	/**
	 * Space and time optimized solution based on fibbonnacci series
	 * @param num
	 * @return
	 */
	private static int encodeCount(String num) {
		//Using fibbonacci method
		int f0 = 1, f1 = 1, fn = 0;
		for(int i=1;i<=num.length();i++) {
		    fn = 0;
			if(num.charAt(i-1) > '0') {
				fn = f1;
			}
			if(i>= 2 && (num.charAt(i-2) == '1' || (num.charAt(i-2) == '2' && num.charAt(i-1) < '7'))) {
				fn += f0;
			}
			f0 = f1;
			f1 = fn;
		}
		
		return fn;
	}
	
	
	private static List<String> encode(String num) {
		List<String> results = new ArrayList<>();
		encodeRecur(num, 0, "", results);
		return results;
	}
	
	
	public static void main(String[] args) {
		String num = "205";
		System.out.println(encodeCount(num));
		/*List<String> results = encode(num);
		for(String str : results) {
			System.out.println(str);
		}*/
	}

}
