/**
 *
 */
package com.pritam.daily.coding;

/**
 * Given a tree, return the size of the largest tree/subtree that is a BST.
 *
 * @author pribiswas
 *
 */
public class LongestBST {

	private static class TreeNode<T extends Comparable<T>> {
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

	private static class Result {
		private int size;

		/**
		 * @param size
		 *            the size to set
		 */
		public void setSize(int size) {
			this.size = size;
		}

		/**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}

	}

	private static <T extends Comparable<T>> int longestBST(TreeNode<T> root) {
		Result result = new Result();
		checkBST(root, null, null, result);
		return result.getSize();
	}

	/**
	 * Check the whether tree rooted at given node is BST and also track the max
	 * size of BST
	 * 
	 * @param root
	 * @param min
	 * @param max
	 * @param result
	 * @return
	 */
	private static <T extends Comparable<T>> boolean checkBST(TreeNode<T> root, T min, T max, Result result) {
		if (root == null) {
			return true;
		}

		if ((min != null && root.getData().compareTo(min) <= 0) || (max != null && root.getData().compareTo(max) > 0)) {
			return false;
		}

		Result left = new Result();
		Result right = new Result();
		boolean isLeftBST = checkBST(root, min, root.getData(), left);
		boolean isRightBST = checkBST(root, min, root.getData(), right);

		boolean isBST = isLeftBST && isRightBST;
		if (isBST) {
			result.setSize(left.getSize() + right.getSize() + 1);
		} else {
			result.setSize(Math.max(left.getSize(), right.getSize()));
		}

		return isBST;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
