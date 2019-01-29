/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Given two singly linked lists that intersect at some point, find the
 * intersecting node. The lists are non-cyclical.
 * 
 * For example, given A = 3 -> 7 -> 8 -> 10 and B = 99 -> 1 -> 8 -> 10, return
 * the node with value 8.
 * 
 * In this example, assume nodes with the same value are the exact same node
 * objects.
 * 
 * Do this in O(M + N) time (where M and N are the lengths of the lists) and
 * constant space.
 * 
 * @author pribiswas
 *
 */
public class IntersectionOfTwoSinglyLinkedList {

	private static class Node<T> {
		private final T data;
		private Node<T> next;

		/**
		 * @param data
		 */
		Node(T data) {
			this.data = data;
		}

		/**
		 * @return the next
		 */
		public Node<T> getNext() {
			return next;
		}

		/**
		 * @param next
		 *            the next to set
		 */
		public void setNext(Node<T> next) {
			this.next = next;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder(data.toString());
			if (next != null) {
				builder.append("->").append(next.toString());
			}
			return builder.toString();
		}

	}

	private static class ListMeta<T> {
		private final Node<T> tail;
		private final int size;

		/**
		 * @param tail
		 * @param size
		 */
		ListMeta(Node<T> tail, int size) {
			this.tail = tail;
			this.size = size;
		}

		/**
		 * @return the tail
		 */
		public Node<T> getTail() {
			return tail;
		}

		/**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}

	}

	private static <T> Node<T> intersectionPoint(Node<T> first, Node<T> second) {
		if (first == null || second == null) {
			return null;
		}
		ListMeta<T> firstMeta = findLastNodeAndSize(first);
		ListMeta<T> secondMeta = findLastNodeAndSize(second);

		if (firstMeta.getTail() != secondMeta.getTail()) {
			return null;
		}

		if (firstMeta.getSize() == secondMeta.getSize()) {
			return intersectionPointOfSameSize(first, second);
		}

		Node<T> large = firstMeta.getSize() > secondMeta.getSize() ? first : second;
		Node<T> small = firstMeta.getSize() > secondMeta.getSize() ? second : first;

		int sizeDiff = Math.abs(firstMeta.getSize() - secondMeta.getSize());
		large = advanceBy(large, sizeDiff);
		return intersectionPointOfSameSize(large, small);
	}

	/**
	 * @param large
	 * @param sizeDiff
	 * @return
	 */
	private static <T> Node<T> advanceBy(Node<T> large, int sizeDiff) {
		for (int i = 0; i < sizeDiff; i++) {
			large = large.getNext();
		}
		return large;
	}

	private static <T> Node<T> intersectionPointOfSameSize(Node<T> first, Node<T> second) {
		Node<T> currentFirst = first;
		Node<T> currentSecond = second;

		while (currentFirst != currentSecond) {
			currentFirst = currentFirst.getNext();
			currentSecond = currentSecond.getNext();
		}
		return currentFirst;
	}

	private static <T> ListMeta<T> findLastNodeAndSize(Node<T> head) {
		Node<T> current = head;
		int size = 1;
		while (current.getNext() != null) {
			current = current.getNext();
			size++;
		}
		return new ListMeta<T>(current, size);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
