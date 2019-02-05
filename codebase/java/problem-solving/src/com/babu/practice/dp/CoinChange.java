package com.babu.practice.dp;

import java.util.Arrays;
import java.util.Scanner;

public class CoinChange {
	
	/**
	 * Recursive API to get minimum number of coins to make value v
	 * Time Complexity : exponential 2 to the power length of s
	 * @param s
	 * @param i
	 * @param v
	 * @return
	 */
	public static int minCoinRecur(int[] s, int i, int v){
        if(v == 0){
            return 0;
        }
        if(i >= s.length || v < 0){
            return Integer.MAX_VALUE;
        }
        //Consider the ith coin
        int withCoin = minCoinRecur(s, i, v-s[i]);
        //Without considering the ith coin
        int withoutCoin = minCoinRecur(s, i+1, v);
        if(withCoin == Integer.MAX_VALUE && withoutCoin == Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }
        if(withCoin != Integer.MAX_VALUE){
            withCoin += 1;
        }
        return Math.min(withCoin, withoutCoin);
    }
    
	/**
	 * API to get minimum coins needed to make value v
	 * Time Complexity : kn
	 * @param s
	 * @param v
	 * @return
	 */
    public static int minCoinDp(int[] s, int v){
        int[] table = new int[v+1];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[0] = 0;
        for(int i=0;i<s.length;i++){
            for(int j=s[i];j<=v;j++){
                if(table[j-s[i]] != Integer.MAX_VALUE){
                    table[j] = Math.min(table[j], table[j-s[i]] + 1);
                }
            }
        }
        
        //OR:
        /*for(int i=0;i<=v;i++){
            for(int j=0;j<s.length && s[j] <= i;j++){
               if(table[i-s[j]] != Integer.MAX_VALUE) {
                    table[i] = Math.min(table[i], table[i-s[j]] + 1);
                }
            }
        }*/
        return table[v];
    }
    
    public static void main (String[] args) {
    	int[] s = new int[] {2, 1};
    	System.out.println(minCoinDp(s, 7));
    	
		/*Scanner in = new Scanner(System.in);
		int t = Integer.parseInt(in.nextLine());
		while(t > 0) {
    		int[] vn = Arrays.stream(in.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    		int[] s = Arrays.stream(in.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    		//int out = minCoinRecur(s, 0, vn[0]);
    		int out = minCoinDp(s, vn[0]);
    		System.out.println(out == Integer.MAX_VALUE?-1:out);
    		t--;
		}*/
	}
}
