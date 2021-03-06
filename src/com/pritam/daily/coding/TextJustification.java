/**
 *
 */
package com.pritam.daily.coding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

	private static String fullJustify(List<String> words, int maxWidth) {
		int[][] extraSpaces = calculateExtraSpaces(words, maxWidth);
		int[][] lineCosts = calculateLineCosts(extraSpaces);
		int[] arrangements = findMinimumCostArrangement(lineCosts);
		return formSolution(words, arrangements, maxWidth);
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
	 * the cost of packing words from ith to jth index in a single line. We use
	 * square to calculate the cost.
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

	/**
	 * Find the minimum cost arrangement from lineCosts array. minCosts[i] signifies
	 * the optimal cost to arrange word from i to end of the list.
	 *
	 * @param lineCosts
	 * @param words
	 * @return
	 */
	private static int[] findMinimumCostArrangement(int[][] lineCosts) {
		int size = lineCosts.length;
		int[] arrangements = new int[size];
		int[] minCosts = new int[size];
		for (int i = size - 1; i >= 0; i--) {
			minCosts[i] = lineCosts[i][size - 1];
			arrangements[i] = size;
			for (int j = size - 1; j > i; j--) {
				if (lineCosts[i][j - 1] == Integer.MAX_VALUE) {
					continue;
				}
				if (minCosts[j] + lineCosts[i][j - 1] < minCosts[i]) {
					minCosts[i] = minCosts[j] + lineCosts[i][j - 1];
					arrangements[i] = j;
				}
			}
		}
		return arrangements;
	}

	/**
	 * Form solution from given arrangements
	 *
	 * @param words
	 * @param arrangements
	 * @return
	 */
	private static String formSolution(List<String> words, int[] arrangements, int maxWidth) {
		final StringBuilder slnBuilder = new StringBuilder();
		int start = 0;
		do {
			int breakIndex = arrangements[start];
			String line = words.subList(start, breakIndex).stream().collect(Collectors.joining(" "));
			slnBuilder.append(line);
			int trailingSpaces = maxWidth - line.length();
			for (int i = 0; i < trailingSpaces; i++) {
				slnBuilder.append(" ");
			}
			slnBuilder.append("\n");
			start = breakIndex;
		} while (start < words.size());
		return slnBuilder.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");
		System.out.println(fullJustify(words, 16));
	}

}
