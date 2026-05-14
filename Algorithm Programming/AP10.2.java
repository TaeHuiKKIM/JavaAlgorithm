import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, W;
	static int[] w;
	static String[] include;
	static List<String> results = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNextInt()) return;

		n = sc.nextInt();
		W = sc.nextInt();

		// 인덱스 초과 에러 방지를 위해 n+2로 크기 지정
		w = new int[n + 2];
		int total = 0;
		for (int i = 1; i <= n; i++) {
			w[i] = sc.nextInt();
			total += w[i];
		}
		include = new String[n + 2];

		sum_of_subsets(0, 0, total);

		System.out.println(results.size());
		for (String res : results) {
			System.out.println(res);
		}
		sc.close();
	}

	static void sum_of_subsets(int i, int weight, int total) {
		if (promising(i, weight, total)) {
			if (weight == W) {
				StringBuilder sb = new StringBuilder();
				for (int j = 1; j <= i; j++) {
					if ("yes".equals(include[j])) {
						sb.append(w[j]).append(" ");
					}
				}
				results.add(sb.toString().trim());
			} else {
				include[i + 1] = "yes";
				sum_of_subsets(i + 1, weight + w[i + 1], total - w[i + 1]);
				include[i + 1] = "no";
				sum_of_subsets(i + 1, weight, total - w[i + 1]);
			}
		}
	}

	static boolean promising(int i, int weight, int total) {
		return (weight + total >= W) && (weight == W || weight + w[i + 1] <= W);
	}
}