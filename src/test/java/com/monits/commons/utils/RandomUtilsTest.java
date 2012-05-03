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
/**
 * Random Utils Test
 *
 * @copyright	2011 Monits
 * @license 	Apache 2.0 License
 * @version 	Release: 1.0.0
 * @link 		http://www.monits.com/
 * @since 		1.0.0
 */
package com.monits.commons.utils;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.monits.commons.utils.RandomUtils;

/**
 * Random Utils test.
 *
 * @author 		Gaston Muñiz <gmuniz@monits.com>
 * @copyright 	2011 Monits
 * @license 	Apache 2.0 License
 * @version 	Release: 1.0.0
 * @link 		http://www.monits.com/
 * @since 		1.0.0
 */
public class RandomUtilsTest {

	private int amount;

	private String actual;

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

	/**
	 * Test for the method RandomUtils.generateRandomString(int amount)
	 */
	@Test
	public void testGenerateRandomString() {

		// Attempt a negative number
		this.amount = -1;
		this.actual = RandomUtils.generateRandomString(amount);

		String expected = "";
		Assert.assertEquals("String build with -1, Failure", expected, actual);

		// Check zero
		this.amount = 0;
		this.actual = RandomUtils.generateRandomString(amount);

		Assert.assertEquals("String build with 0, Failure", "", actual);

		// Positive numbers should work
		amount = 10;
		this.actual = RandomUtils.generateRandomString(amount);

		Assert.assertNotNull("Null generated, string expected", actual);
		Assert.assertEquals("Lenght does not match", amount, actual.length());
	}
}
