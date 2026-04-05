import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	static int prodCount = 0;
	static int threshold;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		if (!sc.hasNextInt()) return;
		threshold = sc.nextInt();

		String strU = sc.next();
		String strV = sc.next();

		BigInteger u = new BigInteger(strU);
		BigInteger v = new BigInteger(strV);

		BigInteger result = prod(u, v);

		System.out.println(prodCount);
		System.out.println(result);
	}

	public static BigInteger prod(BigInteger u, BigInteger v) {
		prodCount++;

		int n = Math.max(u.toString().length(), v.toString().length());

		if (u.equals(BigInteger.ZERO) || v.equals(BigInteger.ZERO) || n <= threshold) {
			return u.multiply(v);
		}

		int m = n / 2;
		BigInteger p = BigInteger.TEN.pow(m);

		BigInteger x = u.divide(p);
		BigInteger y = u.remainder(p);
		BigInteger w = v.divide(p);
		BigInteger z = v.remainder(p);

		BigInteger r1 = prod(x, w);
		BigInteger r2 = prod(x, z);
		BigInteger r3 = prod(y, w);
		BigInteger r4 = prod(y, z);

		BigInteger res1 = r1.multiply(BigInteger.TEN.pow(2 * m));
		BigInteger res2 = (r2.add(r3)).multiply(p);
		BigInteger res3 = r4;

		return res1.add(res2).add(res3);
	}
}