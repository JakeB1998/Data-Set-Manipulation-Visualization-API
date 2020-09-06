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
import com.botka.data.set.visualization.api.LifeCycleCallbackListener;
import com.botka.data.set.visualization.api.data.DataPeekListener;
import com.botka.data.set.visualization.api.render.engine.Render;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class Visualizer implements Render, LifeCycleCallbackListener {

	private DataSet<?> mDataset;

	/**
	 * 
	 */
	public Visualizer(DataSet<?> dataSet) {
		this.mDataset = dataSet;
	}

	public DataSet getWorkingDataSet() {
		return this.mDataset;
	}

	/**
	 * 
	 * @param Input Dataset
	 */
	private void setWorkingDataset(DataSet set) {
		this.mDataset = set;
	}

	/**
	 * Inserts data into visualzier
	 * 
	 * @param set
	 */
	public void insertData(DataSet set) {
		boolean initialized = this.isInitialized();
		if (initialized)
			if (this.isRunning())
				this.onStop();
		this.setWorkingDataset(set);
		if (initialized)
			this.onStart();
		else
			this.init();

	}

	public abstract void init();

	public abstract void drawPointer(DataSet<?> set);

	public abstract void render(DataSet<? extends Comparable> set);

	public abstract void onFinishedAnimation();

	public abstract void onDataChanged(Object oldObj, Object newObj, int oldIndex, int newIndex); // multiple
																									// interperations
																									// of
																									// parameters

	public abstract void onDataAdded(Object newObj, int index, boolean sizeIncreased);

	public abstract void onDataRemoved(Object oldObj, int index, boolean sizeDecreased);

	public abstract void onDataMoved(Object obj, int oldIndex, int newIndex);

	public abstract String getTitle();

	public abstract void registerOnDataPeekCallback(DataPeekListener callback);

	public abstract void setPrefixTitle(String prefixTitle);

	public abstract void setTitle(String title);

}
