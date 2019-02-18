/**
 * 
 */
package com.pritam.daily.coding;

import java.util.Arrays;

/**
 * Given an array of numbers, find the length of the longest increasing
 * subsequence in the array. The subsequence does not necessarily have to be
 * contiguous.
 * 
 * For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11,
 * 7, 15], the longest increasing subsequence has length 6: it is 0, 2, 6, 9,
 * 11, 15.
 * 
 * @author pribiswas
 *
 */
public class LongestIncreasingSubsequence {

	private static int lenOfLIS(int[] arr) {
		int[] memo = new int[arr.length];
		Arrays.fill(memo, 1);

		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && memo[j] + 1 > memo[i]) {
					memo[i] = memo[j] + 1;
				}
			}
		}
		return findMax(memo);
	}

	/**
	 * @param memo
	 * @return
	 */
	private static int findMax(int[] memo) {
		int maxLen = 1;
		for (int i = 0; i < memo.length; i++) {
			if (memo[i] > maxLen) {
				maxLen = memo[i];
			}
		}
		return maxLen;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = new int[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		System.out.println(lenOfLIS(arr));
	}

}
