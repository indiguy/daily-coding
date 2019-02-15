/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Given the head of a singly linked list, reverse it in-place.
 * 
 * @author pribiswas
 *
 */
public class ReverseLinkedList {

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

		public void reverse() {
			Node<T> prev = null;
			Node<T> current = head;
			while (current != null) {
				Node<T> next = current.getNext();
				current.setNext(prev);
				prev = current;
				current = next;
			}
			tail = head;
			head = prev;
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
		list.add(5);

		System.out.println(list);
		list.reverse();
		System.out.println(list);
	}

}
