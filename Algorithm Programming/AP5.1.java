import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		if (k > n / 2) {
			k = n - k;
		}

		int[] dp = new int[k + 1];
		dp[0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = Math.min(i, k); j > 0; j--) {
				dp[j] = (dp[j] + dp[j - 1]) % 10007;
			}
		}

		System.out.println(dp[k]);

		sc.close();
	}
}