package com.babu.practice.algos.greedy;

public class MinJumpsSeatingArrangement {
	
	/**
	 * There is a row of seats. Assume that it contains N seats adjacent to each other. 
	 * There is a group of people who are already seated in that row randomly. i.e. some are sitting together & some are scattered.
	 * An occupied seat is marked with a character 'x' and an unoccupied seat is marked with a dot ('.')
	 * Now your target is to make the whole group sit together i.e. next to each other, 
	 * without having any vacant seat between them in such a way that the total number of hops or jumps to move them should be minimum.
	 * Return minimum value % MOD where MOD = 10000003
	 * 
	 * Approach: find the median person (who is having equal no. of persons in his left and right)
	 * then make seat all the persons to his left to seat adjacent to him in left and all the right side persons to sit 
	 * right side adjacent to him and each other
	 * @param s
	 * @return
	 */
	public static int minJumps(String s) {
        final int MOD = 10000003;
        
        if(s.isEmpty()){
            return 0;
        }
        
        /**
         * count number of persons and position of first person in the row
         */
        int pstart = -1, pcount = 0;
        int i = 0;
        while(i<s.length()) {
            if(s.charAt(i) == 'x') {
                if(pstart == -1) {
                    pstart = i;
                }
                pcount++;
            }
            i++;
        }
        /**
         * if 0 or 1 person is there
         */
        if(pcount <= 1) {
            return 0;
        }
        /**
         * person number of person who is at median of all the persons
         */
        int midPersonNumber = (pcount+1)/2;
        
        int j = pstart;
        while(midPersonNumber > 0) {
            if(s.charAt(j) == 'x') {
                midPersonNumber--;
            }
            j++;
        }
        
        midPersonNumber = j-1;
        int minJumps = 0;
        /**
         * Count jumps in left side of median person
         */
        int emptySlots = 0;
        i = midPersonNumber-1;
        while(i>=0) {
            if(s.charAt(i) == 'x') {
                minJumps += emptySlots;
                minJumps %= MOD;
            } else {
                emptySlots++;
            }
            i--;
        }
        
        /**
         * Count jumps in right side of median person
         */
        emptySlots = 0;
        i = midPersonNumber+1;
        while(i < s.length()) {
            if(s.charAt(i) == 'x') {
                minJumps += emptySlots;
                minJumps %= MOD;
            } else {
                emptySlots++;
            }
            i++;
        }
        
        return minJumps;
    }
	
	public static void main(String[] args) {
		String str = new String("...x...xx....x...");
		System.out.println(minJumps(str));
	}

}
