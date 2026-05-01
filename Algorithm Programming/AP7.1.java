import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;
		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] W = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i != j) {
					W[i][j] = INF;
				} else {
					W[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			W[u][v] = w;
			W[v][u] = w;
		}

		int[] nearest = new int[n + 1];
		int[] distance = new int[n + 1];

		for (int i = 2; i <= n; i++) {
			nearest[i] = 1;
			distance[i] = W[1][i];
		}

		printNearest(nearest, n);

		int[][] F = new int[n - 1][3];

		for (int count = 0; count < n - 1; count++) {
			int min = INF;
			int vnear = -1;

			for (int i = 2; i <= n; i++) {
				if (0 <= distance[i] && distance[i] < min) {
					min = distance[i];
					vnear = i;
				}
			}

			if (vnear == -1) break;

			F[count][0] = vnear;
			F[count][1] = nearest[vnear];
			F[count][2] = W[vnear][nearest[vnear]];

			distance[vnear] = -1;

			for (int i = 2; i <= n; i++) {
				if (0 <= distance[i] && W[i][vnear] < distance[i]) {
					distance[i] = W[i][vnear];
					nearest[i] = vnear;
				}
			}

			printNearest(nearest, n);
		}

		for (int i = 0; i < n - 1; i++) {
			System.out.println(F[i][0] + " " + F[i][1] + " " + F[i][2]);
		}
	}

	static void printNearest(int[] nearest, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			sb.append(nearest[i]);
			if (i < n) sb.append(" ");
		}
		System.out.println(sb.toString());
	}
}