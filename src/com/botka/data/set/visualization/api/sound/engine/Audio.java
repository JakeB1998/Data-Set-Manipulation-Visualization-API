/*
 * File name:  Audio.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualization.api.sound.engine;

import java.io.File;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class Audio
{

	private long mID;
	/**
	 * 
	 */
	public Audio()
	{
		 this.mID = assignID();
	}
	
	
	protected abstract long assignID();
	public abstract File getFile();
	public long getID()
	{
		return this.mID;
	}

}
