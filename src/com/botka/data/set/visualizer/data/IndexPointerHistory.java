/*
 * File name:  IndexPointerHistory.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 17, 2020
 *
 */
package com.botka.data.set.visualizer.data;

import java.util.Stack;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class IndexPointerHistory<T> extends Stack<T>
{

	private int mMaxBufferSize, mCurrentSize;
	
	
	/**
	 * 
	 */
	public IndexPointerHistory(int maxBufferSize)
	{
		super();
		this.mMaxBufferSize = maxBufferSize;
		this.mCurrentSize = 0;
		
	}
	
	public void addHistory(T element)
	{
		super.add(element);
		if (this.mCurrentSize < this.mMaxBufferSize)
		{
			this.mCurrentSize++;
		}
	}
	public int currentSizeOfHistory()
	{
		return super.toArray().length;
	}

}
