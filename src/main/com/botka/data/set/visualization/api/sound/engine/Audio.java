/*
 * File name:  Audio.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package main.com.botka.data.set.visualization.api.sound.engine;

import java.io.File;

/**
 * Base class for a singular instance of Audio
 *
 * @author Jake Botka
 *
 */
public abstract class Audio {

	private long mID;

	/**
	 * 
	 */
	public Audio() {
		this.mID = assignID();
	}

	protected abstract long assignID();

	public abstract byte[] getData();

	public abstract File getFile();

	/**
	 * 
	 * @return
	 */
	public long getID() {
		return this.mID;
	}

}
