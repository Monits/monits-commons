/**
 * SerializationUtilsTest
 * 
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.utils;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * SerializationUtilsTest
 * 
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class SerializationUtilsTest {

	private ArrayList<String> list;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<String>();
		list.add("test1");
		list.add("test2");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testDeserialize() {
		byte[] serializeObject = SerializationUtils.serializeObject(list);
		ArrayList<String> deserializedList =(ArrayList<String>) SerializationUtils.deserializeObject(serializeObject);

		Assert.assertNotNull(deserializedList);
		Assert.assertTrue(list.equals(deserializedList));
	}

	public void testClone() {
		ArrayList<String> clone = SerializationUtils.clone(list);
		
		Assert.assertFalse(clone == list);
		Assert.assertEquals(list, clone);
	}
}
