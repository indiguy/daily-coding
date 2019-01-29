/**
 * 
 */
package com.pritam.daily.coding;

import java.util.Arrays;

/**
 * Given an array of time intervals (start, end) for classroom lectures
 * (possibly overlapping), find the minimum number of rooms required.
 * 
 * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 * 
 * @author pribiswas
 *
 */
public class OverlappingIntervals {

	private static class Interval {
		private final int start;
		private final int end;

		/**
		 * @param start
		 * @param end
		 */
		Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}

		/**
		 * @return the start
		 */
		public int getStart() {
			return start;
		}

		/**
		 * @return the end
		 */
		public int getEnd() {
			return end;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Interval [start=" + start + ", end=" + end + "]";
		}

	}

	private static int minimumRooms(Interval... intervals) {
		Arrays.sort(intervals, (first, second) -> first.getStart() - second.getStart());
		Interval prev = intervals[0];
		int overlapping = 1;
		for (int i = 1; i < intervals.length; i++) {
			// calculate no of overlaps
			Interval current = intervals[i];
			if (current.getStart() < prev.getEnd()) {
				overlapping++;
				if (current.getEnd() < prev.getEnd()) {
					prev = current;
				}
			} else {
				prev = current;
			}
		}
		return overlapping;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.println(minimumRooms(new Interval(9, 10), new Interval(3, 4), new Interval(1, 5), new Interval(4, 8)));

	}

}
