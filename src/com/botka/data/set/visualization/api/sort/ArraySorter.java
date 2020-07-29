/*
 * File name:  ArraysUtil.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualization.api.sort;

/**
 * ArraySorter Class that sorts arrays rangind from primitives to generics using type paramaters
 *
 * @author Jake Botka
 *
 */
public class ArraySorter
{

	/**
	 * Allows objects to be sorted as long as they extend Comparbale
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T findMaxinArray(T[] arr)
	{
		T max = null;
		if (arr != null)
		{
			for (int i =0; i < arr.length; i++)
			{
				if (i == 0)
				{
					max = arr[i];
				}
				else
				{
					if (arr[i].compareTo(max) > 0)
					{
						max = arr[i];
					}
				}
				
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static int findMaxInArray(int[] arr)
	{
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++)
		{
			max = arr[i] > max ? arr[i] : max;
		}
		return max;
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static long findMaxInArray(long[] arr)
	{
		long max = Long.MIN_VALUE;
		for (int i = 0; i < arr.length; i++)
		{
			max = arr[i] > max ? arr[i] : max;
		}
		return max;
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static double findMaxInArray(double[] arr)
	{
		double max = Double.MIN_VALUE;
		for (int i = 0; i < arr.length; i++)
		{
			max = arr[i] > max ? arr[i] : max;
		}
		return max;
	}
}
