import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;

		int n = Integer.parseInt(line.trim());

		char[] chars = new char[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			chars[i] = st.nextToken().charAt(0);
		}

		int[] freqs = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			freqs[i] = Integer.parseInt(st.nextToken());
		}

		List<Node> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new Node(chars[i], freqs[i]));
		}

		list.sort(Comparator.comparingInt(a -> a.freq));

		while (list.size() > 1) {
			Node left = list.remove(0);
			Node right = list.remove(0);
			Node parent = new Node('+', left.freq + right.freq);
			parent.left = left;
			parent.right = right;

			int insertIdx = 0;
			while (insertIdx < list.size() && list.get(insertIdx).freq <= parent.freq) {
				insertIdx++;
			}
			list.add(insertIdx, parent);
		}

		Node root = list.get(0);

		List<String> preList = new ArrayList<>();
		preorder(root, preList);
		System.out.println(String.join(" ", preList));

		List<String> inList = new ArrayList<>();
		inorder(root, inList);
		System.out.println(String.join(" ", inList));

		Map<Character, String> codeMap = new HashMap<>();
		generateCodes(root, "", codeMap);

		int m = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < m; i++) {
			String s = br.readLine().trim();
			StringBuilder encoded = new StringBuilder();
			for (char c : s.toCharArray()) {
				encoded.append(codeMap.get(c));
			}
			System.out.println(encoded.toString());
		}

		int k = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < k; i++) {
			String bits = br.readLine().trim();
			StringBuilder decoded = new StringBuilder();
			Node curr = root;
			for (int j = 0; j < bits.length(); j++) {
				if (bits.charAt(j) == '0') {
					curr = curr.left;
				} else {
					curr = curr.right;
				}

				if (curr.left == null && curr.right == null) {
					decoded.append(curr.c);
					curr = root;
				}
			}
			System.out.println(decoded.toString());
		}
	}

	static void preorder(Node node, List<String> list) {
		if (node == null) return;
		list.add(node.c + ":" + node.freq);
		preorder(node.left, list);
		preorder(node.right, list);
	}

	static void inorder(Node node, List<String> list) {
		if (node == null) return;
		inorder(node.left, list);
		list.add(node.c + ":" + node.freq);
		inorder(node.right, list);
	}

	static void generateCodes(Node node, String code, Map<Character, String> map) {
		if (node == null) return;
		if (node.left == null && node.right == null) {
			map.put(node.c, code);
			return;
		}
		generateCodes(node.left, code + "0", map);
		generateCodes(node.right, code + "1", map);
	}

	static class Node {
		char c;
		int freq;
		Node left, right;

		Node(char c, int freq) {
			this.c = c;
			this.freq = freq;
		}
	}
}