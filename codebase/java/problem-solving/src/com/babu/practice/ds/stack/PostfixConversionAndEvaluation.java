package com.babu.practice.ds.stack;

import java.util.Stack;

public class PostfixConversionAndEvaluation {
	
	private static int getPrecedence(char ch) {
		switch(ch) {
		//Unary ops have most precedence
		case '^':
			return 3;
		//followed by binary multiplication/division/remainder
		case '*':
		case '/':
		case '%':
			return 2;
		//followed by binary addition/subtraction
		case '+':
		case '-':
			return 1;
		//Invalid operator
		default:
			return -1;
		}
	}
	
	private static String infixToPostfix(String exp) {
		StringBuffer sbr = new StringBuffer("");
		Stack<Character> stack = new Stack<>();
		for(int i=0;i<exp.length();i++) {
			char ch = exp.charAt(i);
			if(Character.isDigit(ch))
				sbr.append(ch);
			else if(ch == ')') {
				while(!stack.isEmpty() && stack.peek() != '(')
					sbr.append(stack.pop());
				//Check for invalid expression
				if(stack.peek() != '(')
					return null;
				else
					stack.pop();
			} else if(ch == '(')
				stack.push(ch);
			else {
				while(!stack.isEmpty() && getPrecedence(ch) <= getPrecedence(stack.peek()))
					sbr.append(stack.pop());
				stack.push(ch);
			}
		}
		
		while(!stack.isEmpty())
			sbr.append(stack.pop());
		return sbr.toString();
	}
	
	private static long applyOperation(char op, long op1, long op2) {
		switch(op) {
		case '^':
			return (long) Math.pow(op2, op1);
		case '*':
			return op2*op1;
		case '/':
			return op2/op1;
		case '%':
			return op2%op1;
		case '+':
			return op2+op1;
		case '-':
			return op2-op1;
		default:
			throw new IllegalArgumentException("Illegal operation, not supported currently");
		}
	}
	
	private static long evaluatePostFix(String exp) {
		Stack<Long> digits = new Stack<>();
		for(int i=0;i<exp.length();i++) {
			char ch = exp.charAt(i);
			if(Character.isDigit(ch))
				digits.push((long) ch - '0');
			else {
				digits.push(applyOperation(ch, digits.pop(), digits.pop()));
			}
		}
		if(digits.size() > 1)
			throw new IllegalArgumentException("Invalid Expression!");
		return digits.pop();
	}
	
	public static void main(String[] args) {
		String exp = new String("9+5*4/5+7");
		System.out.println(exp);
		String postfixExp = infixToPostfix(exp);
		System.out.println(postfixExp);
		System.out.println(evaluatePostFix(postfixExp));
	}

}
