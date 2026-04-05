import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();

		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = scan.nextInt();
		}

		int[] search = new int[M];
		for (int i = 0; i < M; i++) {
			search[i] = scan.nextInt();
		}

		for (int i = 0; i < M; i++) {
			int target = search[i];
			int position = -1;

			for (int j = 0; j < N; j++) {
				if (numbers[j] == target) {
					position = j + 1;
					break;
				}
			}
			if (position != -1) {
				System.out.println(target + " is in " + position + ".");
			} else {
				System.out.println(target + " is not in S.");
			}
		}
	}
}