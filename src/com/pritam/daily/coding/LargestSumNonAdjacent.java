/**
 *
 */
package com.pritam.daily.coding;

/**
 * Given a list of integers(may contain zero or negative) find the largest sum
 * of non adjacent numbers in linear time and constant space.
 *
 * @author pribiswas
 *
 */
public class LargestSumNonAdjacent {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(largestSum(2, 4, 6, 2, 5));
		System.out.println(largestSumWithConstantSpace(5, 1, 1, 5));
	}

	private static int largestSum(int... list) {
		int n = list.length;
		if (list == null || n == 0)
			return 0;
		if (n == 1)
			return list[0];
		if (n == 2)
			return Math.max(list[0], list[1]);

		int[] count = new int[n];
		count[0] = list[0];
		count[1] = Math.max(list[0], list[1]);
		for (int i = 2; i < n; i++) {
			count[i] = Math.max(list[i] + count[i - 2], count[i - 1]);
		}
		return count[n - 1];
	}

	private static int largestSumWithConstantSpace(int... list) {
		int n = list.length;
		if (list == null || n == 0)
			return 0;
		if (n == 1)
			return list[0];
		if (n == 2)
			return Math.max(list[0], list[1]);

		int maxSofarAtPrevToPrevIndex = list[0];
		int maxSofarAtPrevIndex = Math.max(list[0], list[1]);
		for (int i = 2; i < n; i++) {
			int max = Math.max(list[i] + maxSofarAtPrevToPrevIndex, maxSofarAtPrevIndex);
			maxSofarAtPrevToPrevIndex = maxSofarAtPrevIndex;
			maxSofarAtPrevIndex = max;
		}
		return maxSofarAtPrevIndex;
	}

}
