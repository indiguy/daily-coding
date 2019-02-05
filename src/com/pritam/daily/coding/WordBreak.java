/**
 * 
 */
package com.pritam.daily.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a dictionary of words and a string made up of those words (no spaces),
 * return the original sentence in a list. If there is more than one possible
 * reconstruction, return any of them. If there is no possible reconstruction,
 * then return null.
 * 
 * For example, given the set of words 'quick', 'brown', 'the', 'fox', and the
 * string "thequickbrownfox", you should return ['the', 'quick', 'brown',
 * 'fox'].
 * 
 * Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the
 * string "bedbathandbeyond", return either ['bed', 'bath', 'and', 'beyond] or
 * ['bedbath', 'and', 'beyond']
 * 
 * @author pribiswas
 *
 */
public class WordBreak {

	private static List<String> wordBreak(String sentence, Set<String> dict) {
		return wordBreakHelper(sentence, dict, new HashMap<>());
	}

	private static List<String> wordBreakHelper(String sentence, Set<String> dict, Map<String, List<String>> memo) {
		if (sentence.isEmpty()) {
			return Collections.emptyList();
		}
		if (dict.contains(sentence)) {
			return Arrays.asList(sentence);
		}

		if (memo.containsKey(sentence)) {
			return memo.get(sentence);
		}

		for (int i = 0; i < sentence.length(); i++) {
			for (int j = i + 1; j < sentence.length(); j++) {
				String prefix = sentence.substring(i, j);
				if (dict.contains(prefix)) {
					List<String> suffixList = wordBreakHelper(sentence.substring(j), dict, memo);
					if (suffixList != null && !suffixList.isEmpty()) {
						List<String> result = new ArrayList<>();
						result.add(prefix);
						result.addAll(suffixList);
						memo.put(sentence, result);
						return result;
					}
				}
			}
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> dict = new HashSet<>();
		dict.addAll(Arrays.asList("quick", "brown", "the", "fox"));

		System.out.println(wordBreak("thequickbrownfox", dict));
	}

}
