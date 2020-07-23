/*
 * File name:  BubbleSort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualizer.sort;

import com.botka.data.set.visualizer.data.DataSet;
import com.botka.data.set.visualizer.step.SortStep;
import com.botka.data.set.visualizer.step.StepResult;

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
		super(SORTING_ALGORITHM, set,500);
		
	}

	/**
	 * @return the step this sort just took and its result
	 */
	@Override
	public SortStep onSortingStep(int stepCount)
	{
		
		boolean canStep = this.canStep(stepCount);
		System.out.println(canStep);
		if (canStep)
		{
			DataSet set = super.getDataSet();
			//TODO do sort step here
			
			int index = set.getPointerInfo().getPointerPosition();
			
			if (index < set.size())
				index++;
				
			set.getPointerInfo().setPointerPosition(index);
			return new SortStep(index, stepCount++, !canStep);
		}
		
		return new SortStep(super.getDataSet().getPointerInfo().getPointerPosition(), stepCount, true);
		
		

	}

	/**
	 * @return true if can step else false
	 */
	@Override
	public boolean canStep(int currentStep)
	{
		return currentStep < super.getMaxAmountOfSteps();
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
