/*
 * File name:  SelectionSort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 25, 2020
 *
 */
package com.botka.data.set.visualizer.sort;

import com.botka.data.set.visualizer.data.DataSet;
import com.botka.data.set.visualizer.step.SortStep;
import com.botka.data.set.visualizer.step.StepResult;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class SelectionSort extends Sort
{

	private int mSubArrayindex; // this is a virtual sub array
	private Comparable mCurrentMin;
	/**
	 * @param sortAlgo
	 * @param dataset
	 * @param numberOfSteps
	 */
	public SelectionSort( DataSet<?> dataset, IFinishedListener finishedListener)
	{
		super("Selection Sort", dataset, -1);
		this.mSubArrayindex = -1;
		this.mCurrentMin = null;
		
	}

	/**
	 * 
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
	 * 
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
		
			if (index + 1 < set.size())
			{
				//Sort logic here
				//find minumum and section off
				if (index > this.mSubArrayindex) 
				{
					if (this.mCurrentMin != null)
					{
						
						Comparable element = set.get(index);
						if (this.mCurrentMin.compareTo(element) > 0) // found new min
						{
							this.mCurrentMin = element;
							if (this.mSubArrayindex == -1)
								this.mSubArrayindex = 0; // starts the sub array section
							
							set.swap(this.mSubArrayindex, index);	
						}
					}
					else 
					{
						this.mCurrentMin = set.get(index);
						if (this.mSubArrayindex == -1)
							this.mSubArrayindex = 0; // starts the sub array section
						else
							set.swap(this.mSubArrayindex, index);
					}
					// find min
				}
				else //this part is already sorted
				{
					// continue
				}
				
				index++;
			}
			else // last element in the array
			{
				index = 0;
				this.mSubArrayindex++;
				this.mCurrentMin = null;
			}
				
		
			set.getPointerInfo().setPointerPosition(index);
			set.inncrementComparrisons();
			
			
			return new SortStep(index, stepCount++, !canStep);
		}
		else
			super.onFinished();
		
		return new SortStep(super.getDataSet().getPointerInfo().getPointerPosition(), stepCount, true);
		
	}

	

	/**
	 * 
	 */
	@Override
	public StepResult onStep(int step)
	{
		return (StepResult)this.onSortingStep(step);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void sort()
	{
		// TODO Auto-generated method stub

	}

}
