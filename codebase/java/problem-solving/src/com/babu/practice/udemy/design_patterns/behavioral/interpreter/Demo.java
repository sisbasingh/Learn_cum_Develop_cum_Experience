package com.babu.practice.udemy.design_patterns.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

class ExpressionProcessor {
	public Map<Character, Integer> variables = new HashMap<>();

	public int calculate(String expression) {
		// Check for valid expression
		if (expression.startsWith("+") || expression.startsWith("-") || expression.endsWith("+")
				|| expression.endsWith("-"))
			return 0;
		int result = 0;
		char operation = ' ';
		int startIndex = 0;
		for (int i = 0; i < expression.length(); i++) {
			char currentChar = expression.charAt(i);
			if (currentChar == '+' || currentChar == '-' || i == expression.length() - 1) {
				String value;
				if (i == expression.length() - 1) {
					value = expression.substring(startIndex, i + 1);
				} else {
					value = expression.substring(startIndex, i);
				}
				int intValue = 0;
				if (value.matches("\\d+")) {
					// It is integer
					intValue = Integer.parseInt(value);
				} else {
					// It is variable search in map
					if (value.length() > 1 || !variables.containsKey(value.charAt(0)))
						return 0;
					else
						intValue = variables.get(value.charAt(0));
				}
				switch (operation) {
				case '+':
					result += intValue;
					break;
				case '-':
					result -= intValue;
					break;
				default:
					result = intValue;
				}
				startIndex = i + 1;
				operation = currentChar;
			}
		}
		return result;
	}
}

public class Demo {
	public static void main(String[] args) {
		ExpressionProcessor ep = new ExpressionProcessor();
		System.out.println(ep.calculate("1+2+3"));
		ep.variables.put('x', 3);
		System.out.println(ep.calculate("1+2+xy"));
		System.out.println(ep.calculate("10-2-x"));
	}
}
