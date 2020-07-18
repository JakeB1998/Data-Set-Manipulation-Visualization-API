/*
 * File name:  Sort.java
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
public abstract class Sort
{

	private String mSortAlgorithm;
	private DataSet mDataSet;
	/**
	 * 
	 */
	public Sort(String sortAlgo)
	{
		this.mSortAlgorithm = sortAlgo;
		// TODO Auto-generated constructor stub
	}
	public abstract boolean canStep();
	public abstract void onSortingStep();
	public abstract void playSort();
	
	public DataSet getDataSet()
	{
		return this.mDataSet;
	}
	

}
