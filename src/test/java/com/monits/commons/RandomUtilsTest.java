/**
 * 
 */
package com.monits.commons;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author rgomez
 *
 */
public class RandomUtilsTest {
	
	protected String random;
	protected int amount;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		amount = 1;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Prueba el metodo  {@link com.monits.commons.RandomUtils#generateRandomString(int)}
	 * con un valor entero 1.	
	 */
	@Test
	public void testGenerateRandomString1() {
		random = RandomUtils.generateRandomString(amount);
		Assert.assertEquals("No coincide longitud de caracteres", random.length(), RandomUtils.generateRandomString(amount).length());		
	}
	
	/**
	 * Prueba el metodo  {@link com.monits.commons.RandomUtils#generateRandomString(int)}
	 * con un valor negativo -1.
	 */
	@Test
	public void testGenerateRandomStringMenos1() {
		random = "";
		amount = amount*-1;
		Assert.assertEquals("String vacio", random.length(), RandomUtils.generateRandomString(amount).length());
	}	
	
	/**
	 * Prueba el metodo  {@link com.monits.commons.RandomUtils#generateRandomString(int)}
	 * con un valor 0.
	 */
	@Test
	public void testGenerateRandomString0() {
		random = "";
		amount -= amount;
		Assert.assertEquals("String vacio", random.length(), RandomUtils.generateRandomString(amount).length());
	}
	
}
