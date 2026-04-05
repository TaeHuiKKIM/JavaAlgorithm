import java.util.Scanner;

public class Main {
	static int[][] board;
	static String encoded;
	static int index = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) return;
		int n = sc.nextInt();
		encoded = sc.next();

		board = new int[n][n];

		decode(0, 0, n);

		System.out.println(n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(board[i][j]);
				if (j != n - 1) sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void decode(int r, int c, int size) {
		if (index >= encoded.length()) return;

		char current = encoded.charAt(index++);

		if (current == 'w') {
			fill(r, c, size, 0);
		} else if (current == 'b') {
			fill(r, c, size, 1);
		} else if (current == 'x') {
			int half = size / 2;
			decode(r, c, half);           // 1사분면
			decode(r, c + half, half);    // 2사분면
			decode(r + half, c, half);    // 3사분면
			decode(r + half, c + half, half); // 4사분면
		}
	}

	public static void fill(int r, int c, int size, int value) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				board[i][j] = value;
			}
		}
	}
}