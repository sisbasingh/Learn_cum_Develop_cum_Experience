package com.babu;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class NumToWords {
	
	private String ones[], tens[];
	private LinkedHashMap<Integer, String> units;
	
	public static void main(String argss[]){
		NumToWords ntw = new NumToWords();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		ntw.toWords(n);
	}
	
	public NumToWords(){
		ones = new String[] { "", " one", " two", " three", " four", " five", " six", " seven", " eight",
				" Nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", 
				" sixteen", " seventeen", " eighteen", " nineteen" };
		tens = new String[] { " ", " ", " twenty", " thirty", " forty", " fifty", " sixty", 
				" seventy", " eighty", " ninety" };
		units = new LinkedHashMap<Integer, String>();
		units.put(10000000, "crore");
		units.put(100000, "lakh");
		units.put(1000, "thousand");
		units.put(100, "hundred");
	}
	
	public void toWords(int n){
		if(n < 0)
			System.out.println("Negative Number");
		else if(n == 0)
			System.out.println("Zero");
		else{
			for(Entry<Integer, String> e : units.entrySet()){
				int unit = e.getKey();
				String unitName = e.getValue();
				if(unit > 100){
					if(n%unit > 0 && n%unit < 100)
						toWordsUtil((n/unit)%100, unitName + " and");
					else
						toWordsUtil((n/unit)%100, unitName);
				}
				else {
					if(n%unit > 0)
						toWordsUtil((n/unit)%10, unitName + " and");
					else
						toWordsUtil((n/unit)%10, unitName);
				}
			}
			toWordsUtil(n%100, "");
		}
	}
	
	private void toWordsUtil(int number, String word){	
		if(number > 19)
			System.out.print(tens[number/10] + ones[number%10]);
		else
			System.out.print(ones[number]);
		if(number > 0)
			System.out.print(" " + word);
	}

}
