import java.util.Scanner;

class Main {
	private static int swap(int[] arr, int idx1, int idx2, int count) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
		return count + 1;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int count = 0;
		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scan.nextInt();
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (numbers[i] > numbers[j]) count = swap(numbers, i, j, count);
			}
		}
		for (int i = 0; i < n; i++) {
			System.out.print(numbers[i]);
			if (i < n - 1) {
				System.out.print(" ");
			}
		}
		System.out.println();
		System.out.println(count);
	}
}