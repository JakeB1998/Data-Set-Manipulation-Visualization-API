/*
 * File name:  Deserializer.java
 *
 * Programmer : Jake Botka
 * ULID: JMBOTKA
 *
 * Date: May 24, 2020
 *
 * Out Of Class Personal Program
 */
package com.botka.data.set.visualization.api.data.serialization;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Base class to deserialize data.
 *
 * @author Jake Botka
 *
 */
public class Deserializer {
	private static final Deserializer DESERIALIZER = new Deserializer();

	/**
	 * 
	 * @return
	 */
	public static Deserializer getDeserializer() {
		return DESERIALIZER;
	}

	/**
	 * 
	 */
	private Deserializer() {

	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public <T> T deserialize(byte[] data) throws ClassNotFoundException, IOException {
		Object obj = this.deserailizeObject(data);
		if (obj != null)
			return ((T) obj);

		return null;

	}

	/**
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object deserailizeObject(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream stream = new ByteArrayInputStream(data);
		if (stream.available() > 0) {
			ObjectInputStream in = new ObjectInputStream(stream);
			return in.readObject();
		}
		return null;

	}

}
