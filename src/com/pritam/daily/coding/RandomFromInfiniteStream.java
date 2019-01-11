/**
 * 
 */
package com.pritam.daily.coding;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Given a stream of elements too large to store in memory, pick a random
 * element from the stream with uniform probability.
 * 
 * @author pribiswas
 *
 */
public class RandomFromInfiniteStream<T> {

	private final Iterator<T> stream;
	private final Random random;
	private int counter;
	private T picked;

	public RandomFromInfiniteStream(Iterator<T> stream) {
		this.stream = stream;
		this.random = new Random();
	}

	public T pickRandom() {
		if (stream.hasNext()) {
			T current = stream.next();
			counter++;
			if (counter == 1) {
				picked = current;
			} else {
				int randomIndex = random.nextInt(counter);
				if (randomIndex == counter - 1) {
					picked = current;
				}
			}
			return picked;
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final List<Integer> input = Arrays.asList(2, 1, 5, 3, 7);
		RandomFromInfiniteStream<Integer> problem = new RandomFromInfiniteStream<>(input.iterator());
		Integer random;
		while ((random = problem.pickRandom()) != null) {
			System.out.println("Random: " + random);
		}
	}

}
