#include <bits/stdc++.h>
#define min(a,b) ((a)<(b)?(a):(b))

typedef long long ll;

using namespace std;

static const int INF = 0x3f3f3f3f;
static const int LINF = 0x3f3f3f3f3f3f3f3f;

void nodesInComponentDFS(vector<vector<int>> cities, int s, bool visited[]) {
    visited[s] = true;
    for(int node : cities[s]) {
        if(!visited[node]) {
            nodesInComponentDFS(cities, node, visited);
        }
    }
}

long nodesCountUsingBFS(vector<vector<int>> cities, int s, bool visited[]) {
    long count = 0;
    visited[s] = true;
    queue<int> q;
    q.push(s);
    while(!q.empty()) {
        int node = q.front();
        q.pop();
        count++;
        for(int n : cities[node]) {
            if(!visited[n]) {
                visited[n] = true;
                q.push(n);
            }
        }
    }
    return count;
}

int findSet(int parent[], int i) {
    while(parent[i] != i) {
        i = parent[i];
    }
    return i;
}

void unionSet(int parent[], int a, int b, int size[]){
    int pa = findSet(parent, a);
    int pb = findSet(parent, b);

    if(pa == pb)
        return;
    if(size[pa] <= size[pb]) {
        parent[pa] = pb;
        size[pb] += size[pa];
    } else {
        parent[pb] = pa;
        size[pa] += size[pb];
    }
}

long componentCountUsingUnionFind(vector<vector<int>> cities, int n) {
    int parent[n];
    int size[n];

    for(int i=0;i<n;i++) {
        parent[i] = i;
        size[i] = 1;
    }

    for(int i=0;i<cities.size();i++) {
        unionSet(parent, cities[i][0]-1, cities[i][1]-1, size);
    }

    long componentsCount = 0;
    for(int i=0;i<n;i++) {
        if(parent[i] == i)
            componentsCount++;
    }
    return componentsCount;
}

// Complete the roadsAndLibraries function below.
long roadsAndLibraries(int n, long c_lib, long c_road, vector<vector<int>> cities) {
    long components = componentCountUsingUnionFind(cities, n);
    return components*c_lib + (n-components)*min(c_lib, c_road);
}

int main()
{
    int q;
    cin >> q;

    for (int q_itr = 0; q_itr < q; q_itr++) {
        int n, m;
        long long c_lib, c_road;
        cin>>n>>m>>c_lib>>c_road;

        vector<vector<int>> cities(m);
		for (int i = 0; i < m; i++) {
			cities[i].resize(2);
			for(int j=0;j<2;j++)
				cin>>cities[i][j];
		}

		long result = roadsAndLibraries(n, c_lib, c_road, cities);

        cout << result << "\n";
    }

    return 0;
}
