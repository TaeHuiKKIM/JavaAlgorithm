import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int searchCount;

	public static int binsearch2(int[] arr, int key, int low, int high) {
		searchCount++;

		if (low > high) {
			return 0;
		}

		int mid = (low + high) / 2;

		if (arr[mid] == key) return mid + 1;
		else if (arr[mid] > key) return binsearch2(arr, key, low, mid - 1);
		else return binsearch2(arr, key, mid + 1, high);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		Arrays.sort(arr);

		int[] searchKeys = new int[m];
		for (int i = 0; i < m; i++) {
			searchKeys[i] = sc.nextInt();
		}
		for (int i = 0; i < m; i++) {
			searchCount = 0;
			int resultIndex = binsearch2(arr, searchKeys[i], 0, n - 1);

			if (resultIndex == 0)
				System.out.println(searchCount + " " + 0);
			else
				System.out.println(searchCount + " " + resultIndex);
		}
	}
}