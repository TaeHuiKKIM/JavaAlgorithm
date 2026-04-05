#include <bits/stdc++.h>

using namespace std;
int funcCnt = 0, moveCnt = 0, k;

void hanoi(int n, char src, char via, char dst) {
	funcCnt++;
	if (n == 1) {
		moveCnt++;
		if (k == moveCnt) printf("%c -> %c\n", src, dst);
	}
	else {
		hanoi(n - 1, src, dst, via);
		hanoi(1, src, via, dst);
		hanoi(n - 1, via, src, dst);
	}
}

int main() {
	int n;
	cin >> n >> k;
	hanoi(n, 'A', 'B', 'C');
	cout << funcCnt;
}