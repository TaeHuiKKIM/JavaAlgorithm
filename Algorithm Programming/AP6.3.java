import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 빠른 입력을 위한 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 두 문자열 입력 받기
		String x = br.readLine();
		String y = br.readLine();

		// 입력값이 비어있는 경우 예외 처리
		if (x == null || y == null) return;

		int m = x.length();
		int n = y.length();

		// dp[i][j]는 x의 i번째, y의 j번째 문자까지의 LCS 길이를 저장
		// (0번 인덱스는 공백 문자열을 의미하므로 크기를 +1 함)
		int[][] dp = new int[m + 1][n + 1];

		// 1. DP 테이블 채우기 (Bottom-Up)
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// 두 문자가 같은 경우: 대각선 위 값 + 1
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				// 두 문자가 다른 경우: 위쪽(i-1)과 왼쪽(j-1) 중 큰 값 선택
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// 최장 공통 부분 수열의 길이 출력
		System.out.println(dp[m][n]);

		// 2. 실제 LCS 문자열 추적 (Backtracking)
		if (dp[m][n] > 0) {
			StringBuilder sb = new StringBuilder();
			int i = m;
			int j = n;

			// 테이블의 오른쪽 아래 끝에서 시작하여 역추적
			while (i > 0 && j > 0) {
				// 두 문자가 같으면 해당 문자를 LCS에 포함시키고 대각선 위로 이동
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					sb.append(x.charAt(i - 1));
					i--;
					j--;
				}
				// 두 문자가 다를 경우, 더 큰 값을 가진 셀로 이동
				// 위쪽 값(dp[i-1][j])이 왼쪽 값(dp[i][j-1])보다 크거나 같으면 위로 이동
				// (샘플 1의 "BCBA" 결과를 얻기 위해 >= 조건을 사용)
				else if (dp[i - 1][j] >= dp[i][j - 1]) {
					i--;
				}
				// 왼쪽 값이 더 크면 왼쪽으로 이동
				else {
					j--;
				}
			}

			// 역순으로 저장되었으므로 뒤집어서 출력
			System.out.println(sb.reverse().toString());
		}
	}
}