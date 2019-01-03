/**
 *
 */
package com.pritam.daily.coding;

/**
 * A unival tree (which stands for "universal value") is a tree where all nodes
 * under it have the same value. Given the root to a binary tree, count the
 * number of unival subtrees.
 *
 * @author pribiswas
 *
 */
public class UnivalTree {

	private static class TreeNode<T> {
		private final T data;
		private TreeNode<T> left, right;

		/**
		 * @param data
		 */
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

	}

	private static class Counter {
		private int count;

		public void increment() {
			count++;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

	}

	private static <T> int countUnivalSubTrees(TreeNode<T> root) {
		if (root == null)
			return 0;
		Counter counter = new Counter();
		countUnivalSubTrees(root, counter);
		return counter.getCount();
	}

	private static <T> void countUnivalSubTrees(TreeNode<T> root, Counter counter) {
		if (root == null)
			return;
		if ((root.getLeft() == null && root.getRight() == null) || (root.getLeft() != null && root.getRight() != null
				&& root.getLeft().getData() == root.getRight().getData())) {
			counter.increment();
		}
		countUnivalSubTrees(root.getLeft(), counter);
		countUnivalSubTrees(root.getRight(), counter);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>(0);
		root.setLeft(new TreeNode<Integer>(1));

		TreeNode<Integer> rightSubtree = new TreeNode<>(0);
		TreeNode<Integer> left = new TreeNode<>(1);
		left.setLeft(new TreeNode<Integer>(1));
		left.setRight(new TreeNode<Integer>(1));

		rightSubtree.setLeft(left);
		rightSubtree.setRight(new TreeNode<Integer>(0));

		root.setRight(rightSubtree);

		System.out.println(countUnivalSubTrees(root));

	}

}
