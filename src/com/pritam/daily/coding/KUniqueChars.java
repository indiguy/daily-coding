/**
 * 
 */
package com.pritam.daily.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer k and a string s, find the length of the longest substring
 * that contains at most k distinct characters.
 * 
 * @author pribiswas
 *
 */
public class KUniqueChars {

	private static int lenOfLongestSubStrWithKUniqueChars(String string, int k) {
		int maxSofar = 0, startIndex = 0;
		final Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
			if (map.size() <= k) {
				maxSofar = Math.max(maxSofar, i - startIndex + 1);
			} else {
				// modify left index keeping in mind the repeated characters
				while (map.size() > k) {
					char leftChar = string.charAt(startIndex);
					int count = map.get(leftChar);
					if (count == 1) {
						map.remove(leftChar);
					} else {
						map.put(leftChar, count - 1);
					}
					startIndex++;
				}
			}
		}
		return maxSofar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(lenOfLongestSubStrWithKUniqueChars("abcba", 2));
	}

}
