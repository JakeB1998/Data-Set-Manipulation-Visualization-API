/*
 * File name:  Serializer.java
 *
 * Programmer : Jake Botka
 * ULID: JMBOTKA
 *
 * Date: May 24, 2020
 *
 * Out Of Class Personal Program
 */
package main.com.botka.data.set.visualization.api.data.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Base class to serialize data.
 *
 * @author Jake Botka
 *
 */
public class Serializer {

	/**
	 * 
	 */
	public Serializer() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public <T> byte[] serialize(T obj) {
		return this.serailizeObject(obj);
	}

	/**
	 * 
	 * @param data of object
	 * @return
	 */
	public byte[] serailizeObject(Object obj) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(stream);
			out.writeObject(obj);
			return stream.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
