/*
 * File name:  Sort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualizer.sort;

import com.botka.data.set.visualizer.data.DataSet;
import com.botka.data.set.visualizer.step.SortStep;
import com.botka.data.set.visualizer.step.StepOperation;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class Sort extends StepOperation
{

	private String mSortAlgorithm;
	private DataSet<?> mDataSet;
	private int mNumberOfSteps;
	private IFinishedListener mFinishedListener;
	/**
	 * 
	 */
	public Sort(String sortAlgo, DataSet dataset, int numberOfSteps)
	{
		this.mSortAlgorithm = sortAlgo;
		this.mDataSet = dataset;
		this.mNumberOfSteps = numberOfSteps;
		// TODO Auto-generated constructor stub
	}
	public abstract boolean canStep(int currentStep);
	public abstract SortStep onSortingStep(int stepCount);
	public abstract void sort();
	
	public void onFinished()
	{
		
		if (this.mFinishedListener != null)
		{
			this.mFinishedListener.onFinished();
		}
	}
	
	/**
	 * 
	 * @return Data Set
	 */
	public DataSet<?> getDataSet()
	{
		return this.mDataSet;
	}
	
	public int getMaxAmountOfSteps()
	{
		return this.mNumberOfSteps;
	}
	
	public void registerOnFinishedListener(IFinishedListener listener)
	{
		this.mFinishedListener = listener;
	}
	
	public String getAlgorithm()
	{
		return this.mSortAlgorithm;
	}

}
