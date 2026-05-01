import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;

		int n = Integer.parseInt(line.trim());

		if (n == 0) {
			System.out.println(0);
			return;
		}

		Meeting[] meetings = new Meeting[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end);
		}

		List<Meeting> selected = new ArrayList<>();
		selected.add(meetings[0]);
		int lastEndTime = meetings[0].end;

		for (int i = 1; i < n; i++) {
			if (meetings[i].start >= lastEndTime) {
				selected.add(meetings[i]);
				lastEndTime = meetings[i].end;
			}
		}

		System.out.println(selected.size());
		StringBuilder sb = new StringBuilder();
		for (Meeting m : selected) {
			sb.append(m.start).append(" ").append(m.end).append("\n");
		}
		System.out.print(sb.toString());
	}

	static class Meeting {
		int start;
		int end;

		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}