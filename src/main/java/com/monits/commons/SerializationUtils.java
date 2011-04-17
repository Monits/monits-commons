package com.monits.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Object serialization utils.
 */
public class SerializationUtils {

	/**
	 * Serializes an object into a byte array.
	 *
	 * The object is assumed to implement Serializable
	 * @param obj The object to serialize
	 * @return
	 */
	public static byte[] serializeObject(Object obj) {
		ObjectOutputStream out;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		try {
			out = new ObjectOutputStream(outputStream);

			out.writeObject(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return outputStream.toByteArray();
	}

	/**
	 * Deserialiazes an object from a byte array.
	 * @param str
	 * @return
	 */
	public static Object deserializeObject(byte[] str) {
		ObjectInputStream in;
		ByteArrayInputStream inputStream = new ByteArrayInputStream(str);
		Object ret;

		try {
			in = new ObjectInputStream(inputStream);

			ret = in.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return ret;
	}

}
