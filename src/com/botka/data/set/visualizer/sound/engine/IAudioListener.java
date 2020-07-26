/*
 * File name:  IAudioListener.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualizer.sound.engine;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public interface IAudioListener
{

	public void onAudioPlayed(long id);
	public void onAudioCompleted(long id);
}
