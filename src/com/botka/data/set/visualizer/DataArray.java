/*
 * File name:  DataArray.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class DataArray<T>
{
	private T[] mArr;

	/**
	 * 
	 */
	public DataArray(T[] arr)
	{
		this.mArr = arr;
	}
	
	
	/**
	 * 
	 * @param index
	 * @return T
	 */
	public T getValueAt(int index)
	{
		T value = null;
		if (this.mArr != null)
		{
			if(index < this.mArr.length && index >= 0)
			{
				return this.mArr[index];
			}
		}
		return value;
	}

}
