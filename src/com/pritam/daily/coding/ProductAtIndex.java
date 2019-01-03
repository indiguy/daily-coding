/**
 * 
 */
package com.pritam.daily.coding;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author pribiswas
 *
 */
public class ProductAtIndex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// withDivision();
		withoutDivision();
	}

	private static void withDivision() {
		int[] array = new int[] { 6, 2, 3, 5 };

		BigInteger multiplication = BigInteger.ONE;

		for (int i = 0; i < array.length; i++) {
			multiplication = multiplication.multiply(BigInteger.valueOf(array[i]));
		}

		BigInteger[] result = new BigInteger[array.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = multiplication.divide(BigInteger.valueOf(array[i]));
		}

		System.out.println(Arrays.asList(result));
	}

	/**
	 * 
	 */
	private static void withoutDivision() {
		int[] array = new int[] { 6, 2, 3, 5 };
		BigInteger[] result = new BigInteger[array.length];

		// from beginning
		BigInteger temp = BigInteger.ONE;
		for (int i = 0; i < array.length; i++) {
			result[i] = temp;
			temp = temp.multiply(BigInteger.valueOf(array[i]));
		}
		// from end
		temp = BigInteger.ONE;
		for (int i = array.length - 1; i >= 0; i--) {
			result[i] = temp.multiply(result[i]);
			temp = temp.multiply(BigInteger.valueOf(array[i]));
		}

		System.out.println(Arrays.asList(result));
	}

}
