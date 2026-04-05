import java.util.Scanner;

public class Main {
	static final int MOD = 1000;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		long K = sc.nextLong(); // 지수 K는 매우 클 수 있으므로 long 타입을 사용합니다.

		long[][] matrix = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 입력받을 때부터 MOD로 나누어 저장합니다.
				matrix[i][j] = sc.nextLong() % MOD;
			}
		}

		// 행렬 거듭제곱 수행
		long[][] result = power(matrix, K);

		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]);
				if (j != N - 1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	// 행렬의 거듭제곱을 재귀적으로 계산하는 함수 (분할 정복)
	public static long[][] power(long[][] a, long exp) {
		// 지수가 1이면 원본 행렬을 반환 (이미 MOD 연산이 되어 있음)
		if (exp == 1) {
			return a;
		}

		// exp를 절반으로 나누어 계산: A^exp = (A^(exp/2))^2
		long[][] half = power(a, exp / 2);
		long[][] res = multiply(half, half);

		// 만약 지수가 홀수라면 원본 행렬(a)을 한 번 더 곱해줍니다.
		if (exp % 2 == 1) {
			res = multiply(res, a);
		}

		return res;
	}

	// 두 행렬의 곱을 계산하는 함수
	public static long[][] multiply(long[][] a, long[][] b) {
		long[][] res = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					res[i][j] += a[i][k] * b[k][j];
					res[i][j] %= MOD; // 중간 과정에서 계속 MOD 연산을 수행합니다.
				}
			}
		}
		return res;
	}
}