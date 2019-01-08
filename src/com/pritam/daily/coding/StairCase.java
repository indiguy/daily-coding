/**
 * 
 */
package com.pritam.daily.coding;

/**
 * There exists a staircase with N steps, and you can climb up either 1 or 2
 * steps at a time. Given N, write a function that returns the number of unique
 * ways you can climb the staircase. The order of the steps matters.
 * 
 * @author pribiswas
 *
 */
public class StairCase {

	private static int climbWays(int n) {
		if (n == 0 || n == 1)
			return 1;
		return climbWays(n - 1) + climbWays(n - 2);
	}

	private static int climbWaysMemo(int n) {
		int[] count = new int[n + 1];
		count[0] = 1;
		count[1] = 1;
		for (int i = 2; i <= n; i++) {
			count[i] = count[i - 1] + count[i - 2];
		}
		return count[n];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(climbWays(4));
		System.out.println(climbWaysMemo(4));
	}

}
