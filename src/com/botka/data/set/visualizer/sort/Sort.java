/*
 * File name:  Sort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualizer.sort;

import com.botka.data.set.visualizer.DataSet;
import com.botka.data.set.visualizer.StepOperation;
import com.botka.data.set.visualizer.step.SortStep;

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
	/**
	 * 
	 */
	public Sort(String sortAlgo, DataSet dataset)
	{
		this.mSortAlgorithm = sortAlgo;
		this.mDataSet = dataset;
		// TODO Auto-generated constructor stub
	}
	public abstract boolean canStep();
	public abstract SortStep onSortingStep(int step);
	public abstract void sort();
	
	/**
	 * 
	 * @return Data Set
	 */
	public DataSet<?> getDataSet()
	{
		return this.mDataSet;
	}
	

}
