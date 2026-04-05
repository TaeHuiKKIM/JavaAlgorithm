import java.util.Scanner;

public class Main {
	static int[][] image;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) return;
		int n = sc.nextInt();
		image = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				image[i][j] = sc.nextInt();
			}
		}

		solve(n, 0, 0);
		System.out.println(sb.toString());
	}

	public static void solve(int n, int r, int c) {
		if (isUniform(n, r, c)) {
			if (image[r][c] == 0) {
				sb.append("w");
			} else {
				sb.append("b");
			}
			return;
		}

		// 색이 섞여 있는 경우
		sb.append("x");
		int m = n / 2;

		// 1, 2, 3, 4분면 순서대로 재귀 호출
		solve(m, r, c);         // 1사분면 (왼쪽 위)
		solve(m, r, c + m);     // 2사분면 (오른쪽 위)
		solve(m, r + m, c);     // 3사분면 (왼쪽 아래)
		solve(m, r + m, c + m); // 4사분면 (오른쪽 아래)
	}

	public static boolean isUniform(int n, int r, int c) {
		int color = image[r][c];
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (image[i][j] != color) {
					return false;
				}
			}
		}
		return true;
	}
}