import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) return;
		int C = sc.nextInt();

		while (C-- > 0) {
			int n = sc.nextInt();
			int[][] triangle = new int[n][n];

			int[][] dp = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					triangle[i][j] = sc.nextInt();
				}
			}
			for (int j = 0; j < n; j++) {
				dp[n - 1][j] = triangle[n - 1][j];
			}

			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j <= i; j++) {
					dp[i][j] = triangle[i][j] + Math.max(dp[i + 1][j], dp[i + 1][j + 1]);
				}
			}
			System.out.println(dp[0][0]);

			int currJ = 0;
			for (int i = 0; i < n; i++) {
				System.out.print(triangle[i][currJ]);
				if (i < n - 1) {
					if (dp[i + 1][currJ + 1] >= dp[i + 1][currJ]) {
						currJ = currJ + 1;
					} else {
						currJ = currJ;
					}
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		sc.close();
	}
}