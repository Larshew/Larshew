#include <iostream>
#include <algorithm>
#define MAX_ANSWER 400*1000000
#define MAX_N 400
using namespace std;

int map[MAX_N+1][MAX_N + 1][MAX_N + 1];
int map_min[MAX_N + 1][MAX_N + 1], map_min_index[MAX_N+1][MAX_N+1];
int N, K;
int snakes[MAX_N + 1];

int snake_sorted[MAX_N + 1], ssorted_n;

int main() {
	cin >> N >> K;
	int i,j,k,tmp[401];
	tmp[0] = -1;
	for (i = 1; i <= N; i++) {
		cin >> snakes[i];
		tmp[i] = snakes[i];
	}
	sort(tmp + 1, tmp + N + 1);
	for (i = 1; i <= N; i++) {
		if (tmp[i] != tmp[i - 1]) {
			snake_sorted[++ssorted_n] = tmp[i];
		}
	}

	int hap = 0, max_snake = snakes[1];
	for (i = 1; i <= N; i++) {
		hap += snakes[i];
		if (max_snake < snakes[i]) max_snake = snakes[i];
		int min = 1231231231, ind=-1;
		for (k = 1; k <= ssorted_n; k++) {
			if (snakes[i] > max_snake) map[i][0][k] = MAX_ANSWER + 1;
			else map[i][0][k] = max_snake * i - hap;
			if (map[i][0][k] < min) min = map[i][0][k], ind = k;

		}
		map_min[i][0] = min, map_min_index[i][0] = ind;
	}
	for (i = 1; i <= N; i++) {
		for (j = 1; j <= K; j++) {
			int min = 1231231231, ind = -1;
			for (k = 1; k <= ssorted_n; k++) {
				if (snake_sorted[k] < snakes[i]) {
					map[i][j][k] = MAX_ANSWER + 1;
				}
				else {
					map[i][j][k] = ::min(map[i - 1][j][k] + snake_sorted[k] - snakes[i], map_min[i-1][j - 1] + snake_sorted[k] - snakes[i] );
				}
				if (min > map[i][j][k]) min = map[i][j][k], ind = k;
			}
			map_min[i][j] = min, map_min_index[i][j] = k;
		}
	}

	cout << map_min[N][K];
	return 0;
}