/*
 * File name:  AudioFile.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualizer.sound.engine;

import java.io.File;

import com.botka.data.set.visualizer.util.IDGenerator;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class AudioFile extends Audio
{
	private File mFile;

	/**
	 * 
	 */
	public AudioFile(String path)
	{
		this(new File(path));
	}
	
	public AudioFile(File file)
	{
		if (file.exists())
		{
			this.mFile = file;
		}
		else
		{
			System.err.print("Audio file can not be found");
		}
	}

	/* (non-Javadoc)
	 * @see com.botka.data.set.visualizer.sound.engine.Audio#assignID()
	 */
	@Override
	protected long assignID()
	{
		return IDGenerator.generateLongID();
	}

	/* (non-Javadoc)
	 * @see com.botka.data.set.visualizer.sound.engine.Audio#getFile()
	 */
	@Override
	public File getFile()
	{
		if (this.mFile != null)
		{
			if (this.mFile.exists())
			{
				return this.mFile;
			}
		}
		
		return null;
	}

}
