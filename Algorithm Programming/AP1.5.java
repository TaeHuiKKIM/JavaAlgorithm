import java.util.Arrays;
import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int[] number = new int[n];
		int[] search = new int[m];
		for (int i = 0; i < n; i++) {
			number[i] = scan.nextInt();
		}
		for (int i = 0; i < m; i++) {
			search[i] = scan.nextInt();
		}
		Arrays.sort(number);
		for (int i = 0; i < m; i++) {
			int flag = binarySearch(number, search[i], n);
			if (flag == -1) System.out.println(search[i] + " is not in S.");
			else System.out.println(search[i] + " is in " + (flag + 1) + ".");
		}
	}

	public static int binarySearch(int[] number, int find, int n) {
		int low, mid, high;
		low = 0;
		high = n - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (find == number[mid]) return mid;
			else if (find < number[mid]) high = mid - 1;
			else low = mid + 1;
		}
		return -1;
	}
}