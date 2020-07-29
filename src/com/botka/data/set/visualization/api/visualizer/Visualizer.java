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
package com.botka.data.set.visualization.api.visualizer;

import com.botka.data.set.visualization.api.data.DataSet;
import com.botka.data.set.visualization.api.render.engine.Render;

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
	public abstract void drawPointer(DataSet<?> set);
	public abstract void onFinishedAnimation();
	public abstract void onDataChanged(Object oldObj, Object newObj, int oldIndex, int newIndex); // multiple interperations of parameters
	public abstract void onDataAdded(Object newObj, int index, boolean sizeIncreased);
	public abstract void onDataRemoved(Object oldObj, int index, boolean sizeDecreased);
	public abstract void onDataMoved(Object obj, int oldIndex, int newIndex);
	
	public abstract void onStart();
	public abstract void onFinished();
	public abstract String getTitle();
	public abstract String getInfoBox1();
	public abstract String getInfoBox2();
	public abstract String getInfoBox3();
	public abstract void setPrefixTitle(String prefixTitle);
	public abstract void setTitle(String title);
	public abstract void setInfoBox1(String info);
	public abstract void setInfoBox2(String info);
	public abstract void setInfoBox3(String info);
	

}
