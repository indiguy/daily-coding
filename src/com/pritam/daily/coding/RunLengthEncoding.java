/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Run-length encoding is a fast and simple method of encoding strings. The
 * basic idea is to represent repeated successive characters as a single count
 * and character. For example, the string "AAAABBBCCDAA" would be encoded as
 * "4A3B2C1D2A".
 * 
 * Implement run-length encoding and decoding. You can assume the string to be
 * encoded have no digits and consists solely of alphabetic characters. You can
 * assume the string to be decoded is valid.
 * 
 * @author pribiswas
 *
 */
public class RunLengthEncoding {

	private static String encode(String decoded) {
		int count = 1;
		final StringBuilder builder = new StringBuilder();
		for (int i = 1; i < decoded.length(); i++) {
			if (decoded.charAt(i) == decoded.charAt(i - 1)) {
				count++;
			} else {
				builder.append(count).append(decoded.charAt(i - 1));
				count = 1;
			}
		}
		return builder.toString();
	}

	private static String decode(String encoded) {
		StringBuilder builder = new StringBuilder();
		int currentDigit = 0;
		for (int i = 0; i < encoded.length(); i++) {
			char ch = encoded.charAt(i);
			if (Character.isDigit(ch)) {
				currentDigit = Integer.parseInt(String.valueOf(ch));
			} else {
				for (int j = 0; j < currentDigit; j++) {
					builder.append(ch);
				}
			}
		}
		return builder.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String initial = "AAAABBBCCDAA";
		String encoded = encode(initial);
		System.out.println(encoded);
		String decoded = decode(encoded);
		System.out.println(decoded);
		assert initial.equals(decoded);

	}

}
