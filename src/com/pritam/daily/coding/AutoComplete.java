/**
 * 
 */
package com.pritam.daily.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
		private List<String> phrasesStartingHere;

		/**
		 * Default constructor that generally would be invoked for root node
		 */
		public TrieNode() {
			children = new HashMap<>();
			phrasesStartingHere = new ArrayList<>();
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
		public List<String> getPhrasesStartingHere() {
			return phrasesStartingHere;
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
	public List<String> matches(String prefix) {
		TrieNode lastNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.getChild(prefix.charAt(i));
			if (lastNode == null) {
				return Collections.emptyList();
			}
		}
		return lastNode.getPhrasesStartingHere().stream().map(phrase -> prefix + phrase).collect(Collectors.toList());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AutoComplete system = new AutoComplete("dog", "deer", "deal", "donut");
		System.out.println(system.matches("do"));
	}

}
