package com.babu.practice.java.immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author sisba01
 * Class should be final to avoid any changes from child classes in case of inheritance
 * Instance variables should be final
 * Setters should not be provided
 * Getters must return either final field or cloned copy of mutable objects to avoid changes by clients for e.g. primitive arrays
 * Instead of array use unmodifiableList to avoid change of elements, or in case of array just return deep copy of array to clients instead of
 * original one
 *
 */
public final class Immutable {
	
	private final String immutable_string;
	private final int immutable_int;
	private final Map<String, String> immutable_map;
	private final List<Integer> immutable_list;
	private final Set<Integer> immutable_set;
	private final int[] immutable_array;
	
	public Immutable(String mstr, int mint, Map<String, String> immap, List<Integer> imlist, Set<Integer> imset, int [] imarray) {
		immutable_string = mstr;
		immutable_int = mint;
		immutable_map = Collections.unmodifiableMap(immap);
		immutable_list = Collections.unmodifiableList(imlist);
		immutable_set = Collections.unmodifiableSet(imset);
		immutable_array = imarray;
	}
	
	
	public String getImmutable_string() {
		return immutable_string;
	}


	public int getImmutable_int() {
		return immutable_int;
	}


	public Map<String, String> getImmutable_map() {
		return immutable_map;
	}
	
	public List<Integer> getImmutable_list() {
		return immutable_list;
	}
	
	public Set<Integer> getImmutable_set() {
		return immutable_set;
	}
	
	public int[] getImmutable_array() {
		return immutable_array;
	}


	public static void main(String[] args) {
		Map<String, String> hmp = new HashMap<>();
		hmp.put("key", "value1");
		List<Integer> imList = new ArrayList<>();
		imList.add(1);
		imList.add(2);
		Set<Integer> hset = new HashSet<>();
		int [] marr = new int[] {1, 2, 3};
		Immutable immtble = new Immutable("string", 10, hmp, imList, hset, marr);
		
		/*System.out.println(Arrays.toString(immtble.immutable_array));
		immtble.immutable_array[0] = 5;
		System.out.println(Arrays.toString(immtble.immutable_array));*/
		
		/*Map<String, String> instanceMap = immtble.getImmutable_map();
		instanceMap.put("key", "value2");
		*//**
		 * To test whether the map reference can be changed to point to another map, it fails
		 *//*
		instanceMap = new HashMap<>();
		instanceMap.put("key", "value2");
		System.out.println(immtble.getImmutable_map().get("key"));*/
		
		/*List<Integer> instanceList = immtble.getImmutable_list();
		instanceList.add(3);
		immtble.immutable_list = new ArrayList<>();
		System.out.println(instanceList.size());
		System.out.println(immtble.immutable_list.size());*/
		
	}

}
