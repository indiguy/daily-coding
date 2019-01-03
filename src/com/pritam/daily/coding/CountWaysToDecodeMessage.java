/**
 *
 */
package com.pritam.daily.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * Given mappings as 1 = a, 2 = b, ... 26 = z and a sequence of digits like
 * "2526", count the no of ways the message can be decoded. In the above example
 * the answer is 4 - bebf, bez, ybf, yz.
 *
 * @author pribiswas
 *
 */
public class CountWaysToDecodeMessage {

	/**
	 * Count the no of ways the message can be decoded
	 *
	 * @param message
	 * @param mappings
	 * @return
	 */
	private static int count(String message, Map<Integer, Character> mappings) {
		if (message == null || message.trim().isEmpty()) {
			return 0;
		}
		int n = message.length();
		int[] count = new int[n + 1];
		count[0] = 1;
		count[1] = 1;
		for (int i = 2; i <= n; i++) {
			count[i] = 0;
			Integer prevDigit = Integer.valueOf(message.substring(i - 1, i));
			if (mappings.containsKey(prevDigit)) {
				count[i] += count[i - 1];
			}
			Integer prev2Digits = Integer.valueOf(message.substring(i - 2, i));
			if (mappings.containsKey(prev2Digits)) {
				count[i] += count[i - 2];
			}
		}
		return count[n];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<Integer, Character> mappings = createMappings();
		System.out.println(count("2526", mappings));
	}

	private static Map<Integer, Character> createMappings() {
		Map<Integer, Character> mappings = new HashMap<>();
		for (int i = 'a', key = 1; i <= 'z'; i++) {
			mappings.put(key, (char) i);
			key++;
		}
		return mappings;
	}

}
