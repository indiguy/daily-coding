/**
 * 
 */
package com.pritam.daily.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pribiswas
 *
 */
public class XoredLinkedList<K> {

	private static class Node<K> {
		private final K data;
		private int xorOfPrevAndNext;

		public Node(K data) {
			this.data = data;
		}

		/**
		 * @return the xorOfPrevAndNext
		 */
		public int getXorOfPrevAndNext() {
			return xorOfPrevAndNext;
		}

		/**
		 * @param xorOfPrevAndNext
		 *            the xorOfPrevAndNext to set
		 */
		public void setXorOfPrevAndNext(int xorOfPrevAndNext) {
			this.xorOfPrevAndNext = xorOfPrevAndNext;
		}

		/**
		 * @return the data
		 */
		public K getData() {
			return data;
		}

	}

	private Node<K> head;
	private Node<K> tail;
	private Map<Integer, Node<K>> mappings = new HashMap<>();

	public void add(K data) {
		Node<K> node = new Node<>(data);
		if (head == null) {
			node.setXorOfPrevAndNext(0);
			head = node;
		} else {
			node.setXorOfPrevAndNext(getPointer(tail));
			tail.setXorOfPrevAndNext(tail.getXorOfPrevAndNext() ^ getPointer(node));
		}
		tail = node;
		mappings.put(getPointer(node), node);
	}

	private int getPointer(Node<K> node) {
		return node == null ? 0 : node.hashCode();
	}

	private Node<K> dereferencePointer(int address) {
		return mappings.get(address);
	}
}
