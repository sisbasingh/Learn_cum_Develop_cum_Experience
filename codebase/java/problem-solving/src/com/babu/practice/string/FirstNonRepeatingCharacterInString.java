package com.babu.practice.string;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacterInString {
	
	/**
	 * Using O(n) space and time complexity
	 * @param s
	 * @return
	 */
	private static char firstNonRepeatChar(String s) {
        Map<Character, CharIndexCount> hm = new HashMap<>();

        for(int i=0;i<s.length();i++){
        	char c = s.charAt(i);
            CharIndexCount cic = hm.get(c);
            if(cic == null){
                hm.put(c, new CharIndexCount(1, i));
            } else {
            	cic.setCount(cic.getCount()+1);
            	cic.setLastIndex(i);
                hm.put(c, cic);
            }
        }
        
        int firstNonRepeatCharIndex = Integer.MAX_VALUE;
        for(Map.Entry<Character, CharIndexCount> entry : hm.entrySet()) {
        	if(entry.getValue().getCount() == 1 && entry.getValue().getLastIndex() < firstNonRepeatCharIndex) {
        		firstNonRepeatCharIndex = entry.getValue().getLastIndex();
        	}
        }
        
        return firstNonRepeatCharIndex == Integer.MAX_VALUE?(0):s.charAt(firstNonRepeatCharIndex);
    }
    
	public static void main (String[] args) {
		char c = (char) -1;
		System.out.println(c);
	}
	
	static class CharIndexCount {
	    int count, lastIndex;
	    
	    public CharIndexCount(int _count, int _lastIndex){
	        count = _count;
	        lastIndex = _lastIndex;
	    }
	    
	    public void setCount(int _count){
	        count = _count;
	    }
	    
	    public void setLastIndex(int _lastIndex){
	        lastIndex = _lastIndex;
	    }
	    
	    public int getCount(){
	        return count;
	    }
	    
	    public int getLastIndex(){
	        return lastIndex;
	    }
	}

}
