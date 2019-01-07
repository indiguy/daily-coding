/**
 * 
 */
package com.pritam.daily.coding;

/**
 * Implement javascript like setTimeout(function, milli)
 * 
 * @author pribiswas
 *
 */
public class SetTimeOut {

	private static void setTimeout(Runnable runnable, int delay) {
		new Thread(() -> {
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			runnable.run();
		}).start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		setTimeout(() -> System.out.println("test"), 2000);

	}

}
