import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		if (line == null || line.trim().isEmpty()) return;

		long n = Long.parseUnsignedLong(line.trim());

		long[] fib = new long[100];
		fib[0] = 1;
		fib[1] = 2;
		int len = 2;

		while (true) {
			long next = fib[len - 1] + fib[len - 2];
			if (Long.compareUnsigned(next, fib[len - 1]) < 0) {
				break;
			}
			fib[len++] = next;
		}

		List<Long> result = new ArrayList<>();
		for (int i = len - 1; i >= 0; i--) {
			if (Long.compareUnsigned(n, fib[i]) >= 0) {
				result.add(fib[i]);
				n -= fib[i];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = result.size() - 1; i >= 0; i--) {
			sb.append(Long.toUnsignedString(result.get(i))).append("\n");
		}
		System.out.print(sb.toString());
	}
}