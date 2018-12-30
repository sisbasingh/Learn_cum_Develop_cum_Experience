#include <bits/stdc++.h>

using namespace std;

static const int INF = 0x3f3f3f3f;

int dist[10000] = {INF};
bool visited[10000] = {false};

typedef pair<int, int> edge;

void dikstraShortestPath(vector<vector<edge> > graph, int n, int m) {
    queue<edge> q;
    q.push({0, 0});
    dist[0] = 0;

    while(!q.empty()){
        edge e = q.front();
        q.pop();
        if(visited[e.first])
            continue;
        visited[e.first] = true;
        for(edge adj : graph[e.first]){
            if(dist[e.first]+adj.second < dist[adj.first]){
                dist[adj.first] = dist[e.first]+adj.second;
                q.push(adj);
            }
        }
    }
}

int main(){
    vector<vector<edge> > graph;

    int n, m;
    cin>>n>>m;
    for(int i=0;i<m;i++){
        int x, y, w;
        cin>>x>>y>>w;
        graph[x-1].push_back({y-1,w});
    }

    dikstraShortestPath(graph, n, m);

    for(int i=1;i<n;i++){
        cout<<dist[i]<<" ";
    }

}
