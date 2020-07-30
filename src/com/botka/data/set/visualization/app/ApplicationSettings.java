package com.botka.data.set.visualization.app;

import java.io.Serializable;
import java.util.Arrays;

import com.botka.data.set.visualization.app.settings.AudioSettings;
import com.botka.data.set.visualization.app.settings.LanguageSettings;
import com.botka.data.set.visualization.app.settings.RenderSettings;
import com.botka.data.set.visualization.app.settings.Settings;

public class ApplicationSettings implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8830688774292143810L;
	private static final int CYCLES_PER_SECOND_DEFAULT = 120;
	private Settings[] mSettings;
	private boolean mAllowDuplicates;
	private int mIndex;
	
	
	/**
	 * 
	 */
	public ApplicationSettings()
	{
		this.mSettings = new Settings[0];
		mIndex = 0;
		this.mAllowDuplicates = false;
		this.loadDefaults();
	}
	
	/**
	 * Constructor the deep copies another instance into this new instance
	 * @param instance
	 */
	public ApplicationSettings(ApplicationSettings instance)
	{
		this();
		if (instance != null)
		{
			this.mSettings = instance.getAllSettings();
		}
	}
	

	/**
	 * Loads default setting objects
	 */
	private void loadDefaults()
	{
		this.addElement(new AudioSettings());
		this.addElement(new RenderSettings());
		this.addElement(new LanguageSettings(Language.English));
	}
	
	
	/**
	 * 
	 * @param setting
	 * @return if the setting was added
	 */
	public boolean addSettings(Settings setting)
	{
		if (this.mSettings != null)
		{
			boolean duplicate = false;
			for (int i =0; i < this.mSettings.length; i++)
			{
				if (setting.equals(this.mSettings[i]))
				{
					duplicate = true;
				}
			}
			
			if (!duplicate)
			{
				return this.addElement(setting);
				
			}
			else
			{
				//TODO
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param setting
	 * @return
	 */
	private boolean addElement(Settings setting)
	{
		if (this.mSettings != null)
		{
			boolean ready = false;
			if (!this.mAllowDuplicates)
				ready = true;
			
			if (ready)
			{
			    if (this.mIndex < this.mSettings.length)
				{
					this.mSettings[this.mIndex] = setting;
				}
				else // size has been reached
				{
					Settings[] tempS = new Settings[this.mSettings.length + 10];
					for (int i =0; i < this.mSettings.length; i++)
					{
						tempS[i] = this.mSettings[i];
					}
					
					this.mSettings = tempS;
					tempS = null;
					return this.addElement(setting); //recalls code with new array size
				}
					
				this.mIndex++;
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Settings[] getAllSettings()
	{
		return this.mSettings;
	}
	
	/**
	 * For when programmers add more setting implementationss
	 * This gets all settings downcasted to Type T.
	 * @param classType
	 * @return
	 */
	public <T extends Settings> T getSetting(Class<T> classType)
	{
		if (this.mSettings != null)
		{
			for (int i =0; i < this.mSettings.length; i++)
			{
				Settings setting = this.mSettings[i];
				if (classType.isInstance(setting))
				{
					return  (T)setting;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * For when programmers add more setting implementations.
	 * This gets all settings downcasted to Type T.
	 * @param classType
	 * @return Array of Type T which is part Of the class Settings
	 */
	public <T extends Settings> T[] getSettings(Class<T> classType)
	{
		if (this.mSettings != null)
		{
			Object[] arr = new Object[this.mSettings.length];
			int index = 0;
			for (int i =0; i < this.mSettings.length; i++)
			{
				Settings setting = this.mSettings[i];
				if (classType.isInstance(setting))
				{
					arr[index] = setting;
					index++;
				}
			}
			
			return (T[])Arrays.copyOf(arr, index + 1);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public AudioSettings getAudioSettings()
	{
		if (this.mSettings != null)
		{
			return this.getSetting(AudioSettings.class);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public LanguageSettings getLanguageSettings()
	{
		if (this.mSettings != null)
		{
			return this.getSetting(LanguageSettings.class);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public RenderSettings getRenderSettings()
	{
		if (this.mSettings != null)
		{
			return this.getSetting(RenderSettings.class);
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getAllowsDuplicates()
	{
		return this.mAllowDuplicates;
	}
	
	/**
	 * 
	 * @param allowDuplicates
	 */
	public void setAllowDuplicates(boolean allowDuplicates)
	{
		this.mAllowDuplicates = allowDuplicates;
	}
	
	
	

}
