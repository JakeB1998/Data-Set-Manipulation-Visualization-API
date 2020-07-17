/*
 * File name:  DataSet.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer;

import java.util.ArrayList;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
@SuppressWarnings("rawtypes")
public class DataSet<T extends Comparable> extends ArrayList<T>
{

	private Object[] mArr;
	private boolean mNumber;
	private T mMax;
	private T mMin;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public DataSet(int size)
	{
		super(size);
		this.mArr = new Object[size];
		this.mMax = null;
		this.mMin = null;
		this.mNumber = this.isNumberArray();
		System.out.println(this.mNumber);
		
		
	}
	
	/**
	 * 
	 * @param arr
	 */
	public DataSet(T[] arr)
	{
		super(arr.length);
		for (int i = 0; i < arr.length; i++)
		{
			super.add(arr[i]);
		}
	}
	
	public boolean isNumberArray()
	{
		
		for (Object element : super.toArray())
		{
			
			if (this.isNumber((T)element) == false)
			{
				return false;
			}
			
		}
		return true;
	}
	
	public boolean isNumber(T element)
	{
		if (element instanceof Number)
			return true;
		return false;
	}
	
	/**
	 * Finds the max and mim element for scaling information for the render engine.
	 * this is achieved in one singualr pass through the dataset
	 */
	public T[] findMaxMin()
	{
		Object[] arr = null;
		if (this.mArr != null)
		{
			for (int i =0; i < super.size(); i++)
			{
				T compare = super.get(i);
				
				if (this.mMax == null)
					this.mMax = compare;
				else
					if (compare.compareTo(this.mMax) > 0)
						this.mMax = compare;
				
				if (this.mMin == null)
					this.mMin = compare;
				else
					if(compare.compareTo(this.mMin) < 0)
						this.mMin = compare;
					
				
			}
			
			arr = new Object[2];
			arr[0] = this.mMax;
			arr[1] = this.mMin;
		}
		return (T[])arr;
	}
	
	@Override
	public boolean add(T element)
	{
		
		if (this.mMax != null)
			this.mMax = this.mMax.compareTo(element) > 0 ? this.mMax : element;
		else
			this.mMax = element;
		
		if (this.mMin != null)
			this.mMin = this.mMin.compareTo(element) < 0 ? this.mMin : element;
		else
			this.mMin = element;
		
		
		return super.add(element);
		
	}
	
	/**
	 * 
	 * @return T
	 */
	public T getMax()
	{
		
		return this.mMax;
	}
	
	/**
	 * Gets value of an object utilizing the comperable interface method by comparing the parameter by 0 revealing its value
	 * @param value
	 * @return
	 */
	public double getValue(T value)
	{
		
		return this.parseValue(value.toString().toCharArray()); // this determines the value of paramter
	}
	
	/**
	 * parcases value
	 * @param arr
	 * @return
	 */
	public double parseValue(char[] arr)
	{
		double x = Double.NaN;
		if (arr != null)
		{
			boolean parcable = true;
			for (int i =0; i < arr.length; i++)
			{
				if (Character.isDigit(arr[i]) == false)
				{
					parcable = false;
					break;
				}
			}
			
			if (parcable)
			{
				try
				{
					x = Double.parseDouble(new String(arr));
				}
				catch(Exception e)
				{
					
				}
				
			}
		}
		return x;
	}
	/**
	 * 
	 * @return T
	 */
	public T getMin()
	{
		return this.mMin;
	}
	


}
