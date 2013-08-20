/*

   Copyright 2011 Monits

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */


package com.monits.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Charsets;
import com.monits.commons.model.HashingAlgorithm;

/**
 * Hashing utilities.
 *
 * @author    Franco Zeoli <fzeoli@monits.com>
 * @copyright 2011 Monits
 * @license   Apache 2.0 License
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class HashingUtils {

	/**
	 * Utility classes should not have a public or default constructor.
	 */
	private HashingUtils() {
		throw new AssertionError();
	}

	/**
	 * Retrieves the file hashed by the given hashing algorithm.
	 * @param filename The path to the file to hash.
	 * @param algorithm Which hashing algorithm to use.
	 * @return The resulting hash
	 * @throws FileNotFoundException
	 */
	public static String getFileHash(final String filename,
			final HashingAlgorithm algorithm) throws FileNotFoundException {
		return getHash(new FileInputStream(filename), algorithm);
	}

	/**
	 * Retrieves the given input string hashed with the given hashing algorithm.
	 * @param input The string to hash.
	 * @param algorithm Which hashing algorithm to use.
	 * @return The resulting hash
	 */
	public static String getHash(final String input, final HashingAlgorithm algorithm) {
		return getHash(new ByteArrayInputStream(input.getBytes(Charsets.UTF_8)), algorithm);
	}

	/**
	 * Retrieves the given input string hashed with the given hashing algorithm.
	 * @param input The source from which to read the input to hash.
	 * @param algorithm Which hashing algorithm to use.
	 * @return The resulting hash
	 */
	public static String getHash(final InputStream input, final HashingAlgorithm algorithm) {
		final byte[] buffer = new byte[1024];

		try {
			final MessageDigest algo = MessageDigest.getInstance(algorithm.getName());

			int readBytes;
			while ((readBytes = input.read(buffer)) != -1) {
				algo.update(buffer, 0, readBytes);
			}

			final byte messageDigest[] = algo.digest();

			final StringBuilder hexString = new StringBuilder();

			for (int i = 0; i < messageDigest.length; i++) {
				final String token = Integer.toHexString(0xFF & messageDigest[i]);

				// Make sure each is exactly 2 chars long
				if (token.length() < 2) {
					hexString.append("0");
				}

				hexString.append(token);
			}

			return hexString.toString();
		} catch (final IOException e) {
			throw new RuntimeException(e);
		} catch (final NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
