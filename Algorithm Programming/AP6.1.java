import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 1. 입력 처리: 행렬의 개수 N을 입력받음
		if (!sc.hasNextInt()) return;
		int n = sc.nextInt();

		// 2. 행렬 차원 입력: d0, d1, ..., dn (n개의 행렬이면 n+1개의 숫자가 필요함)
		// 행렬 Ai의 크기는 d[i-1] x d[i] 입니다.
		int[] d = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			if (sc.hasNextInt()) {
				d[i] = sc.nextInt();
			}
		}

		// M[i][j]: 행렬 Ai부터 Aj까지 곱할 때 드는 최소 곱셈 연산 횟수 저장
		// P[i][j]: Ai부터 Aj까지 최적으로 나뉘는 지점(k)을 저장 (괄호 위치 결정용)
		int[][] M = new int[n + 1][n + 1];
		int[][] P = new int[n + 1][n + 1];

		// 3. Algorithm 3.6: 최적 곱셈 횟수 계산 (Dynamic Programming)
		// diagonal(대각선) 거리를 1부터 n-1까지 늘려가며 계산 (부분 문제의 크기 증가)
		for (int diagonal = 1; diagonal < n; diagonal++) {
			for (int i = 1; i <= n - diagonal; i++) {
				int j = i + diagonal; // i부터 j까지의 행렬 구간
				M[i][j] = Integer.MAX_VALUE; // 최소값을 찾기 위해 무한대로 초기화

				// k는 i와 j 사이를 나누는 기준점 (Ai...Ak) * (Ak+1...Aj)
				for (int k = i; k < j; k++) {
					// 점화식: (왼쪽 구간 비용) + (오른쪽 구간 비용) + (두 덩어리를 곱하는 비용)
					int cost = M[i][k] + M[k + 1][j] + d[i - 1] * d[k] * d[j];

					// 기존 값보다 작으면 최소 비용 갱신 및 나뉘는 지점 k 저장
					if (cost < M[i][j]) {
						M[i][j] = cost;
						P[i][j] = k;
					}
				}
			}
		}

		// 4. 출력 1: 최소 비용 행렬 M의 상삼각 영역 출력
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				System.out.print(M[i][j]);
				if (j < n) System.out.print(" "); // 줄 끝 공백 방지
			}
			System.out.println();
		}

		// 5. 출력 2: 최적 분할 지점 행렬 P의 상삼각 영역 출력
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				System.out.print(P[i][j]);
				if (j < n) System.out.print(" "); // 줄 끝 공백 방지
			}
			System.out.println();
		}

		// 6. 출력 3: 전체 행렬(1번부터 n번까지)의 최소 곱셈 횟수(최적값) 출력
		System.out.println(M[1][n]);

		// 7. 출력 4: 최적의 곱셈 순서(괄호 구조) 출력
		System.out.println(getOptimalOrder(1, n, P));

		sc.close();
	}

	/**
	 * Algorithm 3.7: 최적의 곱셈 순서를 재귀적으로 문자열로 구성하는 함수
	 *
	 * @param i 시작 행렬 인덱스
	 * @param j 끝 행렬 인덱스
	 * @param P 최적 분할 지점 정보가 담긴 행렬
	 * @return 괄호가 포함된 최적 순서 문자열
	 */
	private static String getOptimalOrder(int i, int j, int[][] P) {
		// 기저 사례: 행렬이 하나뿐일 때 (A1, A2, ...)
		if (i == j) {
			return "(A" + i + ")";
		} else {
			// 저장된 최적 분할 지점 k를 기준으로 나누어 재귀 호출
			int k = P[i][j];
			return "(" + getOptimalOrder(i, k, P) + getOptimalOrder(k + 1, j, P) + ")";
		}
	}
}