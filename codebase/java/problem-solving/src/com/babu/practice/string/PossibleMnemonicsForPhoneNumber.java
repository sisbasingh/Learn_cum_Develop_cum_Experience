package com.babu.practice.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PossibleMnemonicsForPhoneNumber {
	
	/**
	 * API to print all possible Mnemonics for a given phone number using phone number as a character array
	 * @param phoneNumber
	 * @param curIndex
	 * @param keypad
	 */
	private static void printAllPossibleMnemonics(char[] phoneNumber, int curIndex, Map<Character, char[]> keypad) {
		if(curIndex >= phoneNumber.length) {
			System.out.println(Arrays.toString(phoneNumber));
		} else {
			char[] possibleChars = keypad.get(phoneNumber[curIndex]);
			for(int i=0;i<possibleChars.length;i++) {
				char[] ph = Arrays.copyOf(phoneNumber, phoneNumber.length);
				ph[curIndex] = possibleChars[i];
				printAllPossibleMnemonics(ph, curIndex+1, keypad);
			}
		}
	}
	
	/**
	 * API to print all possible Mnemonics for a given phone number using phone number as as string
	 * @param phoneNumber
	 * @param curIndex
	 * @param keypad
	 */
	private static void printAllPossibleMnemonics(String phoneNumber, int curIndex, Map<Character, char[]> keypad) {
		if(curIndex >= phoneNumber.length()) {
			System.out.println(phoneNumber);
		} else {
			char[] possibleChars = keypad.get(phoneNumber.charAt(curIndex));
			for(int i=0;i<possibleChars.length;i++) {
				printAllPossibleMnemonics(phoneNumber.substring(0, curIndex) + possibleChars[i] + phoneNumber.substring(curIndex+1, phoneNumber.length()),  curIndex+1, keypad);
			}
		}
	}
	
	private static Map<Character, char[]> getFeaturePhoneKeyPad() {
		Map<Character, char[]> keypad = new HashMap<>();
		keypad.put('1', new char[] {'1'});
		keypad.put('2', new char[] {'A', 'B', 'C'});
		keypad.put('3', new char[] {'D', 'E', 'F'});
		keypad.put('4', new char[] {'G', 'H', 'I'});
		keypad.put('5', new char[] {'J', 'K', 'L'});
		keypad.put('6', new char[] {'M', 'N', 'O'});
		keypad.put('7', new char[] {'P', 'Q', 'R', 'S'});
		keypad.put('8', new char[] {'T', 'U', 'V'});
		keypad.put('9', new char[] {'W', 'X', 'Y', 'Z'});
		keypad.put('*', new char[] {'*'});
		keypad.put('0', new char[] {'0'});
		keypad.put('#', new char[] {'#'});
		return keypad;
	}
	
	public static void main(String[] args) {
		Map<Character, char[]> keypad = getFeaturePhoneKeyPad();
		char[] ph = new char[] {'2', '2', '7', '6', '6', '9', '6'};
		//printAllPossibleMnemonics(ph, 0, keypad);
		printAllPossibleMnemonics("2276696", 0, keypad);
	}

}
