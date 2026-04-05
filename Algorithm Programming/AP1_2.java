import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int sum = 0;
		for (int i = 0; i < N; i++) sum += scan.nextInt();
		System.out.println(sum);
	}
}