import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;

		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] W = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				W[i][j] = (i == j) ? 0 : INF;
			}
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			W[u][v] = w;
		}

		int[] touch = new int[n + 1];
		int[] length = new int[n + 1];

		for (int i = 2; i <= n; i++) {
			touch[i] = 1;
			length[i] = W[1][i];
		}

		printTouch(touch, n);

		int[][] F = new int[n - 1][3];

		for (int count = 0; count < n - 1; count++) {
			int min = INF;
			int vnear = -1;

			for (int i = 2; i <= n; i++) {
				if (length[i] >= 0 && length[i] < min) {
					min = length[i];
					vnear = i;
				}
			}

			if (vnear == -1) break;

			F[count][0] = touch[vnear];
			F[count][1] = vnear;
			F[count][2] = W[touch[vnear]][vnear];

			for (int i = 2; i <= n; i++) {
				if (length[i] >= 0 && W[vnear][i] != INF) {
					if (length[vnear] + W[vnear][i] < length[i]) {
						length[i] = length[vnear] + W[vnear][i];
						touch[i] = vnear;
					}
				}
			}
			length[vnear] = -1;
			printTouch(touch, n);
		}

		for (int i = 0; i < n - 1; i++) {
			System.out.println(F[i][0] + " " + F[i][1] + " " + F[i][2]);
		}

		int c = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < c; i++) {
			int t = Integer.parseInt(br.readLine().trim());
			if (t == 1) {
				System.out.println("1");
			} else {
				List<Integer> path = new ArrayList<>();
				int curr = t;
				while (curr != 1) {
					path.add(curr);
					curr = touch[curr];
				}
				path.add(1);

				StringBuilder sb = new StringBuilder();
				for (int j = path.size() - 1; j >= 0; j--) {
					sb.append(path.get(j));
					if (j > 0) sb.append(" ");
				}
				System.out.println(sb.toString());
			}
		}
	}

	static void printTouch(int[] touch, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			sb.append(touch[i]);
			if (i < n) sb.append(" ");
		}
		System.out.println(sb.toString());
	}
}