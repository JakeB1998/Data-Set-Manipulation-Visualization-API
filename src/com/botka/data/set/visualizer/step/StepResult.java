package com.botka.data.set.visualizer.step;

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
	private boolean mDone;
	
	/**
	 * 
	 * @param stepCount
	 */
	public StepResult(int stepCount,boolean done)
	{
		this.setStepCount(stepCount);
		this.mDone = done;
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
	 * @return state of the playable operation
	 */
	public boolean isDone()
	{
		return this.mDone;
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
