package com.babu.practice.graph.mst;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.babu.practice.graph.UnionFind;

public class KruskalMST {
	
	public class Graph {
		int V, E;
		List<Edge> edges;
		
		public Graph(int v, int e) {
			V = v;
			E = e;
			edges = new ArrayList<>(E);
		}
		
		public void addEdge(int src, int dest, int weight) {
			Edge edge = new Edge(src, dest, weight);
			edges.add(edge);
		}
		
		public List<Edge> kruskalMST() {
			int[] parent = new int[V];
			Arrays.fill(parent, -1);
			List<Edge> mst = new ArrayList<>();
			
			Collections.sort(edges);
			
			int edgeCount = 0, index = 0;
			
			while(edgeCount < V-1) {
				Edge e = edges.get(index++);
				int srcSet = UnionFind.find(parent, e.src);
				int destSet = UnionFind.find(parent, e.dest);
				if(srcSet != destSet) {
					mst.add(e);
					UnionFind.Union(parent, e.src, e.dest);
					edgeCount++;
				}	
			}
			return mst;
		}
		
	}
	
	class Edge implements Comparable<Edge> {
		int src, dest, weight;
		
		public Edge(int s, int d, int w) {
			src = s;
			dest = d;
			weight = w;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	
	public static void main(String[] args) {
		Graph graph = new KruskalMST().new Graph(4, 5);
		graph.addEdge(0, 1, 10);
		graph.addEdge(0, 2, 6);
		graph.addEdge(0, 3, 5);
		graph.addEdge(1, 3, 15);
		graph.addEdge(2, 3, 4);
		List<Edge> mstEdges = graph.kruskalMST();
		System.out.println("Edges in MST are:\n");
		for(Edge e : mstEdges) {
			System.out.println(e.src + " -> " + e.dest + " : " + e.weight);
		}
	}
	
}
