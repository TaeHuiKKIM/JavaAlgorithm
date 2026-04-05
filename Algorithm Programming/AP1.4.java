import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[][] mat1 = new int[n][n];
		int[][] mat2 = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat1[i][j] = scan.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mat2[i][j] = scan.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = 0;
				for (int k = 0; k < n; k++) {
					sum += mat1[i][k] * mat2[k][j];
				}
				System.out.print(sum);
				if (j < n - 1) System.out.print(" ");
			}
			System.out.println();
		}
	}
}