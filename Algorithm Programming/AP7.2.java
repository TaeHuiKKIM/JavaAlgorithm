import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int[] parent;

	static int find(int i) {
		if (parent[i] == i) return i;
		return parent[i] = find(parent[i]);
	}

	static void union(int i, int j) {
		int rootI = find(i);
		int rootJ = find(j);
		if (rootI != rootJ) {
			parent[rootI] = rootJ;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;

		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(u, v, w));
		}

		Collections.sort(edges);

		for (Edge e : edges) {
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				System.out.println(e.u + " " + e.v + " " + e.w);
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int u, v, w;

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
}