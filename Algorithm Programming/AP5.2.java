import java.util.Scanner;

public class Main {
	static int[][] memo;
	static long callCount = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		memo = new int[n + 1][k + 1];

		int result = binom(n, k);

		System.out.println(result);
		System.out.println(callCount);

		sc.close();
	}

	public static int binom(int n, int k) {
		callCount++;

		if (k == 0 || k == n) {
			return 1;
		}

		if (memo[n][k] != 0) {
			return memo[n][k];
		}

		memo[n][k] = (binom(n - 1, k - 1) + binom(n - 1, k)) % 10007;

		return memo[n][k];
	}
}