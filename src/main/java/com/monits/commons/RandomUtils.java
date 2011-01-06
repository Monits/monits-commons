/**
 * Random Utils.
 *
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.commons;

import java.util.Random;

/**
 * Random Utils.
 *
 * @author    Maximiliano Luque <mluque@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class RandomUtils {
	
	/**
	 * Generates a random String with the number of characters supplied.
	 * 
	 * @param amount The number of characters in the string.
	 * 
	 * @return The random String.
	 */
	public static String generateRandomString(int amount){
		
		Random random = new Random();
		
		char[] characters = {'0','1','2','3','4','5','6','7','8','9' ,'a','b',
				'c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p',
				'q','r','s','t','u','v','w','x','y','z'
				};
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < amount ; i++) {
			buffer.append(characters[random.nextInt(characters.length-1)]);
		}
		
		return buffer.toString();
	}
	
}
