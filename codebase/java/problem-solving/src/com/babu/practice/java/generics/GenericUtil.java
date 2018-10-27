package com.babu.practice.java.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericUtil {
	
	Set<String> mSet = new HashSet<>();
	
	List<String> mList = new ArrayList<>();
	
	Map<String, String> mMap = new HashMap<>();
	
	
	public static void main(String[] args) {
		
		GenericUtil gu = new GenericUtil();
		
		String joined = gu.joinCollection(gu.mSet);
	}

	
	private String joinCollection(Collection<String> collection) {
		StringBuffer sbr = new StringBuffer("");
		for(String str : collection) {
			sbr.append(str);
		}
		return sbr.toString();
	}
	
}
