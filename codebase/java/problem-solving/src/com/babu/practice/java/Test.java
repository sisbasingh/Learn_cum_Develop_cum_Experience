package com.babu.practice.java;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private static void testMap() {
		Map<Integer, Integer> hm = new HashMap<>();
		
		//System.out.println("Initial size: " + hm.size());
		for(int i=1;i<=13;i++) {
			hm.put(i, i);
			//System.out.println("After " + i + " entry size is: " + (table == null ? 0 : table.length));
		}
		
		/**
		 * Using reflection to get the capacity of HashMap as there is no direct API to get that
		 */
		Object[] table = null;
		try {
			Field tableField = HashMap.class.getDeclaredField("table");
			tableField.setAccessible(true);
			table = (Object[]) tableField.get(hm);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Map Capacity: " + (table == null ? 0 : table.length));
	}
	
	private static int compute(int n) {
		if(n < 10) {
			return n;
		}
		int count = 9;
		for(int i=11;i<n;i++) {
			String s1 = String.valueOf(i);
			char[] chars = s1.toCharArray();
			Arrays.sort(chars);
			String s2 = new String(chars);
			if(s1.equals(s2)) {
				count++;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		//testMap();
		long startTime = System.nanoTime();
		System.out.println(compute(1000));
		long endTime = System.nanoTime();
		System.out.println((endTime-startTime)/1000000000 + " seonds");
	}

}
