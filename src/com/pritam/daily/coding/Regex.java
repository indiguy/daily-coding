/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Implement regular expression matching with the following special characters:
 * 
 * . (period) which matches any single character (asterisk) which matches zero
 * or more of the preceding element That is, implement a function that takes in
 * a string and a valid regular expression and returns whether or not the string
 * matches the regular expression.
 * 
 * For example, given the regular expression "ra." and the string "ray", your
 * function should return true. The same regular expression on the string
 * "raymond" should return false.
 * 
 * Given the regular expression ".*at" and the string "chat", your function
 * should return true. The same regular expression on the string "chats" should
 * return false.
 * 
 * @author pribiswas
 *
 */
public class Regex {
	private static final String PERIOD = ".";
	private static final String ASTERISK = "*";

	private static boolean matches(String str, String pattern) {
		if (pattern.contains(PERIOD)) {
			return oneEditAway(pattern, str);
		} else if (pattern.contains(ASTERISK)) {
			return wildEditAway(pattern, str);
		}
		return false;
	}

	private static boolean wildEditAway(String first, String second) {
		if (first == null || second == null) {
			return false;
		}
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i) && first.charAt(i) != ASTERISK.charAt(0)) {
				return false;
			} else if (first.charAt(i) != second.charAt(i) && first.charAt(i) == ASTERISK.charAt(0)) {
				if (i == first.length() - 1) {
					return true;
				} else {
					return second.endsWith(first.substring(i + 1));
				}
			}
		}
		return false;
	}

	private static boolean oneEditAway(String first, String second) {
		if (first == null || second == null) {
			return false;
		}
		if (first.length() != second.length()) {
			return false;
		}
		boolean diffFound = false;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i)) {
				if (diffFound) {
					return false;
				} else {
					diffFound = true;
				}
			}
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(matches("Pritam", "Prita."));

	}

}
