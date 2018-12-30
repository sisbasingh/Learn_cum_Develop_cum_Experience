package com.babu.exceptions;

public class GetMethodName {
	
	private void testThrow() throws Exception {
		throw new Exception("My Test");
	}
	
	private void testThrow1() throws Exception {
		testThrow();
	}
	
	public static void main(String[] args) {
		GetMethodName gmn = new GetMethodName();
		try {
			gmn.testThrow1();
		} catch (Exception e) {
			System.out.println("Method Name is: " + e.getStackTrace()[e.getStackTrace().length-1].getMethodName());
			e.printStackTrace();
		}
	}

}
