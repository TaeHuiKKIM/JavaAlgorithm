import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 입력 처리
		String line = br.readLine();
		if (line == null) return;
		int n = Integer.parseInt(line.trim());

		int[] keys = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) keys[i] = Integer.parseInt(st.nextToken());

		int[] p = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) p[i] = Integer.parseInt(st.nextToken());

		// 빈도의 누적 합 계산 (구간 합을 빠르게 구하기 위함)
		int[] S = new int[n + 1];
		for (int i = 1; i <= n; i++) S[i] = S[i - 1] + p[i];

		// 2. DP 테이블 초기화
		long[][] A = new long[n + 2][n + 1];
		int[][] R = new int[n + 2][n + 1];

		for (int i = 1; i <= n; i++) {
			A[i][i - 1] = 0;
			R[i][i - 1] = 0;
			A[i][i] = p[i];
			R[i][i] = i;
		}
		A[n + 1][n] = 0;
		R[n + 1][n] = 0;

		// 3. Algorithm 3.9: Optimal BST 계산
		for (int diagonal = 1; diagonal < n; diagonal++) {
			for (int i = 1; i <= n - diagonal; i++) {
				int j = i + diagonal;
				A[i][j] = Long.MAX_VALUE;
				long sumP = S[j] - S[i - 1]; // i번부터 j번 키까지의 빈도 합
				for (int k = i; k <= j; k++) {
					long cost = A[i][k - 1] + A[k + 1][j] + sumP;
					if (cost < A[i][j]) {
						A[i][j] = cost;
						R[i][j] = k; // 최적의 루트 노드 인덱스 저장
					}
				}
			}
		}

		// 4. 출력 생성 (StringBuilder 사용으로 성능 최적화)
		StringBuilder sb = new StringBuilder();

		// 테이블 A 출력 (i행부터 n열까지의 대각선 정보)
		for (int i = 1; i <= n + 1; i++) {
			for (int j = i - 1; j <= n; j++) {
				sb.append(A[i][j]);
				if (j < n) sb.append(" ");
			}
			sb.append("\n");
		}

		// 테이블 R 출력 (i행부터 n열까지의 대각선 정보)
		for (int i = 1; i <= n + 1; i++) {
			for (int j = i - 1; j <= n; j++) {
				sb.append(R[i][j]);
				if (j < n) sb.append(" ");
			}
			sb.append("\n");
		}

		// 최적값 출력
		sb.append(A[1][n]).append("\n");

		// 최적해의 전위 순회(Preorder) 결과 생성
		List<String> preOrderList = new ArrayList<>();
		getPreorder(1, n, R, keys, preOrderList);
		sb.append(String.join(" ", preOrderList)).append("\n");

		// 최적해의 중위 순회(Inorder) 결과 생성
		List<String> inOrderList = new ArrayList<>();
		getInorder(1, n, R, keys, inOrderList);
		sb.append(String.join(" ", inOrderList)).append("\n");

		System.out.print(sb.toString());
	}

	// 전위 순회 재귀 함수
	private static void getPreorder(int i, int j, int[][] R, int[] keys, List<String> res) {
		if (i > j) return;
		int k = R[i][j];
		res.add(String.valueOf(keys[k]));
		getPreorder(i, k - 1, R, keys, res);
		getPreorder(k + 1, j, R, keys, res);
	}

	// 중위 순회 재귀 함수
	private static void getInorder(int i, int j, int[][] R, int[] keys, List<String> res) {
		if (i > j) return;
		int k = R[i][j];
		getInorder(i, k - 1, R, keys, res);
		res.add(String.valueOf(keys[k]));
		getInorder(k + 1, j, R, keys, res);
	}
}