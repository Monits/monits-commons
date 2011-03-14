package com.monits.commons;

public enum HashingAlgorithm {
	MD5("md5"),
	SHA256("sha-256");
	
	private String name;
	
	/**
	 * Creates a new hashing algorithm.
	 * @param name The algorithm name.
	 */
	HashingAlgorithm(String name) {
		this.name = name;
	}
	
	/**
	 * Retrieves the hashing algorithm's name.
	 * @return
	 */
	public String getName() {
		return name;
	}
}
