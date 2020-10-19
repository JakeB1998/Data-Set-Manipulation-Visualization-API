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

import main.com.botka.data.set.visualization.api.loggers.ConsoleLogger;
import main.com.botka.data.set.visualization.api.util.Loggable;

/**
 * This will hold coppies of a dataset
 *
 * @author Jake Botka
 *
 */
public class DataSetHistory implements Loggable {

	private ArrayList<DataSet<?>> mHistroy;
	private boolean mLogActions;

	/**
	 * 
	 */
	public DataSetHistory() {
		this.mHistroy = new ArrayList(0);
		this.mLogActions = false;

	}

	public void addHistory(DataSet set) {
		if (this.mHistroy != null) {
			this.mHistroy.add(set);
			if (this.isLoggingActivity())
				ConsoleLogger.Logger.log(getClass(), "Data set weas recorded into histroy", true);
		}
	}

	public DataSet<?> get(int index) {
		if (this.mHistroy != null) {
			return this.mHistroy.get(index);
		}
		return null;
	}

	public DataSet[] historyToArray() {
		if (this.mHistroy != null)
			return (DataSet[]) this.mHistroy.toArray();
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
