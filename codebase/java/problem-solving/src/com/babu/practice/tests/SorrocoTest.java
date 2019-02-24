package com.babu.practice.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SorrocoTest {
	
	private Map<String, ArrayList<String>> dict;
	
	private SorrocoTest(ArrayList<ArrayList<String>> dictWords) {
		dict = new HashMap<>();
		for(ArrayList<String> kvals : dictWords) {
			if(dict.containsKey(kvals.get(0))) {
				dict.get(kvals.get(0)).add(kvals.get(1));
				if(dict.containsKey(kvals.get(1))) {
					dict.get(kvals.get(1)).add(kvals.get(0));
				} else {
					ArrayList<String> val = new ArrayList<>();
					val.add(kvals.get(0));
					dict.put(kvals.get(1), val);
				}
			} else {
				ArrayList<String> val1 = new ArrayList<>();
				val1.add(kvals.get(1));
				dict.put(kvals.get(0), val1);
				if(dict.containsKey(kvals.get(1))) {
					dict.get(kvals.get(1)).add(kvals.get(0));
				} else {
					ArrayList<String> val = new ArrayList<>();
					val.add(kvals.get(0));
					dict.put(kvals.get(1), val);
				}
			}
		}
	}
	
	private boolean isSentenceMatch(String s1, String s2) {
		//Base cases
		if(s1 == null && s2 == null) {
			return true;
		} else if (s1 == null || s2 == null) {
			return false;
		} else if(s1.isEmpty() && s2.isEmpty()) {
			return true;
		} else if(s1.isEmpty() || s2.isEmpty()) {
			return false;
		}
		
		String[] w1 = s1.split("\\s+");
		String[] w2 = s2.split("\\s+");
		
		//Base case number of words different
		if(w1.length != w2.length) {
			return false;
		}
		
		Map<String, Integer> wmap1 = new HashMap<>();
		Map<String, Integer> wmap2 = new HashMap<>();
		
		for(String s : w1) {
			if(wmap1.containsKey(s)) {
				wmap1.put(s, wmap1.get(s) + 1);
			} else {
				wmap1.put(s, 1);
			}
		}
		
		for(String s : w2) {
			if(wmap2.containsKey(s)) {
				wmap2.put(s, wmap2.get(s) + 1);
			} else {
				wmap2.put(s, 1);
			}
		}
		
		for(String word : wmap1.keySet()) {
			int wcount = wmap1.get(word);
			for(int i=1;i<=wcount;i++) {
				//If word is contained as is 
				if(wmap2.containsKey(word)) {
					wmap1.put(word, wmap1.get(word) - 1);
					wmap2.put(word, wmap2.get(word) - 1);
				} else {
					//Use dictionary meanings
					ArrayList<String> meanings = dict.get(word);
					if(meanings == null) {
						return false;
					} else {
						boolean meanFound = false;
						for(String meaning : meanings) {
							if(wmap2.containsKey(meaning)) {
								wmap1.put(word, wmap1.get(word) - 1);
								wmap2.put(meaning, wmap2.get(meaning) - 1);
								meanFound = true;
								break;
							}
						}
						if(!meanFound) {
							return false;
						}
					}
				}
			}
		}
		
		/**
		 * At the end check the counts of words in both maps is 0 or not
		 */
		for(String key : wmap1.keySet()) {
			if(wmap1.get(key) > 0) {
				return false;
			}
		}
		
		for(String key : wmap2.keySet()) {
			if(wmap2.get(key) > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		/**
		 * [["great", "fine"], ["fine", "good"], ["acting", "drama"], ["skills", "talent"]]
		 */
		
		String[] keys = {"great", "fine", "acting", "skills" };
		String[] vals = {"fine", "good", "drama", "talent" };
		
		ArrayList<ArrayList<String>> list = new ArrayList<>();
		
		for(int i=0;i<keys.length;i++) {
			ArrayList<String> keyvals = new ArrayList<>();
			keyvals.add(keys[i]);
			keyvals.add(vals[i]);
			list.add(keyvals);
		}
		
		
		String s1 = new String("great drama skills foo");
		String s2 = new String("fine acting talent bar");
		
		SorrocoTest st = new SorrocoTest(list);
		System.out.println(st.isSentenceMatch(s1, s2));
		
	}

}
