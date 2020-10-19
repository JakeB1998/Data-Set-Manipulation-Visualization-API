/*
 * File name:  DataSetHistoryRecorder.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package main.com.botka.data.set.visualization.api.data;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class DataSetHistoryRecorder
{

	private DataSetHistory mHistory;
	
	/**
	 * 
	 */
	public DataSetHistoryRecorder()
	{
		this.mHistory = new DataSetHistory();
	}
	
	/**
	 * 
	 * @return
	 */
	public DataSetHistory getHistory()
	{
		return this.mHistory;
	}
	
	/**
	 * 
	 * @param set
	 */
	public void addHistroy(DataSet<?> set)
	{
		if (this.mHistory != null)
		{
			this.mHistory.addHistory(set);
		}
	}

}
