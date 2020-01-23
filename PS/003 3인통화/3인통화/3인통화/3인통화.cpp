#include <stdio.h>
#include <memory.h>
#include <vector>
#define _MAX 2000000000;
#define MP(x,y) make_pair(x,y)
using namespace std;

int result_hap = 0;
vector<pair<int, int>> result;

class gogogo {
public:
	vector<vector<pair<int, int>>> map;
	int n;
	gogogo(int _n, int _m):n(_n) {
		map.resize(n + 1);
		int a, b, ga;
		for (int i=1;i<=_m;i++){
			scanf("%d %d %d", &a, &b, &ga);
			map[a].push_back(MP(b, ga)), map[b].push_back(MP(a, ga));
		}
	}
	
	vector<pair<int, int>> Get_minpath(int st) {
		vector<pair<int, int>> result;
		bool* visit = new bool[n + 1];
		memset(visit, 0, n+1);
		result.resize(n + 1);
		int i;
		for (i = 1; i <= n; i++) {
			result[i].first = _MAX;
		}
		
		result[st].first = 0;
		while (st) {
			visit[st] = true;
			int size = map[st].size();
			for (i = 0; i < size; i++) {
				if (map[st][i].second + result[st].first < result[map[st][i].first].first) {
					result[map[st][i].first].first = map[st][i].second + result[st].first;
					result[map[st][i].first].second = st;
				}
			}
			int min = _MAX - 1;

			st = 0;
			for (i = 1; i <= n; i++) {
				if (!visit[i] && min > result[i].first) {
					min = result[i].first;
					st = i;
				}
			}
		}
		return result;
	}
};


void add_path(vector<pair<int,int>> path, int ind) {
	int did = ind;
	ind = path[ind].second;
	while (ind != 0) {
		result.push_back(MP(ind, did));
		did = ind, ind = path[ind].second;
	}
}
int main()
{
//	freopen("input.txt", "r", stdin);
	int n,m;
	scanf("%d %d", &n, &m);
	gogogo go(n,m);

	int a, b, c;
	scanf("%d %d %d", &a, &b, &c);
	vector<pair<int, int>> a_min = go.Get_minpath(a), b_min = go.Get_minpath(b), c_min = go.Get_minpath(c);

	int resmin = 1231231231, minind = -1;
	int i;
	for (i = 1; i <= n; i++) {
		if (resmin > a_min[i].first + b_min[i].first + c_min[i].first) resmin = a_min[i].first + b_min[i].first + c_min[i].first, minind = i;
	}
	
	add_path(a_min, minind), add_path(b_min, minind), add_path(c_min, minind);
	printf("%d %d\n", resmin, result.size());
	for (i = 0; i < result.size(); i++) {
		printf("%d %d\n", result[i].first, result[i].second);
	}

	return 0;
}