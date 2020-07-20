/*
 * File name:  BubbleSort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualizer.sort;

import com.botka.data.set.visualizer.DataSet;
import com.botka.data.set.visualizer.StepResult;
import com.botka.data.set.visualizer.step.SortStep;

/**
 * A class implementation of a Bubble sort that can be played out slowly by the RenderEngine class.
 * providing implementation from the abstract class: Sort.>
 *
 * @author Jake Botka
 *
 */
public class BubbleSort extends Sort
{
	private int mStepsPerSecond;
	
	public static final String SORTING_ALGORITHM = "Bubble Sort";

	/**
	 * @param sortAlgo
	 */
	public BubbleSort(DataSet set)
	{
		super(SORTING_ALGORITHM, set);
		
	}

	/**
	 * @return the step this sort just took and its result
	 */
	@Override
	public SortStep onSortingStep(int stepCount)
	{
		boolean d = true;
		
		DataSet set = super.getDataSet();
		//TODO do sort step here
		
		int index = set.getPointerInfo().getPointerPosition();
		index++;
		if (index < set.size())
			d = false;
		set.getPointerInfo().setPointerPosition(index);
		
		return new SortStep(index, stepCount++, d);

	}

	/**
	 * @return true if can step else false
	 */
	@Override
	public boolean canStep()
	{
		return false;
	}

	/**
	 * @param step count
	 */
	@Override
	public StepResult onStep(int step)
	{
		return (StepResult)this.onSortingStep(step);
		
	}
	

	/**
	 * Fully sorts array
	 */
	@Override
	public void sort()
	{
		

	}
	
	
	
	public String toString()
	{
		return super.toString();
	}
	
	
	

}
