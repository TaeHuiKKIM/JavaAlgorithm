import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static int n;
	static int[] col;
	static int count = 0;
	static String maxColString = "";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNextInt()) return;
		n = sc.nextInt();
		col = new int[n + 1];

		queens(0);

		System.out.println(count);
		if (count > 0) {
			System.out.println(maxColString);
		}
		sc.close();
	}

	static void queens(int i) {
		if (promising(i)) {
			if (i == n) {
				count++;
				StringBuilder sb = new StringBuilder();
				for (int j = 1; j <= n; j++) {
					sb.append(col[j]);
				}
				String currentString = sb.toString();

				// 가장 큰 수 찾기
				if (maxColString.equals("")) {
					maxColString = currentString;
				} else {
					BigInteger current = new BigInteger(currentString);
					BigInteger max = new BigInteger(maxColString);
					if (current.compareTo(max) > 0) {
						maxColString = currentString;
					}
				}
			} else {
				for (int j = 1; j <= n; j++) {
					col[i + 1] = j;
					queens(i + 1);
				}
			}
		}
	}

	static boolean promising(int i) {
		int k = 1;
		boolean switchBool = true;
		while (k < i && switchBool) {
			if (col[i] == col[k] || Math.abs(col[i] - col[k]) == i - k) {
				switchBool = false;
			}
			k++;
		}
		return switchBool;
	}
}