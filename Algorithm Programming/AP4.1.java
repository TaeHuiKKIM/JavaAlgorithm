import java.util.Scanner;

public class Main {
	static int strassenCount = 0;
	static int threshold;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		threshold = sc.nextInt();

		int newN = 1;
		while (newN < n) newN *= 2;

		int[][] a = new int[newN][newN];
		int[][] b = new int[newN][newN];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				a[i][j] = sc.nextInt();

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				b[i][j] = sc.nextInt();

		int[][] result = strassen(newN, a, b);

		System.out.println(strassenCount);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(result[i][j]).append(j == n - 1 ? "" : " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	public static int[][] strassen(int n, int[][] a, int[][] b) {
		strassenCount++;

		if (n <= threshold) {
			return multiply(a, b, n);
		}

		int m = n / 2;
		int[][] res = new int[n][n];

		int[][] a11 = divide(a, 0, 0, m);
		int[][] a12 = divide(a, 0, m, m);
		int[][] a21 = divide(a, m, 0, m);
		int[][] a22 = divide(a, m, m, m);

		int[][] b11 = divide(b, 0, 0, m);
		int[][] b12 = divide(b, 0, m, m);
		int[][] b21 = divide(b, m, 0, m);
		int[][] b22 = divide(b, m, m, m);

		int[][] m1 = strassen(m, add(a11, a22, m), add(b11, b22, m));
		int[][] m2 = strassen(m, add(a21, a22, m), b11);
		int[][] m3 = strassen(m, a11, sub(b12, b22, m));
		int[][] m4 = strassen(m, a22, sub(b21, b11, m));
		int[][] m5 = strassen(m, add(a11, a12, m), b22);
		int[][] m6 = strassen(m, sub(a21, a11, m), add(b11, b12, m));
		int[][] m7 = strassen(m, sub(a12, a22, m), add(b21, b22, m));

		int[][] c11 = add(sub(add(m1, m4, m), m5, m), m7, m);
		int[][] c12 = add(m3, m5, m);
		int[][] c21 = add(m2, m4, m);
		int[][] c22 = add(sub(add(m1, m3, m), m2, m), m6, m);

		copy(c11, res, 0, 0, m);
		copy(c12, res, 0, m, m);
		copy(c21, res, m, 0, m);
		copy(c22, res, m, m, m);

		return res;
	}

	public static int[][] multiply(int[][] a, int[][] b, int n) {
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < n; k++)
					res[i][j] += a[i][k] * b[k][j];
		return res;
	}

	public static int[][] add(int[][] a, int[][] b, int n) {
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				res[i][j] = a[i][j] + b[i][j];
		return res;
	}

	public static int[][] sub(int[][] a, int[][] b, int n) {
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				res[i][j] = a[i][j] - b[i][j];
		return res;
	}

	public static int[][] divide(int[][] p, int row, int col, int n) {
		int[][] res = new int[n][n];
		for (int i = 0; i < n; i++)
			System.arraycopy(p[i + row], col, res[i], 0, n);
		return res;
	}

	public static void copy(int[][] src, int[][] dest, int row, int col, int n) {
		for (int i = 0; i < n; i++)
			System.arraycopy(src[i], 0, dest[i + row], col, n);
	}
}