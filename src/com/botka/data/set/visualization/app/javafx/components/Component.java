/*
 * File name:  Component.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 10, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.components;

/**
 * Base class for all components
 *
 * @author Jake Botka
 *
 */
public abstract class Component
{

	protected static final int UNIQUE_ID_LENGTH = 8;
	private int mID;
	/**
	 * 
	 */
	public Component(int id)
	{
		this.mID = id;
	}
	
	public abstract int getStaticID();
	public abstract int getUniqueID();
	/**
	 * 
	 */
	public String toString()
	{
		return super.toString();
	}

}
