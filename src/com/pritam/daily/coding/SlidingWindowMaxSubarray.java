/**
 *
 */
package com.pritam.daily.coding;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers and a number k, where 1 <= k <= length of the
 * array, compute the maximum values of each subarray of length k.
 *
 * For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10,
 * 7, 8, 8]
 *
 * @author pribiswas
 *
 */
public class SlidingWindowMaxSubarray {

	private static void printMax(int[] arr, int k) {
		int n = arr.length;
		// maintains the index of the array elements falls in current window where max
		// is at the front
		Deque<Integer> deque = new LinkedList<>();

		// Pre-compute the dqueue for first window of k
		int i = 0;
		for (i = 0; i < k; i++) {
			while (!deque.isEmpty() && arr[i] >= arr[deque.peekFirst()]) {
				deque.removeFirst();
			}
			deque.addLast(i);
		}

		for (; i < n; i++) {
			System.out.print(arr[deque.peek()] + " ");

			if (!deque.isEmpty() && deque.peek() <= i - k) {
				deque.removeFirst();
			}

			while (!deque.isEmpty() && arr[i] >= arr[deque.peekFirst()]) {
				deque.removeFirst();
			}
			deque.addLast(i);
		}
		System.out.println(arr[deque.poll()]);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printMax(new int[] { 10, 5, 2, 7, 8, 7 }, 3);

	}

}
