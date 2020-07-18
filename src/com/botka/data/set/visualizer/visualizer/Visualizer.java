/*
 * File name:  Visualizer.java
 *
 * Programmer : Jake Botka
 * ULID: JMBOTKA
 *
 * Date: Jul 16, 2020
 *
 * Out Of Class Personal Program
 */
package com.botka.data.set.visualizer.visualizer;

import com.botka.data.set.visualizer.DataSet;
import com.botka.data.set.visualizer.render.engine.Render;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class Visualizer implements Render
{
	

	private DataSet<?> mDataset;
	
	/**
	 * 
	 */
	public Visualizer(DataSet<?> dataSet)
	{
		this.mDataset = dataSet;
	}
	
	
	public DataSet getWorkingDataSet()
	{
		return this.mDataset;
	}
	
	public abstract void init();
	public abstract void drawPointer(DataSet set);
	public abstract void onDataChanged(Object oldObj, Object newObj, int oldIndex, int newIndex); // multiple interperations of parameters
	public abstract void onDataAdded(Object newObj, int index, boolean sizeIncreased);
	public abstract void onDataRemoved(Object oldObj, int index, boolean sizeDecreased);
	public abstract void onDataMoved(Object obj, int oldIndex, int newIndex);
	

}
