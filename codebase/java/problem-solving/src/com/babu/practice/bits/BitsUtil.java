package com.babu.practice.bits;

import java.util.Scanner;

public class BitsUtil {
	
	private static long getRightmostSetBit(long n) {
		int k = (int)(Math.log(n)/Math.log(2));
		System.out.println("K: " + k);
		return (1L<<k);
	}
	
	/**
	 * This API computes the closest number (greater or lesser) to given number with same number of set bits
	 * IMP : to get the rightmost set bit in x there are two ways: 
	 * 1-> x&~(x-1) or 
	 * 2-> x&-x
	 * @param n
	 * @return
	 */
	private static long closestIntWithSameSetBits(long n) {
		System.out.println("Before: " + Long.toBinaryString(n));
		long firstOnBit = n&~(n-1);
		long firstOffBit = ~n & ~(~n-1);
		//Flip the bits
		n ^= firstOnBit | firstOffBit;
		System.out.println("After:  " + Long.toBinaryString(n));
		return n;
	}
	
	private static long swapBits(long n, int i, int j) {
		if(((n>>>i)&1) == ((n>>>j)&1)) {
			//Both bits are same no need to swap
			return n;
		} else {
			return n^ ((1L<<i) | (1L<<j));
		}
	}
	
	private static void test() {
		System.out.println("Int size: " + Integer.SIZE);
		System.out.println("Float size: " + Float.SIZE);
		System.out.println("Double size: " + Double.SIZE);
		System.out.println("Long size: " + Long.SIZE);
	}
	
	public static void main(String[] args) {
		/*Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		System.out.println("N: " + n + "\t Closeset to N: " + closestIntWithSameSetBits(n));*/
		
		//test();
		System.out.println(getRightmostSetBit(74));
	}

}
