/**
 * 
 */
package com.pritam.daily.coding;

import java.util.ArrayList;

/**
 * The string
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * represents
 * 
 * @author pribiswas
 *
 */
public class LongestPathInFileSystem {

	private static final String TAB = "\t";

	private static abstract class Entry {
		private final String name;
		private final Directory parent;

		Entry(String name, Directory parent) {
			this.name = name;
			this.parent = parent;
		}

		public abstract boolean isDirectory();

		/**
		 * @return the parent
		 */
		public Directory getParent() {
			return parent;
		}

		public String getPath() {
			StringBuilder pathBuilder = new StringBuilder();
			if (parent != null) {
				pathBuilder.append(parent.getPath()).append("/");
			}
			return pathBuilder.append(name).toString();
		}

		@Override
		public String toString() {
			return getPath();
		}
	}

	private static class Directory extends Entry {

		private ArrayList<Entry> children;

		Directory(String name, Directory parent) {
			super(name, parent);
			this.children = new ArrayList<>();
		}

		@Override
		public boolean isDirectory() {
			return true;
		}

		public Entry add(String name, boolean isDirectory) {
			Entry entry = isDirectory ? new Directory(name, this) : new File(name, this);
			children.add(entry);
			return entry;
		}

		public Directory goBackBy(int steps) {
			Directory pwd = this;
			for (int i = 0; i < steps; i++) {
				pwd = pwd.getParent();
			}
			return pwd;
		}

	}

	private static class File extends Entry {

		File(String name, Directory parent) {
			super(name, parent);
		}

		@Override
		public boolean isDirectory() {
			return false;
		}

	}

	private static class Tuple<S, T> {
		private final S first;
		private final T second;

		/**
		 * @param first
		 * @param second
		 */
		Tuple(S first, T second) {
			this.first = first;
			this.second = second;
		}

		/**
		 * @return the first
		 */
		public S getFirst() {
			return first;
		}

		/**
		 * @return the second
		 */
		public T getSecond() {
			return second;
		}

	}

	private final Directory root;
	private Entry longest;

	public LongestPathInFileSystem(String path) {
		this.root = new Directory(".", null);
		initializeFileSystem(path);
	}

	/**
	 * @param path
	 */
	private void initializeFileSystem(String path) {
		String[] splits = path.split("\\n");
		if (splits.length > 0) {
			Directory currentDir = (Directory) root.add(splits[0], true);
			longest = currentDir;
			Entry entry = null;
			int previousTabs = 0;
			for (int i = 1; i < splits.length; i++) {
				String token = splits[i];
				Tuple<Integer, String> tuple = detectAndRemoveTabs(token);
				if (tuple.getFirst() < previousTabs) {
					currentDir = currentDir.goBackBy(previousTabs - tuple.getFirst());
					if (entry.isDirectory()) {
						currentDir = currentDir.getParent();
					}
				}
				previousTabs = tuple.getFirst();
				token = tuple.getSecond();
				if (token.contains(".")) {
					entry = currentDir.add(token, false);
				} else {
					entry = currentDir.add(token, true);
					currentDir = (Directory) entry;
				}
				if (entry.getPath().length() > longest.getPath().length()) {
					longest = entry;
				}
			}
		}
	}

	/**
	 * Detect the no of tabs and remove the tabs from string
	 * 
	 * @param str
	 * @return A {@link Tuple} containing no of tabs and string after removing the
	 *         tabs
	 */
	private Tuple<Integer, String> detectAndRemoveTabs(String str) {
		int tabCount = 0;
		int index;
		while ((index = str.indexOf(TAB)) != -1) {
			str = str.substring(index + TAB.length());
			tabCount++;
		}
		return new Tuple<Integer, String>(tabCount, str);
	}

	public String getLongestPath() {
		if (longest != null) {
			return longest.getPath();
		}
		return "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestPathInFileSystem prob = new LongestPathInFileSystem(
				"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext");
		System.out.println(prob.getLongestPath());
	}

}
