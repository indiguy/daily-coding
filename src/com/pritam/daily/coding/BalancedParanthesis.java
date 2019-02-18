/**
 * 
 */
package com.pritam.daily.coding;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string of round, curly, and square open and closing brackets, return
 * whether the brackets are balanced (well-formed).
 * 
 * For example, given the string "([])[]({})", you should return true.
 * 
 * Given the string "([)]" or "((()", you should return false.
 * 
 * @author pribiswas
 *
 */
public class BalancedParanthesis {

	private static boolean isBalanced(String expr) {
		Stack<Character> stack = new Stack<>();
		Map<Character, Character> closeParens = getCloseParens();
		for (int i = 0; i < expr.length(); i++) {
			char c = expr.charAt(i);
			switch (c) {
			case '(':
			case '{':
			case '[':
				stack.push(c);
				break;
			case ')':
			case '}':
			case ']':
				if (closeParens.get(c) != stack.pop())
					return false;
				break;
			default:
				throw new IllegalArgumentException();
			}
		}
		return stack.isEmpty();
	}

	/**
	 * @return
	 */
	private static Map<Character, Character> getCloseParens() {
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');
		return map;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isBalanced("([])[]({})"));
		System.out.println(isBalanced("([)]"));
		System.out.println(isBalanced("((()"));
	}

}
