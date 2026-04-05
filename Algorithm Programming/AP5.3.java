import java.util.Scanner;

public class Main {
	static final int INF = 999;
	static int n, m;
	static int[][] D;
	static int[][] P;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNext()) return;
		n = sc.nextInt();
		m = sc.nextInt();

		D = new int[n + 1][n + 1];
		P = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) D[i][j] = 0;
				else D[i][j] = INF;
			}
		}

		for (int i = 0; i < m; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int w = sc.nextInt();
			D[u][v] = w;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (D[i][k] + D[k][j] < D[i][j]) {
						D[i][j] = D[i][k] + D[k][j];
						P[i][j] = k;
					}
				}
			}
		}

		printMatrix(D);
		printMatrix(P);

		if (sc.hasNextInt()) {
			int k_queries = sc.nextInt();
			for (int i = 0; i < k_queries; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();

				if (D[start][end] >= INF) {
					System.out.println("NONE");
				} else {
					if (start == end) {
						System.out.println(start + " " + end);
					} else {
						System.out.print(start + " ");
						printPath(start, end);
						System.out.println(end);
					}
				}
			}
		}
	}

	public static void printPath(int i, int j) {
		if (P[i][j] != 0) {
			printPath(i, P[i][j]);
			System.out.print(P[i][j] + " ");
			printPath(P[i][j], j);
		}
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				System.out.print(matrix[i][j] + (j == n ? "" : " "));
			}
			System.out.println();
		}
	}
}