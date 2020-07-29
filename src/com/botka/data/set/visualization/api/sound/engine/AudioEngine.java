/*
 * File name:  AudioEngine.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualization.api.sound.engine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class AudioEngine
{

	private HashMap<Long,File> mAudioFiles;
	private IPlayAudio mPlayAudioCallback;
	private IAudioListener mAudioListenerCallbacks;
	
	/**
	 * 
	 */
	public AudioEngine()
	{
		this.mAudioFiles = new HashMap(0);
	}
	
	/**
	 * 
	 * @param playAudioListener
	 */
	public AudioEngine(IPlayAudio playAudioListener)
	{
		this();
		this.mPlayAudioCallback = playAudioListener;
	}
	
	/**
	 * 
	 * @param audioActionListener
	 */
	public AudioEngine(IAudioListener audioActionListener)
	{
		this();
		this.mAudioListenerCallbacks = audioActionListener;
	}
	
	/**
	 * 
	 * @param playAudioListener
	 * @param audioActionListener
	 */
	public AudioEngine(IPlayAudio playAudioListener, IAudioListener audioActionListener)
	{
		this();
		this.mPlayAudioCallback = playAudioListener;
		this.mAudioListenerCallbacks = audioActionListener;
	}
	
	/**
	 * 
	 * @param audio
	 */
	public void addAudioFile(Audio audio)
	{
		if (this.mAudioFiles != null)
		{
			if (audio != null)
			{
				Long id = new Long(audio.getID());
				if (!this.mAudioFiles.containsKey(id))
				{
					File file = audio.getFile();
					if (file != null)
					{
						this.mAudioFiles.put(id, file);
					}
				}
				else
					System.out.println("File exists through key");
			}
		}
		else
		{
			this.mAudioFiles = new HashMap<Long, File>(0);
			this.addAudioFile(audio);
		}
	}
	
	/**
	 * Plays audio by retrieving the file from the hashap of audio files using the key ID
	 * @param ID assigned to the AudioFile object connecting to the file inside the hashmap
	 */
	public void playAudio(long audioID)
	{
		if (this.mAudioFiles != null)
		{
			Long id = new Long(audioID);
			if (this.mAudioFiles.containsKey(id))
			{
				File file = this.mAudioFiles.get(id);
				if (file.exists())
				{
					
					this.playAudio(file, audioID);
				}
			}
		}
	}
	
	/**
	 * Plays audio dirrectly from the file
	 * Does not need to retrieve from hashmap.
	 * If the file is inside the hashmap it will retrieve it ID and send it to the callback interface method onAudioPlayed(id).
	 * if not the ID is passed as -1 (this is done in the method getIDfromfile()).
	 * @param audio file
	 */
	public void playAudio(File file)
	{
		if (this.mPlayAudioCallback != null)
		{
			boolean played = this.mPlayAudioCallback.playAudio(file);
			if (played == true && this.mAudioListenerCallbacks != null)
			{
				
				this.mAudioListenerCallbacks.onAudioPlayed(this.getIdFromFile(file));
			}
			
		}
	}
	
	/**
	 * 
	 * @param file
	 * @param id
	 */
	public void playAudio(File file, long id)
	{
		if (this.mPlayAudioCallback != null)
		{
			boolean played = this.mPlayAudioCallback.playAudio(file);
			if (played == true && this.mAudioListenerCallbacks != null)
			{
				this.mAudioListenerCallbacks.onAudioPlayed(id);
			}
			
		}
	}
	
	/**
	 * 
	 * @param map
	 * @param value
	 * @return
	 */
	private  <T, E> T getKeyByValue(Map<T, E> map, E value) {
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	public long getIdFromFile(File file)
	{
		if (this.mAudioFiles != null)
		{
			if (this.mAudioFiles.containsValue(file))
			{
				return this.getKeyByValue(this.mAudioFiles, file);
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @param listener
	 */
	public void registerPlayAudioListener(IPlayAudio listener)
	{
		this.mPlayAudioCallback = listener;
	}
	
	public void registerAudioListener(IAudioListener listener)
	{
		this.mAudioListenerCallbacks = listener;
	}
	
	

}
