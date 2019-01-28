/**
 *
 */
package com.pritam.daily.coding;

/**
 * A builder is looking to build a row of N houses that can be of K different
 * colors. He has a goal of minimizing cost while ensuring that no two
 * neighboring houses are of the same color.
 *
 * Given an N by K matrix where the nth row and kth column represents the cost
 * to build the nth house with kth color, return the minimum cost which achieves
 * this goal
 *
 * @author pribiswas
 *
 */
public class CostOfBuilding {

	private static long minimumCost(long[][] cost) {
		int n = cost.length;
		int k = cost[0].length;

		long costSofar = 0;
		int lastChosenColorIndex = 0;
		long minForCurrentBuilding = cost[0][0];
		// Compute for first building
		for (int c = 1; c < k; c++) {
			if (cost[0][c] < minForCurrentBuilding) {
				minForCurrentBuilding = cost[0][c];
				lastChosenColorIndex = c;
			}
		}
		costSofar += minForCurrentBuilding;
		// Compute for rest
		for (int b = 1; b < n; b++) {
			int minColorIndex = lastChosenColorIndex == 0 ? 1 : 0;
			minForCurrentBuilding = cost[b][minColorIndex];
			for (int c = minColorIndex + 1; c < k; c++) {
				if (c != lastChosenColorIndex && cost[b][c] < minForCurrentBuilding) {
					minForCurrentBuilding = cost[b][c];
					lastChosenColorIndex = c;
				}
			}
			costSofar += minForCurrentBuilding;
		}

		return costSofar;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
