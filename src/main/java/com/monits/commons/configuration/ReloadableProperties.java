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
package com.monits.commons.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * ReloadableProperties.
 *
 * @copyright 2011 Monits
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class ReloadableProperties {

	private static final int DEFAULT_EXPIRATION_TIME = 300000;
	private static final String EXPIRATION_TIME_KEY = "expirationTime";
	
	private final Properties properties = new Properties();
	private long lastLoadTime;
	private final String filename;
	
	/**
	 * @param filename The filename.
	 */
	public ReloadableProperties(final String filename) throws FileNotFoundException, IOException {
		super();

		this.filename = filename;
		
		loadProperties();
	}
	
	/**
	 * Searches for the property with the specified key in this property list. 
	 * If the key is not found in this property list, the default property list, 
	 * and its defaults, recursively, are then checked. 
	 * The method returns null if the property is not found. 
	 * 
	 * @param key The property key. 
	 * @return the value in this property list with the specified key value.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String get(final String key) throws FileNotFoundException, IOException {
		if (System.currentTimeMillis() - lastLoadTime > getExpirationTime()) {
			loadProperties();
		}
		
		return properties.getProperty(key);
	}
	
	/**
	 * Loads the properties file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void loadProperties() throws FileNotFoundException, IOException {

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filename);

			properties.load(fileInputStream);
			lastLoadTime = System.currentTimeMillis();

		} finally {
			// Making sure Stream, if any, is closed.
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}

	}

	/**
	 * Retrieves the expiration time.
	 * 
	 * @return the expiration time.
	 */
	private int getExpirationTime() {
		final String value = properties.getProperty(EXPIRATION_TIME_KEY);
		
		if (value == null) {
			return DEFAULT_EXPIRATION_TIME;
		}
		
		return Integer.parseInt(value);
	}
	
}
