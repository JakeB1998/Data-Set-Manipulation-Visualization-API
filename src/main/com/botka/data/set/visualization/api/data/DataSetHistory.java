/*
 * File name:  DataSetHistory.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package main.com.botka.data.set.visualization.api.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import main.com.botka.data.set.visualization.api.util.Loggable;

/**
 * This will hold copies of a dataset
 *
 * @author Jake Botka
 *
 */
public class DataSetHistory implements Loggable {

	private Vector<DataSet<?>> mHistroy;
	private boolean mLogActions;

	/**
	 * Constructor.
	 */
	public DataSetHistory() {
		this.mHistroy = new Vector(0);
		this.mLogActions = false;

	}

	/**
	 * Adds dat to histroy. A.K.A the array.
	 * @param set 
	 *
	 */
	public void addHistory(DataSet<?> dataSet) {
		if (this.mHistroy != null) {
			this.mHistroy.add(dataSet);
			if (this.isLoggingActivity()) {
				//ConsoleLogger.Logger.log(getClass(), "Data set weas recorded into histroy", true);
			}
		}
	}

	/**
	 * Get a copy in history by index.
	 * @param index Index of the copy requested.
	 * @return Copy of dataset at specified index
	 *
	 */
	public DataSet<?> get(int index) {
		if (this.mHistroy != null) {
			return this.mHistroy.get(index);
		}
		return null;
	}

	/**
	 * Get entire histroy in array format
	 * @return Dataset object array
	 *
	 */
	public DataSet[] historyToArray() {
		if (this.mHistroy != null)
			return (DataSet[]) this.mHistroy.toArray();
		return null;
	}
	
	/**
	 * Get entire histroy in array format
	 * @return Dataset object array
	 *
	 */
	public List<?> historyToList() {
		if (this.mHistroy != null)
			return this.mHistroy;
		return null;
	}
	
	/**
	 * 
	 * @param value The boolean value
	 */
	public void setLogActivity(boolean value) {
		this.mLogActions = true;
	}

	public boolean isLoggingActivity() {
		return this.mLogActions;
	}
}
