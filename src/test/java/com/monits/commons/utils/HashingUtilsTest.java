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
		String actualMD5 = HashingUtils.getHash(TEXT, HashingAlgorithm.MD5);
		Assert.assertNotNull(actualMD5);
		Assert.assertEquals(TEXT_TO_MD5, actualMD5);
	}

	@Test
	public void testGetSHA256Hash() {
		String actualSHA256 = HashingUtils.getHash(TEXT, HashingAlgorithm.SHA256);
		Assert.assertNotNull(actualSHA256);
		Assert.assertEquals(TEXT_TO_SHA256, actualSHA256);
	}
}
