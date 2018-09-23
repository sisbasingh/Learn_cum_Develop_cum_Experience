package com.babu.practice.java.traps;

public class MethodOverloading {
	
	public long sum(int a, long b) {
		return a+b;
	}
	
	public long sum(long a, int b) {
		return a+b;
	}
	
	public static void main(String [] args) {
		MethodOverloading mo = new MethodOverloading();
		/**
		 * IMP : Here the following call to sum is ambiguous as it can be applied to call any of the above methods
		 * as Java by default promotes primitive types to get the appropriate method
		 * 
		 * TODO: Also check for type promotion in Java
		 */
		//mo.sum(20, 20);
	}

}
