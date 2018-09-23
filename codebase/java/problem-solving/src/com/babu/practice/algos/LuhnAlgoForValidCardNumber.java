package com.babu.practice.algos;

import java.util.Scanner;

public class LuhnAlgoForValidCardNumber {
	private static boolean isValidCard(long cardNum){
		char[] cardArr = String.valueOf(cardNum).toCharArray();
		int sum = 0;
		boolean isSecondDigit = false;
		for(int i=cardArr.length-1;i>=0;i--){
			int thisDigit = cardArr[i] - '0';
			if(isSecondDigit){
				thisDigit *= 2;
			}
			sum += thisDigit/10;
			sum += thisDigit%10;
			isSecondDigit = !isSecondDigit;
		}
		if(sum%10 == 0)
			return true;
		return false;
	}
	
	public static void main(String args[]){
		//System.out.println(Integer.MAX_VALUE + "\n" + Long.MAX_VALUE);
		long beginRange, endRange;
		Scanner scanner = new Scanner(System.in);
		beginRange = scanner.nextLong();
		endRange = scanner.nextLong();
		for(long i=beginRange;i<=endRange;i++){
			if(isValidCard(i))
				System.out.println(i);
		}
	}
}
