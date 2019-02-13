/**
 * 
 */
package com.pritam.daily.coding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * You are given an M by N matrix consisting of booleans that represents a
 * board. Each True boolean represents a wall. Each False boolean represents a
 * tile you can walk on.
 * 
 * Given this matrix, a start coordinate, and an end coordinate, return the
 * minimum number of steps required to reach the end coordinate from the start.
 * If there is no possible path, then return null. You can move up, left, down,
 * and right. You cannot move through walls. You cannot wrap around the edges of
 * the board.
 * 
 * For example, given the following board:
 * 
 * [[f, f, f, f], [t, t, f, t], [f, f, f, f], [f, f, f, f]] and start = (3, 0)
 * (bottom left) and end = (0, 0) (top left), the minimum number of steps
 * required to reach the end is 7, since we would need to go through (1, 2)
 * because there is a wall everywhere else on the second row.
 * 
 * @author pribiswas
 *
 */
public class MazeRunner {

	private static class Point {
		private final int row;
		private final int col;
		private int distanceFromSource;

		/**
		 * @param row
		 * @param col
		 */
		private Point(int row, int col) {
			this.row = row;
			this.col = col;
			this.distanceFromSource = 0;
		}

		/**
		 * @return the row
		 */
		public int getRow() {
			return row;
		}

		/**
		 * @return the col
		 */
		public int getCol() {
			return col;
		}

		/**
		 * @return the distanceFromSource
		 */
		public int getDistanceFromSource() {
			return distanceFromSource;
		}

		/**
		 * @param distanceFromSource
		 *            the distanceFromSource to set
		 */
		public void setDistanceFromSource(int distanceFromSource) {
			this.distanceFromSource = distanceFromSource;
		}

		public static Point create(int row, int col) {
			return new Point(row, col);
		}

		/**
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			return row == other.row && col == other.col;
		}

	}

	private static int minSteps(boolean[][] adjacencyMatrix, Point start, Point end) {
		int rowSize = adjacencyMatrix.length;
		int colSize = adjacencyMatrix[0].length;
		Set<Point> visited = new HashSet<>();
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			Point front = queue.poll();
			if (front.equals(end)) {
				return front.getDistanceFromSource();
			}
			for (Point neighbour : getAdjacentPoints(front)) {
				if (inRange(rowSize, colSize, neighbour) && !adjacencyMatrix[neighbour.getRow()][neighbour.getCol()]
						&& !visited.contains(neighbour)) {
					neighbour.setDistanceFromSource(front.getDistanceFromSource() + 1);
					visited.add(neighbour);
					queue.add(neighbour);
				}
			}
		}
		return -1;
	}

	private static boolean inRange(int rowSize, int colSize, Point point) {
		int row = point.getRow();
		int col = point.getCol();
		return row >= 0 && row < rowSize && col >= 0 && col < colSize;
	}

	private static List<Point> getAdjacentPoints(Point point) {
		int row = point.getRow();
		int col = point.getCol();
		return Arrays.asList(Point.create(row, col - 1), Point.create(row - 1, col), Point.create(row, col + 1),
				Point.create(row + 1, col));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final boolean[][] matrix = new boolean[][] { { false, false, false, false }, { true, true, false, true },
				{ false, false, false, false }, { false, false, false, false } };
		System.out.println(minSteps(matrix, Point.create(3, 0), Point.create(0, 0)));
	}

}
