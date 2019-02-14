/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Implement locking in a binary tree. A binary tree node can be locked or
 * unlocked only if all of its descendants or ancestors are not locked.
 * 
 * Design a binary tree node class with the following methods:
 * 
 * is_locked, which returns whether the node is locked lock, which attempts to
 * lock the node. If it cannot be locked, then it should return false.
 * Otherwise, it should lock it and return true. unlock, which unlocks the node.
 * If it cannot be unlocked, then it should return false. Otherwise, it should
 * unlock it and return true.
 * 
 * @author pribiswas
 *
 */
public class LockingBinaryTree {

	private static class TreeNode<T> {
		private final T data;
		private TreeNode<T> left;
		private TreeNode<T> right;
		private TreeNode<T> parent;
		private boolean locked;
		private int noOfDecedentLocked;

		/**
		 * @param data
		 */
		TreeNode(T data) {
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
			if (left != null) {
				left.setParent(this);
			}
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
			if (right != null) {
				right.setParent(this);
			}
		}

		/**
		 * @return the locked
		 */
		public boolean isLocked() {
			return locked;
		}

		/**
		 * @return the parent
		 */
		public TreeNode<T> getParent() {
			return parent;
		}

		/**
		 * @param parent
		 *            the parent to set
		 */
		public void setParent(TreeNode<T> parent) {
			this.parent = parent;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * Attempts to lock the node
		 * 
		 * @return true on success, false otherwise
		 */
		public boolean lock() {
			if (isAncestorsLocked() || noOfDecedentLocked > 0) {
				return false;
			}
			this.locked = true;
			notifyAncenstors(true);
			return true;
		}

		private void notifyAncenstors(boolean locked) {
			if (parent == null) {
				return;
			}
			TreeNode<T> temp = parent;
			while (temp != null) {
				if (locked) {
					temp.noOfDecedentLocked++;
				} else {
					temp.noOfDecedentLocked--;
				}
				temp = temp.getParent();
			}
		}

		private boolean isAncestorsLocked() {
			if (parent == null) {
				return false;
			}
			TreeNode<T> temp = parent;
			while (temp != null) {
				if (temp.isLocked()) {
					return true;
				}
				temp = temp.getParent();
			}
			return false;
		}

		/**
		 * Attempts to unlock the node
		 * 
		 * @return true on success, false otherwise
		 */
		public boolean unlock() {
			if (isAncestorsLocked() || noOfDecedentLocked > 0) {
				return false;
			}
			this.locked = false;
			notifyAncenstors(false);
			return true;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
