package annas.math;

public class BaseConverter {

	/**
	 * Converts from Decimal to the base provided
	 * @param n Number to convert
	 * @param base Base (not to exceed 36, runs out of character)
	 * @return String representation of the number
	 */
	public static String toString(int n, int base) {
		// special case
		if (n == 0)
			return "0";

		String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s = "";
		while (n > 0) {
			int d = n % base;
			s = digits.charAt(d) + s;
			n = n / base;
		}
		return s;
	}

	/**
	 * Converts to Binary
	 * @param n Number to convert
	 * @return String representation of n presented in binary
	 */
	public static String toBinaryString(int n) {
		return toString(n, 2);
	}

	/**
	 * Converts to Hex
	 * @param n Number to convert
	 * @return String representation of n presented in Hex
	 */
	public static String toHexString(int n) {
		return toString(n, 16);
	}

	static void inputError(String s) {
		throw new RuntimeException("Input error with" + s);
	}

	/**
	 * Converts a String representing a number to an int
	 * @param s String representing the number
	 * @param b Base
	 * @return
	 */
	static int fromString(String s, int b) {
		int result = 0;
		int digit = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9')
				digit = c - '0';
			else if (c >= 'A' && c <= 'Z')
				digit = 10 + c - 'A';
			else
				inputError(s);

			if (digit < b)
				result = b * result + digit;
			else
				inputError(s);
		}
		return result;
	}

	public static int fromBinaryString(String s) {
		return fromString(s, 2);
	}

	public static int fromHexString(String s) {
		return fromString(s, 16);
	}int n = 47;
	

}
