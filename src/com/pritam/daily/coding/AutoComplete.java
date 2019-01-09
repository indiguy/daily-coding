/**
 * 
 */
package com.pritam.daily.coding;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Design a auto complete system that returns a list of dictionary words that
 * have the given prefix.
 * 
 * @author pribiswas
 *
 */
public class AutoComplete {

	private static class TrieNode {
		private char character;
		private HashMap<Character, TrieNode> children;
		private boolean terminates;
		private Set<String> phrasesStartingHere;

		/**
		 * Default constructor that generally would be invoked for root node
		 */
		public TrieNode() {
			children = new HashMap<>();
			phrasesStartingHere = new LinkedHashSet<>();
		}

		public TrieNode(char character) {
			this();
			this.character = character;
		}

		/**
		 * Add the given word into the trie node
		 * 
		 * @param word
		 */
		public void addWord(String word) {
			if (word == null || word.trim().isEmpty()) {
				return;
			}
			word = word.trim();
			phrasesStartingHere.add(word);
			char firstCharacter = word.charAt(0);
			TrieNode child = getChild(firstCharacter);
			if (child == null) {
				child = new TrieNode(firstCharacter);
				children.put(firstCharacter, child);
			}
			if (word.length() > 1) {
				child.addWord(word.substring(1));
			} else {
				child.setTerminates(true);
			}
		}

		/**
		 * Get the child initiated at given character
		 * 
		 * @param character
		 * @return
		 */
		public TrieNode getChild(char character) {
			return children.get(character);
		}

		/**
		 * @return the terminates
		 */
		public boolean terminates() {
			return terminates;
		}

		/**
		 * @param terminates
		 *            the terminates to set
		 */
		public void setTerminates(boolean terminates) {
			this.terminates = terminates;
		}

		/**
		 * @return the character
		 */
		public char getCharacter() {
			return character;
		}

		/**
		 * @return the phrasesStartingHere
		 */
		public Set<String> getPhrasesStartingHere() {
			return phrasesStartingHere;
		}

		/**
		 * @return the children
		 */
		public HashMap<Character, TrieNode> getChildren() {
			return children;
		}

	}

	private final TrieNode root;

	public AutoComplete(List<String> words) {
		root = new TrieNode();
		for (String word : words) {
			root.addWord(word);
		}
	}

	public AutoComplete(String... words) {
		this(Arrays.asList(words));
	}

	/**
	 * Return the list of strings that matches the given prefix
	 * 
	 * @param prefix
	 * @return
	 */
	public Set<String> matches(String prefix) {
		TrieNode node = find(prefix);
		// Uses the pre-computed set which reduces the time complexity but increases the
		// space complexity
		if (node == null) {
			return Collections.emptySet();
		} else if (node.terminates()) {
			return new HashSet<>(Arrays.asList(prefix));
		} else {
			return node.getPhrasesStartingHere().stream().map(phrase -> prefix + phrase).collect(Collectors.toSet());
		}
	}

	/**
	 * Return the list of strings that matches the given prefix. Result is
	 * calculated at run time.
	 * 
	 * @param prefix
	 * @return
	 */
	public Set<String> matchesAtRuntime(String prefix) {
		TrieNode node = find(prefix);
		if (node == null) {
			return Collections.emptySet();
		} else if (node.terminates()) {
			return new HashSet<>(Arrays.asList(prefix));
		} else {
			return matches(node, prefix);
		}
	}

	private Set<String> matches(TrieNode node, String prefix) {
		final Set<String> matches = new LinkedHashSet<>();
		for (Entry<Character, TrieNode> entry : node.getChildren().entrySet()) {
			if (entry.getValue().terminates()) {
				matches.add(prefix + entry.getKey());
			} else {
				matches.addAll(matches(entry.getValue(), prefix + entry.getKey()));
			}
		}
		return matches;
	}

	/**
	 * Find the node that ends in given prefix
	 * 
	 * @param prefix
	 * @return
	 */
	private TrieNode find(String prefix) {
		TrieNode lastNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.getChild(prefix.charAt(i));
			if (lastNode == null) {
				return null;
			}
		}
		return lastNode;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AutoComplete system = new AutoComplete("dog", "deer", "deal", "donut");
		System.out.println(system.matches("do"));
		System.out.println(system.matchesAtRuntime("do"));
	}

}
