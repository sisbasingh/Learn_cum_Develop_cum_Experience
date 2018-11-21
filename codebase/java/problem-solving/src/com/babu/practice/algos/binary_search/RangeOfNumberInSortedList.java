package com.babu.practice.algos.binary_search;

import java.util.ArrayList;
import java.util.List;

public class RangeOfNumberInSortedList {
	
	public int findStart(final List<Integer> a, int target) {
        int low = 0, high = a.size()-1;
        while(low <= high) {
            int mid = low + (high - low)/2;
            if((mid == 0 || a.get(mid-1) < target) && a.get(mid) == target)
                return mid;
            else if(a.get(mid) < target)
                low = mid+1;
            else
                high = mid-1;
        }
        return -1;
    }
    
    public int findEnd(final List<Integer> a, int start, int target) {
        int low = start, high = a.size()-1;
        while(low <= high) {
            int mid = low + (high-low)/2;
            if((mid == a.size()-1 || a.get(mid+1) > target) && a.get(mid) == target)
                return mid;
            else if(a.get(mid) > target)
                high = mid-1;
            else
                low = mid+1;
        }
        return -1;
    }
    
    public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
        ArrayList<Integer> results = new ArrayList<>();
        int start = findStart(a, b);
        if(start == -1) {
            results.add(-1);
            results.add(-1);
            return results;
        }
        results.add(start);
        results.add(findEnd(a, start, b));
        return results;
    }

}
