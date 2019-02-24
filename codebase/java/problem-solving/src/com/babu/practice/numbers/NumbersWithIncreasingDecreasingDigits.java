package com.babu.practice.numbers;

public class NumbersWithIncreasingDecreasingDigits {
	/**
	 * API to print numDigits numbers (in sorted order) in which digits are in decreasing order
	 * @param startNum
	 * @param lastDigit
	 * @param numDigits
	 */
	public static void printDecreasingDigitNumbers(int startNum, int lastDigit, int numDigits) {
		if(numDigits == 0) {
			System.out.println(startNum);
			return;
		}
		
		/**
		 * For strictly decreasing use i <= lastDigit-1 instead lastDigit
		 */
		for(int i=0;i<=lastDigit;i++) {
			printDecreasingDigitNumbers(startNum*10 + i, i, numDigits-1);
		}
	}
	
	/**
	 * API to print 1 to numDigits numbers (in sorted order) in which digits are in increasing order
	 * @param startNum
	 * @param lastDigit
	 * @param numDigits
	 */
	public static void printIncreasingDigitNumbers(int startNum, int lastDigit, int numDigits) {
		if(numDigits == 0) {
			System.out.println(startNum);
			return;
		}
		
		/**
		 * For strictly increasing start i from lastDigit+1 instead of lastDigit
		 */
		for(int i=lastDigit;i<10;i++) {
			printIncreasingDigitNumbers(startNum*10 + i, i, numDigits-1);
		}
	}
	
	/**
	 * API to computer number of digits in a number n with base base
	 * @param n
	 * @param base
	 * @return
	 */
	public static int numberOfDigitsInNumber(long n, int base) {
		return (int) Math.ceil((Math.log(n)/Math.log(base)));
	}
	
	public static void main(String[] args) {
		/**
		 * Calling Decreasing digit number api initial inputs
		 */
	    //printDecreasingDigitNumbers(0, 9, 3);
		/**
		 * Calling Increasing digit number api initial inputs
		 */
		//printIncreasingDigitNumbers(0, 0, 3);
		
		System.out.println(numberOfDigitsInNumber(1234567890, 10));
	}

}
