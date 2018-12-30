#include <bits/stdc++.h>

using namespace std;

typedef pair<int, int> iip;
typedef vector<vector<iip>> graph;
typedef vector<vector<int>> vvi;

typedef tuple<int, int, int> iiit;
typedef vector<iiit> edges;

bool sortByThird(const tuple<int, int, int>& t1, const tuple<int, int, int> &t2) {
	return (get<2>(t1) < get<2>(t2));
}


edges getEdges(vvi& roads) {
	edges e;
	for(int i=0;i<roads.size();i++) {
		iiit t{roads[i][0], roads[i][1], roads[i][2]};
		e.push_back(t);
	}
	return e;
}



// Complete the minTime function below.
int minTime(vector<vector<int>> roads, vector<int> machines) {
	set<int> machineSet;
	for(auto n : machines) {
		machineSet.insert(n);
	}

	edges e = getEdges(roads);
	sort(e.begin(), e.end(), sortByThird);
	int minT = 0;
	int count = 0;
	for(int i=0;i<e.size(), count<machines.size()-1;i++) {
		if(machineSet.find(get<0>(e[i])) != machineSet.end()) {
			//machineSet.erase(get<0>(e[i]));
			minT += get<2>(e[i]);
			count++;
		} else if(machineSet.find(get<1>(e[i])) != machineSet.end()) {
			//machineSet.erase(get<1>(e[i]));
			minT += get<2>(e[i]);
			count++;
		}
	}
	return minT;
}

int main() {
	int n, k;
	cin >> n >> k;

	vector<vector<int>> roads(n - 1);
	for (int i = 0; i < n - 1; i++) {
		roads[i].resize(3);

		for (int j = 0; j < 3; j++) {
			cin >> roads[i][j];
		}

		cin.ignore(numeric_limits<streamsize>::max(), '\n');
	}

	vector<int> machines(k);

	for (int i = 0; i < k; i++) {
		int machines_item;
		cin >> machines_item;
		cin.ignore(numeric_limits<streamsize>::max(), '\n');

		machines[i] = machines_item;
	}

	int result = minTime(roads, machines);

	cout << result << "\n";

	//fout.close();

	return 0;
}
