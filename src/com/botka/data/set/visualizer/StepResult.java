package com.botka.data.set.visualizer;

/**
 * 
 * A class to represent the results of a singular step
 * This is an abstract class to be implemented by its sub classes.
 *
 * @author Jake Botka
 *
 */
public abstract class StepResult
{

	private int mStepCount; //count of steps
	
	/**
	 * 
	 * @param stepCount
	 */
	public StepResult(int stepCount)
	{
		this.setStepCount(stepCount);
	}
	
	/**
	 * 
	 * @return the amount of steps
	 */
	public int getStepCount()
	{
		return mStepCount;
	}
	
	/**
	 * 
	 * @param mStepCount
	 */
	public void setStepCount(int mStepCount)
	{
		this.mStepCount = mStepCount;
	}
	
	/**
	 * @return String reprensentation of this object
	 */
	public String toString()
	{
		return "\nStep count: " + String.valueOf(this.mStepCount);
	}

}
