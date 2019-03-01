/**
 * 
 */
package com.pritam.daily.coding;

import java.util.List;

/**
 * Write an algorithm to justify text. Given a sequence of words and an integer
 * line length k, return a list of strings which represents each line, fully
 * justified.
 * 
 * More specifically, you should have as many words as possible in each line.
 * There should be at least one space between each word. Pad extra spaces when
 * necessary so that each line has exactly length k. Spaces should be
 * distributed as equally as possible, with the extra spaces, if any,
 * distributed starting from the left.
 * 
 * If you can only fit one word on a line, then you should pad the right-hand
 * side with spaces.
 * 
 * Each word is guaranteed not to be longer than k.
 * 
 * For example, given the list of words ["the", "quick", "brown", "fox",
 * "jumps", "over", "the", "lazy", "dog"] and k = 16, you should return the
 * following:
 * 
 * ["the quick brown", # 1 extra space on the left "fox jumps over", # 2 extra
 * spaces distributed evenly "the lazy dog"] # 4 extra spaces distributed evenly
 * 
 * @author pribiswas
 *
 */
public class TextJustification {

	private static List<String> fullJustify(List<String> words, int maxWidth) {
		return null;
	}

	/**
	 * Calculate extra spaces while putting words in single line. extraSpaces[i][j]
	 * indicates no of extra spaces if words from ith to jth index are put in single
	 * line.
	 * 
	 * @param words
	 * @param maxWidth
	 * @return
	 */
	private static int[][] calculateExtraSpaces(List<String> words, int maxWidth) {
		int size = words.size();
		int[][] extraSpaces = new int[size][size];
		for (int i = 0; i < size; i++) {
			extraSpaces[i][i] = maxWidth - words.get(i).length();
			for (int j = i + 1; j < size; j++) {
				// There is a space in between words to subtract 1 at the end
				extraSpaces[i][j] = extraSpaces[i][j - 1] - words.get(j).length() - 1;
			}
		}
		return extraSpaces;
	}

	/**
	 * Calculate cost of each line from given space array. lineCosts[i][j] indicates
	 * the cost if words from ith to jth index are put in single line. We use square
	 * to calculate the cost.
	 * 
	 * @param extraSpaces
	 * @return
	 */
	private static int[][] calculateLineCosts(int[][] extraSpaces) {
		int size = extraSpaces.length;
		int[][] lineCosts = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if (extraSpaces[i][j] < 0) {
					lineCosts[i][j] = Integer.MAX_VALUE;
				} else if (j == size - 1 && extraSpaces[i][j] >= 0) {
					// The trailing spaces of last line shouldn't be considered
					lineCosts[i][j] = 0;
				} else {
					lineCosts[i][j] = extraSpaces[i][j] * extraSpaces[i][j];
				}
			}
		}
		return lineCosts;
	}

	private static List<String> findMinimumCostArrangement(int[][] lineCosts, List<String> words) {

		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
