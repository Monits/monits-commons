/**
 * ReloadablePropertiesTest
 *
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.configuration;


import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * ReloadablePropertiesTest
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class ReloadablePropertiesTest {

	private static final String FILE_PATH = "src/test/resources/properties/test.properties";
	private static final String WRONG_FILE_PATH = "src/test/resources/properties/test.prop";

	private static final String PROPERTY_NAME = "name";
	private static final String PROPERTY_VALUE = "Test S.A.";

//	Low value forcing to reload properties.
	private static final String EXPIRATION_TIME_KEY = "expirationTime";
	private static final int EXPIRATION_TIME_VALUE = 1000;

	private static final String MISSING_PROPERTY_NAME = "surname";

	private ReloadableProperties properties;

	@Before
	public void setUp() throws Exception {
		properties = new ReloadableProperties(FILE_PATH);
	}

	@Test(expected = FileNotFoundException.class)
	public void getPropertiesInstance() throws FileNotFoundException, IOException {
		properties = new ReloadableProperties(WRONG_FILE_PATH);
	}

	@Test
	public void getPropertiesKey() throws FileNotFoundException, IOException {
		String value = properties.get(PROPERTY_NAME);
		Assert.assertEquals(PROPERTY_VALUE, value);
	}

	@Test
	public void getPropertiesMissingKey() throws FileNotFoundException, IOException {
		String value = properties.get(MISSING_PROPERTY_NAME);
		Assert.assertNull(value);
	}

	@Test
	public void getPropertiesExpiration() throws FileNotFoundException, IOException {
		String value = properties.get(EXPIRATION_TIME_KEY);
		Assert.assertEquals(EXPIRATION_TIME_VALUE, Integer.parseInt(value));
	}
}
