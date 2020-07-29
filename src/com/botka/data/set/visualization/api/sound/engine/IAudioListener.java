/*
 * File name:  IAudioListener.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualization.api.sound.engine;

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
