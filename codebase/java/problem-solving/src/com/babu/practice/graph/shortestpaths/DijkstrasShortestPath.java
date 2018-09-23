package com.babu.practice.graph.shortestpaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstrasShortestPath {
	
	public static void main(String [] args) {
		Graph g = new DijkstrasShortestPath().new Graph(9);
		
		int graph[][] = new int[][]{{0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 14, 10, 0, 2, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
           };
           
       for(int i=0;i<g.v;i++) {
    	   for(int j=0;j<g.v;j++) {
    		   if(graph[i][j] != 0) {
    			   g.addEdge(i, j, graph[i][j]);
    		   }
    	   }
       }
       
       g.shortestPath(0);
		
	}
	
	public class Graph {
		int v;
		Map<Vertex, List<Edge>> graph;
		
		Map<Integer, Vertex> vset;
		
		Graph(int v) {
			this.v = v;
			graph = new HashMap<>();
			
			vset = new HashMap<>();
			
			for(int i=0;i<v;i++) {
				vset.put(i, new Vertex(i));
				graph.put(vset.get(i), new ArrayList<Edge>());
			}
		}
		
		public void addEdge(int u, int v, int w) {
			graph.get(vset.get(u)).add(new Edge(vset.get(v), w));
		}
		
		public void shortestPath(int u) {
			Vertex source = vset.get(u);
			source.setMinDist(0);
			
			//Create Priority queue
			Queue<Vertex> pq = new PriorityQueue<Vertex>();
			for(int i=0;i<v;i++) {
				pq.add(vset.get(i));
			}
			//pq.add(source);
			
			for(int count=0;count<v-1;count++) {
				Vertex current = pq.poll();
				current.setIsIncluded(true);
				for(Edge edge : graph.get(current)) {
					if(!edge.endVertex.isIncluded && current.minDist != Integer.MAX_VALUE && (current.minDist + edge.weight < edge.endVertex.minDist)) {
						edge.endVertex.setMinDist(current.minDist + edge.weight);
						edge.endVertex.setParent(current.v);
					}
				}
			}
			
			System.out.println("Minimum distances are: \n");
			for(int i=0;i<v;i++) {
				System.out.println("Min distance from: " + u + " to: " + i + " is: " + vset.get(i).minDist);
			}
		}
	}
	
	class Edge {
		Vertex endVertex;
		int weight;
		
		public Edge(Vertex v, int w) {
			this.endVertex = v;
			this.weight = w;
		}
	}
	
	class Vertex implements Comparable<Vertex> {
		int v;
		int minDist;
		int parent;
		boolean isIncluded;
		
		public Vertex(int v) {
			this.v = v;
			minDist = Integer.MAX_VALUE;
			parent = -1;
			isIncluded = false;
		}
		
		public void setMinDist(int minDist) {
			this.minDist = minDist;
		}
		
		public void setIsIncluded(boolean isIncluded) {
			this.isIncluded = isIncluded;
		}
		
		public void setParent(int parent) {
			this.parent = parent;
		}

		@Override
		public int compareTo(Vertex vertex) {
			return (this.minDist - vertex.minDist);
		}
	}

}
