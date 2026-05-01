import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		// 빠른 입출력을 위한 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;

		int n = Integer.parseInt(line.trim());
		if (n == 0) {
			System.out.println(0);
			return;
		}

		// 데드라인 배열 입력
		int[] deadlines = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			deadlines[i] = Integer.parseInt(st.nextToken());
		}

		// 이익 배열 입력 (이미 내림차순 정렬되어 있다고 가정)
		int[] profits = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			profits[i] = Integer.parseInt(st.nextToken());
		}

		// 작업 객체 배열 생성
		Job[] jobs = new Job[n];
		for (int i = 0; i < n; i++) {
			jobs[i] = new Job(deadlines[i], profits[i]);
		}

		// 선택된 작업들을 저장할 리스트 J
		List<Job> J = new ArrayList<>();
		J.add(jobs[0]); // 첫 번째(가장 이익이 큰) 작업은 무조건 선택

		// 2번째 작업부터 순회 (Algorithm 4.4 로직)
		for (int i = 1; i < n; i++) {
			Job currentJob = jobs[i];

			// 타당성을 테스트하기 위한 임시 리스트 K
			List<Job> K = new ArrayList<>(J);

			// currentJob을 데드라인 오름차순에 맞게 K에 삽입
			int insertPos = 0;
			// '<=' 조건을 사용함으로써 동일한 데드라인일 경우
			// 먼저 들어온(이익이 높은) 작업 뒤에 위치하게 하여 알고리즘 선택 순서를 유지합니다.
			while (insertPos < K.size() && K.get(insertPos).deadline <= currentJob.deadline) {
				insertPos++;
			}
			K.add(insertPos, currentJob);

			// K 집합이 타당한지(Feasible) 검사
			boolean isFeasible = true;
			for (int j = 0; j < K.size(); j++) {
				// 인덱스 j에 있는 작업은 최소한 j+1의 시간이 필요함
				if (K.get(j).deadline < j + 1) {
					isFeasible = false;
					break; // 하나라도 데드라인을 맞추지 못하면 실패
				}
			}

			// 타당하다면 임시 리스트 K를 확정 리스트 J로 갱신
			if (isFeasible) {
				J = K;
			}
		}

		// 결과 계산 및 출력
		int totalProfit = 0;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < J.size(); i++) {
			totalProfit += J.get(i).profit;
			sb.append(J.get(i).profit).append(i == J.size() - 1 ? "" : " ");
		}

		// 첫째 줄에 최대 이익 출력
		System.out.println(totalProfit);
		// 둘째 줄에 선택된 작업들의 이익 출력 (데드라인 오름차순)
		System.out.println(sb.toString());
	}

	// 작업을 표현하는 클래스
	static class Job {
		int deadline;
		int profit;

		public Job(int deadline, int profit) {
			this.deadline = deadline;
			this.profit = profit;
		}
	}
}