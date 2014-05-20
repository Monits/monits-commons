/**
 * HashingUtilsTest.java
 *
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.utils;


import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.monits.commons.model.HashingAlgorithm;

/**
 * HashingUtilsTest
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class HashingUtilsTest {

	private static final String TEXT = "q1w2e3r4";
	private static final String TEXT_TO_MD5 = "c62d929e7b7e7b6165923a5dfc60cb56";
	private static final String TEXT_TO_SHA256 = "13a5c202e320d0bf9bb2c6e2c7cf380a6f7de5d392509fee260b809c893ff2f9";
	private static final String TEXT_TO_SHA1 = "1fc854110e5532480000542834f453de31936c2f";

	private static final String TEST_1024_BYTE_FILE = "src/test/resources/1024bytesFile";
	private static final String TEST_1025_BYTE_FILE = "src/test/resources/1025bytesFile";
	private static final String TEST_2_BYTE_FILE = "src/test/resources/2bytesFile";
	private static final String TEST_0_BYTE_FILE = "src/test/resources/0bytesFile";

	private static final String TEST_1024_BYTE_FILE_HASH = "cd3b0a56bc304075e28a20a4edd57068";
	private static final String TEST_1025_BYTE_FILE_HASH = "44d0dc734ef2d3bcaa32ff4c8e7b2419";
	private static final String TEST_2_BYTE_FILE_HASH = "60b725f10c9c85c70d97880dfe8191b3";
	private static final String TEST_0_BYTE_FILE_HASH = "d41d8cd98f00b204e9800998ecf8427e";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMD5Hash() {
		final String actualMD5 = HashingUtils.getHash(TEXT, HashingAlgorithm.MD5);
		Assert.assertNotNull(actualMD5);
		Assert.assertEquals(TEXT_TO_MD5, actualMD5);
	}

	@Test
	public void testGetSHA256Hash() {
		final String actualSHA256 = HashingUtils.getHash(TEXT, HashingAlgorithm.SHA256);
		Assert.assertNotNull(actualSHA256);
		Assert.assertEquals(TEXT_TO_SHA256, actualSHA256);
	}

	@Test
	public void testGetSHA1Hash() {
		final String actualSHA1 = HashingUtils.getHash(TEXT, HashingAlgorithm.SHA1);
		Assert.assertNotNull(actualSHA1);
		Assert.assertEquals(TEXT_TO_SHA1, actualSHA1);
	}

	/**
	 * Some tests regarding different file sizes
	 * Note that 1024 is the current size of the buffer used in getHash function.
	 */
	@Test
	public void testGetHash() throws FileNotFoundException {
		String hashTest = HashingUtils.getFileHash(TEST_0_BYTE_FILE,  HashingAlgorithm.MD5);
		Assert.assertNotNull(hashTest);
		Assert.assertEquals(TEST_0_BYTE_FILE_HASH, hashTest);

		hashTest = HashingUtils.getFileHash(TEST_2_BYTE_FILE, HashingAlgorithm.MD5);
		Assert.assertNotNull(hashTest);
		Assert.assertEquals(TEST_2_BYTE_FILE_HASH, hashTest);

		hashTest = HashingUtils.getFileHash(TEST_1024_BYTE_FILE, HashingAlgorithm.MD5);
		Assert.assertNotNull(hashTest);
		Assert.assertEquals(TEST_1024_BYTE_FILE_HASH, hashTest);

		hashTest = HashingUtils.getFileHash(TEST_1025_BYTE_FILE, HashingAlgorithm.MD5);
		Assert.assertNotNull(hashTest);
		Assert.assertEquals(TEST_1025_BYTE_FILE_HASH, hashTest);
	}
}
