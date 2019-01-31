/**
 * 
 */
package com.pritam.daily.coding;

/**
 * An sorted array of integers was rotated an unknown number of times.
 * 
 * Given such an array, find the index of the element in the array in faster
 * than linear time. If the element doesn't exist in the array, return -1.
 * 
 * @author pribiswas
 *
 */
public class FindInSortedRotatedArray {

	private static int search(int[] array, int start, int end, int element) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (array[mid] == element) {
			return mid;
		}
		// Need to find the normally sorted half and decide which half to search based
		// on that
		if (array[start] < array[mid]) { // left half is normally sorted
			// if element falls in left range search there
			if (array[start] <= element && array[mid] > element) {
				return search(array, start, mid - 1, element);
			} else {
				return search(array, mid + 1, end, element);
			}
		} else if (array[start] > array[mid]) { // right half is normally sorted
			// if element falls in right range search there
			if (array[mid] < element && array[end] >= element) {
				return search(array, mid + 1, end, element);
			} else {
				return search(array, start, mid - 1, element);
			}
		} else {
			// looks like array contains duplicate elements
			if (array[mid] != array[end]) {
				return search(array, mid + 1, end, element);
			} else {
				// no other way but searching both halves
				int index = search(array, start, mid - 1, element);
				return index == -1 ? search(array, mid + 1, end, element) : index;
			}
		}
	}

	private static int search(int[] array, int element) {
		return search(array, 0, array.length - 1, element);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// int[] input = new int[] { 13, 18, 25, 2, 8, 10 };
		int[] input = new int[] { 2, 2, 2, 8, 6, 2 };
		System.out.println(search(input, 3));
	}

}
