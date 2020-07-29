/*
 * File name:  AudioSetting.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 28, 2020
 *
 */
package com.botka.data.set.visualization.app.settings;

import com.botka.data.set.visualization.api.sound.engine.Audio;
import com.botka.data.set.visualization.api.sound.engine.AudioFile;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class AudioSettings extends Settings
{
	private static final Audio CLICK_AUDIO_DEFAULT = (Audio)new AudioFile("ui-sfx.wav");
	private static final Audio FINISHED_ANIMATION_AUDIO_DEFAULT = null; //TODO get default audio file
	
	
	private Audio mCLickAudio;
	private Audio mFinishedAudio;

	/**
	 * 
	 */
	public AudioSettings()
	{
		this.mCLickAudio = null;
		this.mFinishedAudio = null;
		
	}
	
	public AudioSettings(AudioSettings instance)
	{
		this();
		if (instance != null)
		{
			//TODO: Copy instance
			this.mCLickAudio = instance.mCLickAudio;
			this.mFinishedAudio = instance.mFinishedAudio;
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Audio getIndexPointerAudio()
	{
		Audio audio = this.mCLickAudio != null ? this.mCLickAudio : CLICK_AUDIO_DEFAULT;
		return audio;
	}
	
	/**
	 * 
	 * @return
	 */
	public Audio getDefaultIndexPointerAudio()
	{
		return CLICK_AUDIO_DEFAULT;
	}
	
	/**
	 * 
	 * @return
	 */
	public Audio getFinishedAnimationAudio()
	{
		Audio audio = this.mFinishedAudio != null ? this.mFinishedAudio : FINISHED_ANIMATION_AUDIO_DEFAULT;
		return audio;
	}
	
	/**
	 * 
	 * @return
	 */
	public Audio getDefaultFinishedAnimationAudio()
	{
		return FINISHED_ANIMATION_AUDIO_DEFAULT;
	}

}
