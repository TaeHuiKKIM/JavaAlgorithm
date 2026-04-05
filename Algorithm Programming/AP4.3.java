import java.util.Scanner;
import java.math.BigInteger;

public class Main {
	static final long MOD = 10007;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNext()) return;

		BigInteger n = new BigInteger(sc.next());

		if (n.equals(BigInteger.ZERO)) {
			System.out.println(0);
			return;
		}
		if (n.equals(BigInteger.ONE)) {
			System.out.println(1);
			return;
		}

		long[][] base = {{1, 1}, {1, 0}};
		long[][] result = power(base, n);

		System.out.println(result[0][1]);
	}

	public static long[][] power(long[][] a, BigInteger exp) {
		long[][] res = {{1, 0}, {0, 1}}; // 단위 행렬
		long[][] base = a;

		while (exp.compareTo(BigInteger.ZERO) > 0) {
			if (exp.remainder(BigInteger.valueOf(2)).equals(BigInteger.ONE)) {
				res = multiply(res, base);
			}
			base = multiply(base, base);
			exp = exp.divide(BigInteger.valueOf(2));
		}
		return res;
	}

	public static long[][] multiply(long[][] a, long[][] b) {
		long[][] c = new long[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % MOD;
				}
			}
		}
		return c;
	}
}