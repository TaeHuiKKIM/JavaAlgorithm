import java.util.Scanner;

public class Main {
	static int[][] board;
	static int trominoId = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) return;

		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();

		board = new int[n][n];

		solve(n, 0, 0, r, c);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(board[i][j]);
				if (j < n - 1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());

		sc.close();
	}

	private static void solve(int n, int r, int c, int hr, int hc) {
		if (n == 1) return;

		int id = ++trominoId;
		int m = n / 2;
		int midR = r + m;
		int midC = c + m;

		if (hr < midR && hc < midC) {
			board[midR - 1][midC] = id;
			board[midR][midC - 1] = id;
			board[midR][midC] = id;

			solve(m, r, c, hr, hc);
			solve(m, r, midC, midR - 1, midC);
			solve(m, midR, c, midR, midC - 1);
			solve(m, midR, midC, midR, midC);
		} else if (hr < midR && hc >= midC) {
			board[midR - 1][midC - 1] = id;
			board[midR][midC - 1] = id;
			board[midR][midC] = id;

			solve(m, r, c, midR - 1, midC - 1);
			solve(m, r, midC, hr, hc);
			solve(m, midR, c, midR, midC - 1);
			solve(m, midR, midC, midR, midC);
		} else if (hr >= midR && hc < midC) {
			board[midR - 1][midC - 1] = id;
			board[midR - 1][midC] = id;
			board[midR][midC] = id;

			solve(m, r, c, midR - 1, midC - 1);
			solve(m, r, midC, midR - 1, midC);
			solve(m, midR, c, hr, hc);
			solve(m, midR, midC, midR, midC);
		} else {
			board[midR - 1][midC - 1] = id;
			board[midR - 1][midC] = id;
			board[midR][midC - 1] = id;

			solve(m, r, c, midR - 1, midC - 1);
			solve(m, r, midC, midR - 1, midC);
			solve(m, midR, c, midR, midC - 1);
			solve(m, midR, midC, hr, hc);
		}
	}
}