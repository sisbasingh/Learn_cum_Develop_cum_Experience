package com.babu.practice.graph.shortestpaths;

public class FloydWashallAllPairShortestPaths {
	
	/**
	 * Floyd-Warshall algorithm for all pair shortest paths, the graph can have negative edge weights
	 * The algorithm takes O(V3) time to compute this
	 * @param graph
	 * @param v
	 */
	private static void computeAllPairShortestPath(int[][] graph, int v) {
		for(int k=1;k<v;k++) {
			for(int i=0;i<v;i++) {
				for(int j=0;j<v;j++) {
					if(graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
						graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
					}
				}
			}
		}
	}
	
	/**
	 * Method to compute Transitive closure of a Graph to check whether there is a path between any two pair of 
	 * vertices or not
	 * @param graph
	 * @param v
	 * @return
	 */
	private static boolean[][] computeTransitiveClosure(int[][] graph, int v) {
		boolean[][] tc = new boolean[v][v];
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++) {
				if(i == j || graph[i][j] != 0)
					tc[i][j] = true;
				else
					tc[i][j] = false;
			}
		}
		
		for(int k=0;k<v;k++) {
			for(int i=0;i<v;i++) {
				for(int j=0;j<v;j++) {
					tc[i][j] = tc[i][j] || (tc[i][k] && tc[k][j]);
				}
			}
		}
		
		return tc;
	}
	
	private static void printGraph(int[][] g, int v) {
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++) {
				System.out.print(g[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		/*int[][] graph = {
							{ 0, 3, 8, Integer.MAX_VALUE, -4},
							{ Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 1, 7 },
							{ Integer.MAX_VALUE, 4, 0, Integer.MAX_VALUE, Integer.MAX_VALUE },
							{ 2, Integer.MAX_VALUE, -5, 0, Integer.MAX_VALUE },
							{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 0 }
						};
		computeAllPairShortestPath(graph, 5);
		printGraph(graph, 5);*/
		
		//Test for Transitive closure of a graph
		int v = 4;
		int[][] graph = {
							{1, 0, 0, 0},
							{0, 1, 1, 1},
							{0, 1, 1, 0},
							{1, 0, 1, 1}
						};
		boolean[][] tc = computeTransitiveClosure(graph, v);
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++)
				System.out.print("  " + (tc[i][j]?1:0));
			System.out.println();
		}
	}

}