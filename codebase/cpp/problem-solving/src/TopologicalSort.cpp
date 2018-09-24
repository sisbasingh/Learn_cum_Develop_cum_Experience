/*
 * TopologicalSort.cpp
 *
 *  Created on: Jul 28, 2018
 *      Author: sisba01
 */

#include<iostream>
#include<cstring>
#include<bits/stdc++.h>
#include<vector>
#include<stack>
#include<queue>

using namespace std;

vector<int> graph[10001];
int * topoSort(vector<int> graph[],int N);



int main()
{
	int T;
	cin>>T;
	while(T--)
	{
		memset(graph,0,sizeof graph);
		int N,E;
		cin>>E>>N;
		for(int i=0;i<E;i++)
		{
			int u,v;
			cin>>u>>v;
			graph[u].push_back(v);
		}
		int *res = topoSort(graph,N);
		bool valid =true;
		for(int i=0;i<N;i++)
		{
			int n=graph[res[i]].size();
			for(int j=0;j<graph[res[i]].size();j++)
			{
				for(int k=i+1;k<N;k++)
				{
					if(res[k]==graph[res[i] ] [j] )
						n--;
				}
			}
			if(n!=0)
			{
				valid =false;
				break;
			}
		}
		if(valid==false)
			cout<<0<<endl;
		else
			cout<<1<<endl;
	}
}


/*Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above.*/

void topoSortUsingDFS(vector<int> g[], int currVertex, bool visited[], stack<int> topOrder) {
    visited[currVertex] = true;
    for(int v : g[currVertex]) {
    	if(!visited[v]) {
    		topoSortUsingDFS(g, v, visited, topOrder);
    	}
    }
    topOrder.push(currVertex);
}

int * topoSortUsingBFS(vector<int> g[], int n) {
	bool visited[] = new bool[n];
	memset(visited, false, sizeof visited);
	int indegrees[] = new int[n];
	memset(indegrees, 0, sizeof indegrees);
	for(int i=0;i<n;i++) {
		for(int j : g[i]) {
			indegrees[j]++;
		}
	}
	queue<int> bfsq = new queue<int>(n);
	for(int i=0;i<n;i++) {
		if(indegrees[i] == 0) {
			bfsq.push(i);
			visited[i] = true;
		}
	}
	int topOrder[] = new int[n];
	int index = 0;
	while(!bfsq.empty()) {
		int v = bfsq.front();
		bfsq.pop();
		topOrder[index++] = v;
		for(int w : g[v]) {
			if(!visited[w]) {
				indegrees[w] -= 1;
				if(indegrees[w] == 0) {
					bfsq.push(w);
					visited[w] = true;
				}
			}
		}
	}
	return topOrder;
}

/* You need to complete this function */
int * topoSort(vector<int> graph[], int N)
{
   // Your code here
}




