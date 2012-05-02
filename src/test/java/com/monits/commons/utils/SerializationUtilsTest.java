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


import java.io.Serializable;

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

	private static final String OBJECT_NAME_1 = "Object 1";
	private TestObject testObj1;

	@Before
	public void setUp() throws Exception {
		testObj1 = new TestObject(OBJECT_NAME_1);
	}

	@Test
	public void testDeserialize() {
		byte[] serializeObject = SerializationUtils.serializeObject(testObj1);
		TestObject deserializeObject =(TestObject) SerializationUtils.deserializeObject(serializeObject);
	
		Assert.assertEquals(OBJECT_NAME_1, deserializeObject.getName());
		Assert.assertTrue(testObj1.equals(deserializeObject));
	}

}

/**
 * ONLY FOR TESTING PURPOSE! 
 * 
 * Simple Object implementing serializable in order to test this methods.
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2012 Monits
 * @license Copyright (C) 2012. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
class TestObject implements Serializable {

	private static final long serialVersionUID = -4127478195289707770L;

	private String name;

	public TestObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestObject other = (TestObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}