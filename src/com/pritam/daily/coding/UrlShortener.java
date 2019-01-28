/**
 * 
 */
package com.pritam.daily.coding;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Tiny url like service which shortens a long url and given a short url it
 * returns the actual long url.
 * 
 * @author pribiswas
 *
 */
public class UrlShortener {

	private static final String HTTP_PROTOCOL = "http://";
	private static final String HTTPS_PROTOCOL = "https://";

	private final ConcurrentMap<String, String> shortToLong;
	private final ConcurrentMap<String, String> longToShort;
	private final char[] alphaNumerics;
	private final Random random;
	private int keyLength;
	private String domain;

	public UrlShortener() {
		shortToLong = new ConcurrentHashMap<>();
		longToShort = new ConcurrentHashMap<>();
		// A-Za-z0-9
		alphaNumerics = new char[62];
		for (int i = 0, ascii = 0; i < alphaNumerics.length; i++) {
			if (i < 10) {
				ascii = i + 48;
			} else if (i >= 10 && i <= 35) {
				ascii = i + 55;
			} else {
				ascii = i + 61;
			}
			alphaNumerics[i] = (char) ascii;
		}
		random = new Random();
		keyLength = 8;
		domain = "mydomain.com";
	}

	/**
	 * @param keyLength
	 * @param domain
	 */
	public UrlShortener(int keyLength, String domain) {
		this();
		if (keyLength > 0) {
			this.keyLength = keyLength;
		}
		if (domain != null && !domain.trim().isEmpty()) {
			this.domain = sanitize(domain);
		}
	}

	/**
	 * Omits the protocol section
	 * 
	 * @param url
	 * @return
	 */
	private String sanitize(String url) {
		url = url.trim();
		if (url.startsWith(HTTP_PROTOCOL)) {
			url = url.substring(HTTP_PROTOCOL.length());
		} else if (url.startsWith(HTTPS_PROTOCOL)) {
			url = url.substring(HTTPS_PROTOCOL.length());
		}
		return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
	}

	/**
	 * Convert the given long url to tiny url and return the same
	 * 
	 * @param longUrl
	 * @return
	 */
	public String shorten(String longUrl) {
		longUrl = sanitize(longUrl);
		String key;
		if (longToShort.containsKey(longUrl)) {
			key = longToShort.get(longUrl);
		} else {
			// generate tiny url
			do {
				key = generateKey();
			} while (shortToLong.putIfAbsent(key, longUrl) != null);
			longToShort.put(longUrl, key);
		}
		return domain + "/" + key;
	}

	/**
	 * Given tiny url return the actual long url
	 * 
	 * @param tinyUrl
	 * @return
	 */
	public String expand(String tinyUrl) {
		final String prefix = domain + "/";
		String key = tinyUrl.substring(prefix.length());
		return shortToLong.get(key);
	}

	/**
	 * Generate a random key of alphanumeric string consisting of key length
	 * 
	 * @return
	 */
	private String generateKey() {
		StringBuilder keyBuilder = new StringBuilder();
		for (int i = 0; i < keyLength; i++) {
			int charIndex = random.nextInt(alphaNumerics.length);
			keyBuilder.append(alphaNumerics[charIndex]);
		}
		return keyBuilder.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UrlShortener u = new UrlShortener(6, "www.tinyurl.com");
		String urls[] = { "www.google.com/", "www.google.com", "http://www.yahoo.com", "www.yahoo.com/",
				"www.amazon.com", "www.amazon.com/page1.php", "www.amazon.com/page2.php", "www.flipkart.in",
				"www.rediff.com", "www.techmeme.com", "www.techcrunch.com", "www.lifehacker.com", "www.icicibank.com" };

		for (int i = 0; i < urls.length; i++) {
			final String tiny = u.shorten(urls[i]);
			System.out.println("URL:" + urls[i] + "\tTiny: " + tiny + "\tExpanded: " + u.expand(tiny));
		}

	}

}
