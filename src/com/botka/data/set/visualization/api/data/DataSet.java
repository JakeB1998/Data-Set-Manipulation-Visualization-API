/*
 * File name:  DataSet.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualization.api.data;

import java.lang.reflect.ParameterizedType;
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

	private final DataSetHistoryRecorder RECORDER = new DataSetHistoryRecorder();
	private Object[] mArr;
	private boolean mNumber;
	private T mMax;
	private T mMin;
	private DataSetPointer mDataSetIteratorInfo;
	private DataSetHistory mHistory;
	private int mComparrisons;
	/**
	 * Main constructor
	 * @param initial size of the dynammic array
	 */
	@SuppressWarnings("unchecked")
	public DataSet(int size)
	{
		
		super(size);
		
		this.mArr = new Object[size];
		this.mMax = null; // default
		this.mMin = null; // default
		this.mNumber = this.isNumberArray();
		this.mDataSetIteratorInfo = new DataSetPointer();
		
		
	}
	
	/**
	 * Constructor that accepts an array of data to be comppied into the dynamic arraylist
	 * @param array with the type of T
	 */
	public DataSet(T[] arr)
	{
		this(arr.length);
		
		for (int i = 0; i < arr.length; i++)
		{
			super.add(arr[i]);
		}
	}
	
	/**
	 * Determines if the entire generic array is type safed with numerical values.
	 * @return true if the entire array is filled with numbers
	 */
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
	
	/**
	 * This method determines wether or not an object is an instance of the Number class defining wether this object has numerical value that can be read.
	 * @param element
	 * @return true if object is a nuber otherwise false
	 */
	public boolean isNumber(Object element)
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
	
	/**
	 * @return true if the leement was added to array otherwise false
	 */
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
	 * @return the maximum value of the array
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
		
		return this.parseValue(value); // this determines the value of paramter
	}
	
	/**
	 * parses value
	 * @param arr
	 * @return
	 */
	public double parseValue(Object value)
	{
		double x = Double.NaN;
		if (this.isNumber(value))
		{
			Number number = (Number) value;
			return number.doubleValue();
		}
		return x;
	}
	/**
	 * 
	 * @return the minimum value of the array
	 */
	public T getMin()
	{
		return this.mMin;
	}
	
	/**
	 * Swaps two elements.
	 * Hannldes with size and index shifts due to the remove method
	 * @param index1 The index of the first element
	 * @param index2 The index of the second element
	 */
	public void swap(int index1, int index2)
	{
		int shift = 0;
		if (index1 < index2)
			shift = 1; // due to remove method if removed element is a lower index then all indexes after included the swapped index shift to the left
		Object o1 = super.remove(index1);
		Object o2 = super.remove(index2 - shift); //  - 1 due to remove shift);
		System.out.println(this.getClass().getName() + "Swaped: " + this.parseValue(o1) + "," + this.parseValue(02));
		this.getPointerInfo().setPointerPosition(index1);
		super.add(index1, (T)o2);
		this.getPointerInfo().setPointerPosition(index1);
		super.add(index2, (T)o1);
	}
	
	/**
	 * 
	 * @return the pointer of this dataset wrapped into an object
	 */
	public DataSetPointer getPointerInfo()
	{
		return this.mDataSetIteratorInfo;
	}
	
	/**
	 * 
	 * @return
	 */
	public DataSetHistory getHistory()
	{
		return RECORDER.getHistory();
	}
	
	public synchronized void recordDataSet()
	{
		RECORDER.addHistroy(this);
	}
	
	/**
	 * Increments position. 
	 * This is synchronized and will block if multiple threads attempt to access resource method.
	 */
	public synchronized void inncrementPosition()
	{
		if (this.mDataSetIteratorInfo != null)
		{
			int nIndex = this.mDataSetIteratorInfo.getPointerPosition() + 1;
			if (nIndex < super.size())
				this.mDataSetIteratorInfo.incrementPosition();
			else
				this.mDataSetIteratorInfo.setPointerPosition(0);
		}
	}
	
	/**
	 * 
	 * This is synchronized and will block if multiple threads attempt to access resource method.
	 */
	public synchronized void inncrementComparrisons()
	{
		this.mComparrisons++;
	}
	
	/**
	 * 
	 * This is synchronized and will block if multiple threads attempt to access resource method.
	 */
	public synchronized void resetComparrisons()
	{
		this.mComparrisons = 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public synchronized int getAmountOfComparrisons()
	{
		return mComparrisons;
		
	}
			


}
