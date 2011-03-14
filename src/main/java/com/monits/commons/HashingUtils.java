package com.monits.commons;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hashing utilities.
 *
 * @author    Franco Zeoli <fzeoli@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class HashingUtils {

	/**
	 * Retrieves the file hashed by the given hashing algorithm.
	 * @param filename The path to the file to hash.
	 * @param algorithm Which hashing algorithm to use.
	 * @return The resulting hash
	 * @throws FileNotFoundException 
	 */
	public static String getFileHash(String filename, HashingAlgorithm algorithm) throws FileNotFoundException {
		return getHash(new FileInputStream(filename), algorithm);
	}
	
	/**
	 * Retrieves the given input string hashed with the given hashing algorithm.
	 * @param input The string to hash.
	 * @param algorithm Which hashing algorithm to use.
	 * @return The resulting hash
	 */
	public static String getHash(String input, HashingAlgorithm algorithm) {
		return getHash(new ByteArrayInputStream(input.getBytes()), algorithm);
	}
	
	/**
	 * Retrieves the given input string hashed with the given hashing algorithm.
	 * @param input The source from which to read the input to hash.
	 * @param algorithm Which hashing algorithm to use.
	 * @return The resulting hash
	 */
	public static String getHash(InputStream input, HashingAlgorithm algorithm) {
		byte[] buffer = new byte[1024];
		
		try {
			MessageDigest algo = MessageDigest.getInstance(algorithm.getName());
			
			int readBytes;
			do {
				readBytes = input.read(buffer);
				algo.update(buffer);
			} while (readBytes != buffer.length);
			
			byte messageDigest[] = algo.digest();

			StringBuffer hexString = new StringBuffer();
			
			for (int i = 0; i < messageDigest.length; i++) {
				String token = Integer.toHexString(0xFF & messageDigest[i]);
				
				// Make sure each is exactly 2 chars long
				if (token.length() < 2) {
					hexString.append("0");
				}
				
				hexString.append(token);
			}
			
			return hexString.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
