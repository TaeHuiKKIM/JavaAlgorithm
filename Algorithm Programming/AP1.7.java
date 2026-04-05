import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] fib = new int[n + 1];
		fib[0] = 0;
		if (n > 0) fib[1] = 1;
		for (int i = 2; i <= n; i++) {
			fib[i] = (fib[i - 1] + fib[i - 2]) % 1000000;
		}
		System.out.print(fib[n]);
	}
}