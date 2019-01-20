package com.babu.practice.recursion;

public class MatchParenthesis {
	
	private static void generateParens(int lcount, int rcount, String paren) {
		if(lcount == 0 && rcount == 0) {
			System.out.println(paren);
			return;
		}
		
		if(lcount > 0) {
			generateParens(lcount-1, rcount, paren + "(");
		}
		if(lcount < rcount) {
			generateParens(lcount, rcount-1, paren + ")");
		}
	}
	
	public static void main(String[] args) {
		String paren = "";
		generateParens(3, 3, "");
	}

}
