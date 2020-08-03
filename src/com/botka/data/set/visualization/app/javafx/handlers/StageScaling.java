/*
 * File name:  StageScaling.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.handlers;

import com.botka.data.set.visualization.api.loggers.ConsoleLogger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class StageScaling
{

	
	private double mScaleX;
	private double mScaleY;
	/**
	 * 
	 */
	public StageScaling()
	{
		
		this.mScaleX = 1.0;
		this.mScaleY = 1.0;
		
	}
	
	public ChangeListener<? super Number> getHeightScaling()
	{
		return (ChangeListener)new HeightScaling();
	}
	
	public ChangeListener<? super Number> getWidthScaling()
	{
		return (ChangeListener)new WidthScaling();
	}
	/**
	 * 
	 * <insert class description here>
	 *
	 * @author Jake Botka
	 *
	 */
	private class HeightScaling implements ChangeListener<Double>
	{

		@Override
		public void changed(ObservableValue<? extends Double> observable,
				Double oldValue, Double newValue)
		{
			//TODO
			
			double x = oldValue.doubleValue() - newValue.doubleValue();
			double change = x / oldValue.doubleValue();
			mScaleY = (double)mScaleY + (mScaleY * change);
			System.out.println(mScaleY);
		
			
		}
		
	}
	
	/**
	 * 
	 * <insert class description here>
	 *
	 * @author Jake Botka
	 *
	 */
	private class WidthScaling implements ChangeListener<Double>
	{

		@Override
		public void changed(ObservableValue<? extends Double> observable,
				Double oldValue, Double newValue)
		{
			double x = oldValue.doubleValue() - newValue.doubleValue();
			double change = x / oldValue.doubleValue();
			mScaleX = mScaleX + (mScaleX * change);
			
		}
		
	}

}
