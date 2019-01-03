package com.babu.practice.ds.stack;

import java.util.Stack;

public class StackWithMinOp <T extends Comparable<T>> implements Comparable<T> {
	
	Stack<T> main_stack;
	Stack<T> min_stack;
	
	public static void main(String[] args) {
		StackWithMinOp<Integer> s = new StackWithMinOp<>();
		int[] inputs = { 5, 2, 7, 4, 6, 3, 1, 9, 8 };
		for(int i : inputs) {
			s.push(i);
			System.out.println(s.getMin());
		}
	}

	public StackWithMinOp() {
		main_stack = new Stack<T>();
		min_stack = new Stack<T>();
	}
	
	public void push(T e) {
		main_stack.push(e);
		if(min_stack.isEmpty() || min_stack.peek().compareTo(e) >= 0) {
			min_stack.push(e);
		}
	}
	
	public T pop() {
		if(main_stack.isEmpty()) {
			return null;
		} else {
			T item = main_stack.pop();
			if(min_stack.peek().equals(item)) {
				min_stack.pop();
			}
			return item;
		}
	}
	
	public T getMin() {
		return min_stack.peek();
	}

	@Override
	public int compareTo(T o) {
		return this.compareTo(o);
	}

}
