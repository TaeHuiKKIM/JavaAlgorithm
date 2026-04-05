import java.util.Scanner;

class Main {
	public static int fibCount = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int count = 0;
		System.out.println(fib(n));
		System.out.print(fibCount);
	}

	public static int fib(int n) {
		fibCount++;
		if (n == 0) return 0;
		else if (n == 1) return 1;
		else return (fib(n - 1) + fib(n - 2)) % 1000000;
	}
}