package com.babu.practice.graph;

public class UnionFind {
	
	public static int find(int[] parent, int x) {
		if(parent[x] == -1)
			return x;
		return find(parent, parent[x]);
	}
	
	public static void Union(int[] parent, int x, int y) {
		parent[x] = y;
	}

}
