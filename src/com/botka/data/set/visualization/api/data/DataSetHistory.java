/*
 * File name:  DataSetHistory.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package com.botka.data.set.visualization.api.data;

import java.util.ArrayList;

import com.botka.data.set.visualization.api.loggers.ConsoleLogger;

/**
 * This will hold coppies of a dataset
 *
 * @author Jake Botka
 *
 */
public class DataSetHistory
{

	private ArrayList<DataSet<?>> mHistroy;
	/**
	 * 
	 */
	public DataSetHistory()
	{
		this.mHistroy = new ArrayList(0);
		
	}
	
	public void addHistory(DataSet set)
	{
		if (this.mHistroy != null)
		{
			this.mHistroy.add(set);
			ConsoleLogger.Logger.log(getClass(), "Data set weas recorded into histroy", true);
		}
	}
	
	public DataSet<?> get(int index)
	{
		if (this.mHistroy != null)
		{
			return this.mHistroy.get(index);
		}
		return null;
	}
	
	public DataSet[] historyToArray()
	{
		if (this.mHistroy != null)
			return (DataSet[])this.mHistroy.toArray();
		return null;
	}

}
