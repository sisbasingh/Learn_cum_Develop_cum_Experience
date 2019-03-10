package com.babu.practice.design.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheUsingLinkedHashMap {
	LinkedHashMap<Integer, KV> lruCache;
	int capacity;

	public LRUCacheUsingLinkedHashMap(int capacity) {
		this.capacity = capacity;
		lruCache = new LinkedHashMap<Integer, KV>(capacity) {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(Map.Entry<Integer, KV> eldest) {
				return size() > capacity;
			}
		};
	}

	public int get(int key) {
		if (!lruCache.containsKey(key)) {
			return -1;
		}
		KV element = lruCache.remove(key);
		lruCache.put(key, element);
		return element.val;
	}

	public void set(int key, int value) {
		if (lruCache.containsKey(key)) {
			KV element = lruCache.remove(key);
			element.val = value;
			lruCache.put(key, element);
			return;
		}
		KV newElement = new KV(key, value);
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
		LRUCacheUsingLinkedHashMap lru = new LRUCacheUsingLinkedHashMap(2);
		lru.set(1, 2);
		lru.set(3, 4);
		lru.set(1, 5);
		lru.set(2, 6);
	}

}
