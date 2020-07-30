/*
 * File name:  LanguageSettings.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 30, 2020
 *
 */
package com.botka.data.set.visualization.app.settings;

import com.botka.data.set.visualization.app.Language;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class LanguageSettings extends Settings
{

	private Language mLanguage;
	
	/**
	 * 
	 */
	public LanguageSettings()
	{
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param lang
	 */
	public LanguageSettings(Language lang)
	{
		this.mLanguage = lang;
	}
	
	/**
	 * 
	 * @param instance
	 */
	public LanguageSettings(LanguageSettings instance)
	{
		this.mLanguage = instance.getLanguage();
	}
	
	/**
	 * 
	 * @return mLanguage
	 */
	public Language getLanguage()
	{
		return this.mLanguage;
	}
	
	/**
	 * Sets language variable
	 * @param lang
	 */
	public void setLanguage(Language lang)
	{
		this.mLanguage = lang;
	}
	
	/**
	 * @return String representation of this object
	 */
	public String toString()
	{
		return super.toString();
	}

}
