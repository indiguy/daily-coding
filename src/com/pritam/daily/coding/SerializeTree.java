/**
 * 
 */
package com.pritam.daily.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 * @author pribiswas
 *
 */
public class SerializeTree<T> {

	private static class TreeNode<T> {
		private final T data;
		private TreeNode<T> left;
		private TreeNode<T> right;

		public TreeNode(T data) {
			this.data = data;
		}

		/**
		 * @return the left
		 */
		public TreeNode<T> getLeft() {
			return left;
		}

		/**
		 * @param left
		 *            the left to set
		 */
		public void setLeft(TreeNode<T> left) {
			this.left = left;
		}

		/**
		 * @return the right
		 */
		public TreeNode<T> getRight() {
			return right;
		}

		/**
		 * @param right
		 *            the right to set
		 */
		public void setRight(TreeNode<T> right) {
			this.right = right;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		@Override
		public String toString() {
			ArrayList<String> builder = new ArrayList<>();
			visit(this, builder);
			return builder.stream().map(str -> str).collect(Collectors.joining("->"));
		}

		private void visit(TreeNode<T> node, ArrayList<String> builder) {
			if (node == null) {
				builder.add("null");
				return;
			} else {
				builder.add(node.getData().toString());
				visit(node.getLeft(), builder);
				visit(node.getRight(), builder);
			}
		}

	}

	private final TreeNode<T> root;

	public SerializeTree(TreeNode<T> root) {
		this.root = root;
	}

	public String serialize() {
		return root.toString();
	}

	public TreeNode<String> desrialize(String data) {
		if (data == null)
			return null;
		List<String> nodes = Arrays.asList(data.split("->"));
		if (nodes.size() == 1)
			return new TreeNode<String>(nodes.get(0));

		return build(nodes.listIterator());
	}

	private TreeNode<String> build(ListIterator<String> iterator) {
		String node = iterator.next();
		if (node.equals("null")) {
			return null;
		}
		TreeNode<String> root = new TreeNode<>(node);
		root.setLeft(build(iterator));
		root.setRight(build(iterator));
		return root;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode<Integer> leftLeaf = new TreeNode<>(1);
		TreeNode<Integer> left = new TreeNode<>(2);
		left.setLeft(leftLeaf);

		TreeNode<Integer> rightLeaf = new TreeNode<>(5);
		TreeNode<Integer> right = new TreeNode<>(6);
		right.setLeft(rightLeaf);

		TreeNode<Integer> root = new TreeNode<Integer>(3);
		root.setLeft(left);
		root.setRight(right);

		SerializeTree<Integer> tree = new SerializeTree<>(root);
		String serializedStr = tree.serialize();

		System.out.println("Serialize: " + serializedStr);

		TreeNode<String> desrialized = tree.desrialize(serializedStr);

		String deserializedStr = desrialized.toString();
		System.out.println("Deserialize: " + deserializedStr);

		assert serializedStr.equals(deserializedStr);
	}

}
