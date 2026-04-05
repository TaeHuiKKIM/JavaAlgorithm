import java.util.Scanner;

public class Main {
	static int count = 0;

	public static void merge2(int[] arr, int low, int mid, int high) {
		count++;
		int i = low;
		int j = mid + 1;
		int k = low;

		int[] temp = new int[arr.length];
		for (int l = low; l <= high; l++) {
			temp[l] = arr[l];
		}

		while (i <= mid && j <= high) {
			if (temp[i] <= temp[j]) {
				arr[k++] = temp[i++];
			} else {
				arr[k++] = temp[j++];
			}
		}
		while (i <= mid) {
			arr[k++] = temp[i++];
		}

		while (j <= high) {
			arr[k++] = temp[j++];
		}
	}

	public static void mergeSort(int[] arr, int low, int high) {
		if (low < high) {
			int mid = low + (high - low) / 2;

			mergeSort(arr, low, mid);
			mergeSort(arr, mid + 1, high);
			merge2(arr, low, mid, high);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		mergeSort(arr, 0, n - 1);

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + (i == n - 1 ? "" : " "));
		}
		System.out.println();

		System.out.println(count);

	}
}