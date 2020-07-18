/*
 * File name:  BubbleSort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualizer;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class BubbleSort extends Sort
{
	private int mStepsPerSecond;
	private boolean mStep;
	public static final String SORTING_ALGORITHM = "Bubble Sort";

	/**
	 * @param sortAlgo
	 */
	public BubbleSort(int StepsPerSecond)
	{
		super(SORTING_ALGORITHM);
		
	}

	@Override
	public void onSortingStep()
	{
		
		this.mStep = false;

	}

	
	@Override
	public void playSort()
	{
		

	}

	@Override
	public boolean canStep()
	{
		
		return this.mStep;
	}
	
	
	private class AsyncSort implements Runnable
	{

		private boolean mRunning;
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			
		}
		
	}

}
