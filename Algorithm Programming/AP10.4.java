import java.util.Scanner;

public class Main {
	static int n, m_edges;
	static int[][] W;
	static int[] vindex;
	static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNextInt()) return;
		n = sc.nextInt();
		m_edges = sc.nextInt();

		W = new int[n + 1][n + 1];
		for (int i = 0; i < m_edges; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			W[u][v] = 1;
			W[v][u] = 1; // 무방향 그래프
		}

		vindex = new int[n];
		vindex[0] = 1; // 시작 정점 고정 (1번 노드)

		hamiltonian(0);

		System.out.println(count);
		sc.close();
	}

	static void hamiltonian(int i) {
		if (promising(i)) {
			if (i == n - 1) {
				count++;
			} else {
				for (int j = 2; j <= n; j++) {
					vindex[i + 1] = j;
					hamiltonian(i + 1);
				}
			}
		}
	}

	static boolean promising(int i) {
		// 마지막 정점에서 첫 정점(1번)으로 가는 간선이 없는 경우
		if (i == n - 1 && W[vindex[n - 1]][vindex[0]] == 0) {
			return false;
		}
		// i번째 정점이 이전 정점과 연결되어 있지 않은 경우
		if (i > 0 && W[vindex[i - 1]][vindex[i]] == 0) {
			return false;
		}
		// 이미 방문한 정점인지 확인
		int j = 1;
		boolean switchBool = true;
		while (j < i && switchBool) {
			if (vindex[i] == vindex[j]) {
				switchBool = false;
			}
			j++;
		}
		return switchBool;
	}
}