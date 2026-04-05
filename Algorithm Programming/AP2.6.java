import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int max = 0, count = 0, maxi = 0;

		for (int i = n; i <= m; i++) {
			count = 0;
			int temp = i;

			while (true) {
				count++;
				if (temp == 1) break;
				if (temp % 2 == 0) {
					temp /= 2;
				} else {
					temp = temp * 3 + 1;
				}
			}

			if (max < count) {
				maxi = i;
				max = count;
			}
		}
		System.out.println(maxi + " " + max);
		while (true) {
			System.out.print(maxi);
			if (maxi == 1) break;
			System.out.print(" ");
			if (maxi % 2 == 0) {
				maxi /= 2;
			} else {
				maxi = maxi * 3 + 1;
			}
		}
	}
}