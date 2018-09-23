package com.babu.practice.java.traps;

public class CommonJavaTraps {

	/**
	 * This function fails for negative numbers to identify correctly for oddness
	 * because of the remainder(%) operator behavior.
	 * @param i
	 * @return boolean
	 */
	public static boolean isOdd(int i) {
		System.out.println(i%2);
		return i%2 == 1;
	}
	
	/**
	 * This function prints incorrect long devision because of overflows, as the right side value is assumed to be int
	 * but while multiplication with other values it overflows
	 * IMP : the promotion to long is done only when computation of right side value finishes
	 * to avoid this the solution can be like this:
	 * final long MICROS_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
	 * final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;
	 */
	public static void longDivision() {
		final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
		System.out.println(MICROS_PER_DAY/MILLIS_PER_DAY);
	}
	
	/**
	 * This function checks for String interning features of JVM, where two constant strings with constant values will
	 * refer the same object, irrespective of program, this is internal feature of JVM to optimize memory
	 * in the following demonstration even though the the two string literals are different object but from JVM's perspective
	 * both are same and referring to same object until and unless their value changes
	 */
	public static void stringInterningCheck() {
		final String s1 = "Hello String";
		final String s2 = "Hello String";
		System.out.println(s1==s2);
	}
	
	public static void main(String [] args) {
		/*int n = -3;
		System.out.println("is Number " + n + " odd: " + isOdd(n));*/
		
		//longDivision();
		
		stringInterningCheck();
	}
	
}
