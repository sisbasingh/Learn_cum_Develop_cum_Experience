/*
 * DFS.cpp
 *
 *  Created on: Jul 28, 2018
 *      Author: sisba01
 */
#include<iostream>
#include<cstring>
#include<list>

using namespace std;
using std::list;

class Graph {
private:
	int v;
	list<int> *adj;

public:
	Graph(int v);
	void addEdge(int u, int v);
	void DFSUtil(int s, int d, int distances[]);
};

Graph::Graph(int v) {
	this->v = v;
	adj = new list<int>[v];
}

void Graph::addEdge(int u, int v) {
	this->adj[u].push_back(v);
}

void Graph::DFSUtil(int s, int d, int distances[]) {
	distances[s] = d;
	list<int> :: iterator i;
	for(i=this->adj[s].begin(); i!=this->adj[s].end(); ++i) {
		if(distances[*i] == -1) {
			DFSUtil(*i, d+1, distances);
		}
	}
}

/**
 * Count number of nodes at even distance in an connected acyclic graph
 */
int main() {
	int t;
	cin>>t;
	while(t--) {
		int v, x, y;
		cin>>v;
		Graph g (v);
		int * dist = new int[v];
		memset(dist, -1, v*sizeof(int));
		for(int i=1;i<v;i++) {
			cin>>x>>y;
			g.addEdge(x-1, y-1);
			g.addEdge(y-1, x-1);
		}
		g.DFSUtil(0, 0, dist);
		long evenCount = 0, oddCount = 0;
		for(int i=0;i<v;i++) {
			if(dist[i] % 2 != 0)
				oddCount++;
			else
				evenCount++;
		}
		long result = (evenCount*(evenCount-1))/2 + (oddCount*(oddCount-1))/2;
		cout<<result<<endl;
	}
}





