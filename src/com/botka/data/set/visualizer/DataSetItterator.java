/*
 * File name:  DataSetItterator.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualizer;

import java.util.Iterator;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class DataSetItterator
{

	private int mPointerPosition;
	private Iterator mItterator;
	/**
	 * 
	 */
	public DataSetItterator(Iterator iterator)
	{
		this.mPointerPosition = 0;
		// TODO Auto-generated constructor stub
	}
	
	public void incrementPosition()
	{
		this.mPointerPosition++;
	}
	
	
	public int getPointerPosition()
	{
		return this.mPointerPosition;
	}
	
	public void setPointerPosition(int position)
	{
		this.mPointerPosition = position;
	}

}
