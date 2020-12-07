/*
 * File name:  AudioFile.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package main.com.botka.data.set.visualization.api.sound.engine;

import java.io.File;
import java.util.Random;

import main.org.botka.openjdk.api.addon.security.IDGenerator;



/**
 * Base class that represents an audio file.
 *
 * @author Jake Botka
 *
 */
public class AudioFile extends Audio {
	private File mFile;

	/**
	 * Constructor with the arguents of the path to construct the abstract file
	 * object that will connect to the data of the audio file
	 * 
	 * @param the path to the audio file
	 */
	public AudioFile(String path) {
		this(new File(path));
	}

	/**
	 * 
	 * @param the File connecting to the audio data.
	 */
	public AudioFile(File file) {
		if (file.exists()) {
			this.mFile = file;
		} else {
			System.err.print("Audio file can not be found");
		}
	}

	/**
	 * 
	 */
	@Override
	protected long assignID() {
		return IDGenerator.generateID(new Random().nextInt(), 8);
	}

	/**
	 * @return the audio file
	 */
	@Override
	public File getFile() {
		if (this.mFile != null) {
			if (this.mFile.exists()) {
				return this.mFile;
			}
		}

		return null;
	}

	@Override
	public byte[] getData() {
		
		return null;
	}

}
