import java.util.Scanner;

public class Main {
	static int n, m_edges;
	static int[][] W;
	static int[] vcolor;
	static int count;

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

		// 1. 최소 m 찾기 및 2. 경우의 수 세기
		for (int m = 1; m <= n; m++) {
			vcolor = new int[n + 1];
			count = 0;
			m_coloring(0, m);

			// 가능한 칠하기 경우가 1개라도 있다면 해당 m이 최소값
			if (count > 0) {
				System.out.println(m);
				System.out.println(count);
				break;
			}
		}
		sc.close();
	}

	static void m_coloring(int i, int m) {
		if (promising(i)) {
			if (i == n) {
				count++;
			} else {
				for (int color = 1; color <= m; color++) {
					vcolor[i + 1] = color;
					m_coloring(i + 1, m);
				}
			}
		}
	}

	static boolean promising(int i) {
		int j = 1;
		boolean switchBool = true;
		while (j < i && switchBool) {
			// 인접해 있으면서 색이 같으면 안됨
			if (W[i][j] == 1 && vcolor[i] == vcolor[j]) {
				switchBool = false;
			}
			j++;
		}
		return switchBool;
	}
}