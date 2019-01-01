#include <bits/stdc++.h>

using namespace std;

#define INF 0x3f3f3f3f

typedef pair<int, int> interval;

int min(int a, int b){
	return a<b?a:b;
}

int max(int a, int b){
	return a>b?a:b;
}

bool myCompare(const interval& a, const interval& b){
	return a.first < b.first;
}

vector<interval> computeUnion(vector<interval> slots){
	sort(slots.begin(), slots.end(), myCompare);
	vector<interval> results;
	results.push_back(slots[0]);
	int i=0, j=1;
	while(j < slots.size()){
		//if current interval is outside of previous interval
		if(slots[j].first > results[i].second){
			results.push_back(slots[j]);
			i++;
		} else {
			results[i] = {min(results[i].first, slots[j].first), max(results[i].second, slots[j].second)};
		}
		j++;
	}
	return results;
}

int main(){
	int n;
	cin>>n;
	vector<interval> slots;
	while(n--){
		int a, b;
		cin>>a>>b;
		slots.push_back({a,b});
	}

	vector<interval> results = computeUnion(slots);
//	for(auto& i : results){
//	    cout<<i.first<<" "<<i.second<<endl;
//	}
 	for(int i=0;i<results.size();i++){
 		cout<<results[i].first<<" "<<results[i].second<<endl;
 	}
	return 0;
}
