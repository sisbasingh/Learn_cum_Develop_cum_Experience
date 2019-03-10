package com.babu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Solution {

	List<KV> llist;
	Map<Integer, KV> lruCache;
	int capacity;

	public Solution(int capacity) {
		this.capacity = capacity;
		llist = new LinkedList<>();
		lruCache = new HashMap<>(capacity);
	}

	public int get(int key) {
		if (!lruCache.containsKey(key)) {
			return -1;
		}
		KV element = lruCache.get(key);
		if (element == llist.get(llist.size() - 1)) {
			return element.val;
		}
		llist.remove(element);
		llist.add(element);
		return element.val;
	}

	public void set(int key, int value) {
		if (lruCache.containsKey(key)) {
			KV element = lruCache.get(key);
			element.val = value;
			llist.remove(element);
			llist.add(element);
			return;
		}
		KV newElement = new KV(key, value);
		if (llist.size() == capacity) {
			KV element = llist.get(0);
			lruCache.remove(element.key);
			llist.remove(0);
		}
		llist.add(newElement);
		lruCache.put(key, newElement);
	}

	static class KV {
		int key, val;

		KV(int k, int v) {
			key = k;
			val = v;
		}
	}

	public static void main(String[] args) {
		// 6 2 S 2 1 S 1 1 S 2 3 S 4 1 G 1 G 2

		Solution sol = new Solution(2);
		sol.set(2, 1);
		sol.set(1, 1);
		sol.set(2, 3);
		sol.set(4, 1);
		sol.get(1);
		sol.get(2);
	}
}
