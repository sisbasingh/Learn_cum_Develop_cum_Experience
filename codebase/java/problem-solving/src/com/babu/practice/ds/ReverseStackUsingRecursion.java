package com.babu.practice.ds;

import java.util.Stack;

public class ReverseStackUsingRecursion {
	
	private static void reverseStack(Stack<Integer> s){
		if(s.isEmpty())
			return;
		else{
			int data = s.peek();
			s.pop();
			reverseStack(s);
			insertToBottom(s, data);
		}
	}
	
	private static void insertToBottom(Stack<Integer> s, int element){
		if(s.isEmpty())
			s.push(element);
		else{
			int topElement = s.peek();
			s.pop();
			insertToBottom(s, element);
			s.push(topElement);
		}
	}
	
	public static void main(String argss[]){
		Stack<Integer> s = new Stack<Integer>();
		s.push(5);
		s.push(4);
		s.push(3);
		s.push(2);
		s.push(1);
		System.out.println("Before Reversal: \n" + s.toString());
		reverseStack(s);
		System.out.println("After Reversal: \n" + s.toString());
	}

}
