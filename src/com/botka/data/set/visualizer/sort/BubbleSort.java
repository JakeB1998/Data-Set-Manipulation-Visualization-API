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
	public BubbleSort(DataSet set, IFinishedListener finishedListener)
	{
		super(SORTING_ALGORITHM, set, Integer.MAX_VALUE);
		super.registerOnFinishedListener(finishedListener);
		
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
			DataSet<Comparable> set = (DataSet<Comparable>) super.getDataSet();
			//TODO do sort step here
			
			int index = set.getPointerInfo().getPointerPosition();
			System.out.println(index);
			
			
			if (index + 1 < set.size())
			{
				int compare = set.get(index).compareTo(set.get(index + 1));
				if (compare > 0) // swap
				{
					System.out.println("Swaped: ");
					set.swap(index, index + 1);
				}
				index++;
			}
			else
				index = 0;
				
		
			set.getPointerInfo().setPointerPosition(index);
			set.inncrementComparrisons();
			
			
			return new SortStep(index, stepCount++, !canStep);
		}
		else
			super.onFinished();
		
		return new SortStep(super.getDataSet().getPointerInfo().getPointerPosition(), stepCount, true);
		
		

	}

	/**
	 * @return true if can step else false
	 */
	@Override
	public boolean canStep(int currentStep)
	{
		boolean notSorted = false;
		DataSet<Comparable> set = (DataSet<Comparable>) super.getDataSet();
		if (set  != null)
		{
			for (int i =0; i < set.size(); i++)
			{
				if (i + 1 < set.size())
				{
					if (set.get(i).compareTo(set.get(i + 1)) > 0)
					{
						return true;
					}
				}
			}
			
			return false;
			
		}
		else
			System.err.print("Dataset is null");
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
