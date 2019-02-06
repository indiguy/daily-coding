/**
 *
 */
package com.pritam.daily.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, how many distinct substrings of s are palindromes?
 *
 * @author pribiswas
 *
 */
public class SubPalindrome {

	private static int palindromes(String str) {
		int counter = 0;
		Map<String, Boolean> memo = new HashMap<>();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				String subStr = str.substring(i, j);
				if (!memo.containsKey(subStr) && isPalindrome(subStr)) {
					memo.put(subStr, true);
					counter++;
					while (subStr.length() > 3) {
						subStr = subStr.substring(1, subStr.length() - 1);
						memo.put(subStr, true);
						counter++;
					}
				}
			}
		}
		return counter;
	}

	private static boolean isPalindrome(String str) {
		int len = str.length();
		if (len == 1)
			return true;
		int start = 0, end = len - 1;
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(palindromes("mokkorokkoi"));

	}

}
