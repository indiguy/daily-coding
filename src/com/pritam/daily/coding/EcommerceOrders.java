/**
 * 
 */
package com.pritam.daily.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.RandomAccess;

/**
 * You run an e-commerce website and want to record the last N order ids in a
 * log. Implement a data structure to accomplish this, with the following API:
 * 
 * record(order_id): adds the order_id to the log and get_last(i): gets the ith
 * last element from the log. i is guaranteed to be smaller than or equal to N.
 * 
 * @author pribiswas
 *
 */
public class EcommerceOrders {

	private static class EvictionQueue<E> implements RandomAccess {

		private final int capacity;
		private final ArrayList<E> buffer;

		private int size;
		private int tail;

		/**
		 * Instantiate an eviction queue with given capacity
		 * 
		 * @param capacity
		 */
		public EvictionQueue(int capacity) {
			this.capacity = capacity;
			this.buffer = new ArrayList<>(Collections.nCopies(capacity, null));
		}

		/**
		 * Add the given element at the tail of the queue. If the capacity exceeds the
		 * oldest element is removed to accommodate the space.
		 * 
		 * @param element
		 */
		public void add(E element) {
			if (size == capacity) {
				// adjust tail to point to the eldest element
				tail = tail % capacity;
			}
			buffer.set(tail++, element);
			if (size < capacity) {
				size++;
			}
		}

		/**
		 * Get the ith element of the queue in O(1)
		 * 
		 * @param i
		 * @return
		 */
		public E get(int i) {
			if (i < 0 || i > size - 1) {
				throw new ArrayIndexOutOfBoundsException();
			}
			return buffer.get((tail + i) % size);
		}

		/**
		 * Return the current size of the queue
		 * 
		 * @return
		 */
		public int size() {
			return size;
		}

	}

	private final int n;
	private final EvictionQueue<String> queue;

	/**
	 * @param n
	 */
	public EcommerceOrders(int n) {
		this.n = n;
		this.queue = new EvictionQueue<>(n);
	}

	/**
	 * Record the order id
	 * 
	 * @param orderId
	 */
	public void record(String orderId) {
		queue.add(orderId);
	}

	/**
	 * Get the ith last order
	 * 
	 * @param i
	 * @return
	 */
	public String getLast(int i) {
		return queue.get(queue.size() - i);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EcommerceOrders prob = new EcommerceOrders(4);
		prob.record("5");
		prob.record("1");
		prob.record("2");
		prob.record("7");

		prob.record("6");

		System.out.println(prob.getLast(1));

	}

}
