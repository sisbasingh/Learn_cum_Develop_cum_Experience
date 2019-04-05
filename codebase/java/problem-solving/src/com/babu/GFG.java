package com.babu;

/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    
    private Map<Integer, String> hmap;
    
    public GFG() {
        hmap = new HashMap<>();
        hmap.put(2, "abc");
        hmap.put(3, "def");
        hmap.put(4, "ghi");
        hmap.put(5, "jkl");
        hmap.put(6, "mno");
        hmap.put(7, "pqrs");
        hmap.put(8, "tuv");
        hmap.put(9, "wxyz");
        hmap = Collections.unmodifiableMap(hmap);
    }
    
    public void printWords(int[] nums, int curIndex, StringBuilder curWord) {
        if(curIndex == nums.length) {
            System.out.print(curWord.toString() + " ");
            return;
        }
        if(hmap.containsKey(nums[curIndex])) {
            String word = hmap.get(nums[curIndex]);
            for(int i=0;i<word.length();i++) {
                curWord.append(word.charAt(i));
                printWords(nums, curIndex+1, curWord);
                curWord.deleteCharAt(curWord.length()-1);
            }
        }
    }
    
	public static void main (String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		GFG gfg = new GFG();
		try {
		    int t = Integer.parseInt(br.readLine());
		while(t > 0) {
		    int n = Integer.parseInt(br.readLine());
		    int[] nums = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		    StringBuilder sbr = new StringBuilder("");
		    gfg.printWords(nums, 0, sbr);
		    System.out.println();
		    t--;
		}
		} catch(Exception e) {
		    e.printStackTrace();
		}
	}
}
