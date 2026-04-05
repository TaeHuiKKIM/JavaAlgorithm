import java.util.Scanner;

public class Main {
	static int swapCount = 0;

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		swapCount++;
	}

	public static int partition(int[] arr, int low, int high) {
		int pivotitem = arr[low];
		int j = low;
		for (int i = low + 1; i <= high; i++) {
			if (arr[i] < pivotitem) {
				j++;
				swap(arr, i, j);
			}
		}
		int pivotpoint = j;
		swap(arr, low, pivotpoint);
		return pivotpoint;
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pivotpoint = partition(arr, low, high);
			quickSort(arr, low, pivotpoint - 1);
			quickSort(arr, pivotpoint + 1, high);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if (!sc.hasNextInt()) return;
		int n = sc.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		quickSort(arr, 0, n - 1);

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + (i == n - 1 ? "" : " "));
		}
		System.out.println();
		System.out.println(swapCount);

		sc.close();
	}
}