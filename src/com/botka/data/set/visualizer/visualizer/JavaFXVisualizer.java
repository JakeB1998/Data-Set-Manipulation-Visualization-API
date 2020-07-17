/*
 * File name:  JavaFXVisualizer.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer.visualizer;

import java.util.Scanner;

import com.botka.data.set.visualizer.DataSet;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class JavaFXVisualizer extends Visualizer
{
	private Stage mStage;
	private Scene mScene;
	private Canvas mCanvas;
	private GraphicsContext mContext;
	private double mGroundX, mGroundY,  mScaleXFactor, mScaleYFactor;
	private boolean mReady;
	
	public final double MAX_SCALE_X = 50.0;
	public final double MAX_SCLAE_Y = 100.0;
	

	/**
	 * 
	 * @param stage
	 */
	public JavaFXVisualizer(DataSet<?> dataSet, Stage stage)
	{
		super(dataSet);
		this.mStage = stage;
		
	}
	
	/**
	 * 
	 * @param stage
	 * @param scene
	 */
	public JavaFXVisualizer(DataSet<?> dataSet,Stage stage, Scene scene)
	{
		this(dataSet, stage);
		this.mScene = scene;
		this.mStage.setScene(this.mScene);
	}
	

	/**
	 * 
	 * @param stage
	 * @param scene
	 * @param canvas
	 */
	public JavaFXVisualizer(DataSet<?> dataSet,Stage stage, Scene scene, Canvas canvas)
	{
		this(dataSet, stage,scene);
		this.mCanvas = canvas;
		// if canvas null then assign context null otherwise call canvas getContext method;
		
	
		
	}
	
	/**
	 * 
	 */
	@Override
	public void init()
	{
		this.mContext = this.mCanvas != null ? this.mCanvas.getGraphicsContext2D() : null;
		this.mGroundX = this.mCanvas != null ? 0 : -1;
		this.mGroundY = this.mCanvas != null ? (int) this.mCanvas.getHeight() : -1;
		this.mReady = this.checkIfReady();
	
		DataSet set = super.getWorkingDataSet();
		if(set != null)
		{
			if (set.isNumberArray())
			{
				double max = set.parseValue(set.getMax().toString().toCharArray());
				double min = set.parseValue(set.getMin().toString().toCharArray());
				
				
				this.mScaleYFactor =  this.mCanvas.getHeight() / ((max - min)) ;
				this.mScaleXFactor = this.mCanvas.getWidth() / set.size();
				if (this.mScaleXFactor > MAX_SCALE_X)
					this.mScaleXFactor = MAX_SCALE_X;
				
				while (this.mCanvas.getHeight() - this.mScaleYFactor * max < 0) //the value can not be represented in the current scale without cutting off screen
				{
					System.out.println("Scale factor was to large from the larges value at : " + this.mScaleYFactor);
					this.mScaleYFactor /=  2;
				}
			}
			
		}
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public boolean checkIfReady()
	{
		if (this.mCanvas != null && this.mContext != null
				&& this.mGroundX >= 0 && this.mGroundY >= 0 
				&& this.mScene != null && this.mStage != null)
			return true;
		
		return false;
	}
	
	public String checkErrorCode()
	{
		if (this.mReady)
		{
			return "No error";
		}
		else
		{
			//TODO: Implement error code method
			return "Method not implemented yet";
		}
	}

	/**
	 * Overridden from Interface implementation of superclass
	 */
	@Override
	public void onRender()
	{
		if (this.mReady)
		{
			DataSet set = super.getWorkingDataSet();
			double x = this.mGroundX;
			for (Object o : set.toArray())
			{
				x += this.mScaleXFactor;
				double y = this.mGroundY;
				double value = Double.NaN;
				if (set.isNumberArray())
				{
					value = set.parseValue(o.toString().toCharArray());
					y-= value * this.mScaleYFactor;
					
					if (y < 0) // being to large
						
						
					System.out.println(value);
				}
				else
				{
				
				}
				
				
				System.out.println(y);
				this.mContext.setFill(Color.BLACK);
				this.mContext.setStroke(Color.WHITESMOKE);
				this.mContext.setLineWidth(3);
				this.mContext.fillRect(x,y, this.mScaleXFactor, this.mCanvas.getHeight() - y);
				this.mContext.strokeRect(x, y, this.mScaleXFactor, this.mCanvas.getHeight() - y);
			}
		}
		else
		{
			System.err.println("There is a vairbale that is null, Call the check error code method for more information");
		}
		
	}
	
	public void renderData()
	{
		
	}

}
