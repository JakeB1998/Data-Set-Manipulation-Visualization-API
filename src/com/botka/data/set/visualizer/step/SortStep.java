/*
 * File name:  SortStep.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 19, 2020
 *
 */
package com.botka.data.set.visualizer.step;

import com.botka.data.set.visualizer.StepResult;

/**
 * Class providing implementation to the abstract class: StepResult
 *
 * @author Jake Botka
 *
 */
public class SortStep extends StepResult
{
	private int mIndex, mStep;
	private boolean mDone;
	
	/**
	 * Main constructor
	 * @param index
	 * @param step
	 * @param done
	 */
	public SortStep(int index, int step, boolean done)
	{
		super(step);
		this.mIndex = index;
		this.mStep = step;
		this.mDone = done;
	}
	
	/**
	 * 
	 * @return index
	 */
	public int getIndex()
	{
		return this.mIndex;
	}
	
	/**
	 * 
	 * @return state of the playable operation
	 */
	public boolean isDone()
	{
		return this.mDone;
	}

}
