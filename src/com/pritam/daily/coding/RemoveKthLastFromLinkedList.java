/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Given a singly linked list and an integer k, remove the kth last element from
 * the list. k is guaranteed to be smaller than the length of the list.
 * 
 * The list is very long, so making more than one pass is prohibitively
 * expensive.
 * 
 * Do this in constant space and in one pass.
 * 
 * @author pribiswas
 *
 */
public class RemoveKthLastFromLinkedList {

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

	}

	private static class LinkedList<T> {
		private Node<T> head;
		private Node<T> tail;

		public void add(T data) {
			Node<T> node = new Node<>(data);
			if (head == null) {
				head = node;
				tail = head;
			} else {
				tail.setNext(node);
				tail = node;
			}
		}

		public void removeKthLast(int k) {
			Node<T> slow = head;
			Node<T> fast = head;
			while (fast != null) {
				slow = slow.getNext();
				fast = advanceBy(fast, k);
			}
			Node<T> kthLast = slow.getNext();
			slow.setNext(kthLast.getNext());
		}

		private Node<T> advanceBy(Node<T> current, int n) {
			for (int i = 0; i < n; i++) {
				if (current != null) {
					current = current.next;
				}
			}
			return current;
		}

		@Override
		public String toString() {
			if (head == null)
				return "";
			Node<T> current = head;
			final StringBuilder builder = new StringBuilder();
			while (current != null) {
				builder.append(current.getData());
				current = current.getNext();
				if (current != null) {
					builder.append("->");
				}
			}
			return builder.toString();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);

		System.out.println(list);
		list.removeKthLast(2);
		System.out.println(list);

	}

}
