/*
 * File name:  JavaFXVisualizer.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer.visualizer;

import java.util.Iterator;
import java.util.Scanner;

import com.botka.data.set.visualizer.DataSet;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
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
	
	private final Color DEFAULT_BLOCK_COLOR = Color.GREY;
	private final Color DEFAULT_BLOCK_STROKE_COLOR = Color.WHITESMOKE;
	private final Color DEFUALT_POINTER_BLOCK_COLOR = Color.BLUE;
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
		 Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		 
		this.mReady = this.checkIfReady();
		if(mReady)
		{
			this.initCanvasSettings();
			this.mCanvas.setWidth(screenBounds.getWidth());
			this.mCanvas.setHeight(screenBounds.getHeight() - 100);
			//this.mStage.setResizable(false);
		
			
		}
	
		DataSet<?> set = super.getWorkingDataSet();
		if(set != null)
		{
			if (set.isNumberArray())
			{
				
				double max = set.parseValue(set.getMax());
				double min = set.parseValue(set.getMin());
				
				
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
	 * Inits the canvas settings
	 */
	private void initCanvasSettings()
	{
		
		this.mContext.setFill(Color.GREY);
		this.mContext.setStroke(Color.WHITESMOKE);
		this.mContext.setLineWidth(3);
	}
	
	
	
	/**
	 * Checks whether the visualizer has all the neccassary data non nulled to operate.
	 * @return boolean
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
	 * Called on each frame rendered with this framework implementation of JAVAFX front end frame work
	 */
	@Override
	public void onRender()
	{
		if (this.mReady)
		{
			if (this.mStage.isShowing())
			{
				
			 DataSet<?> set = super.getWorkingDataSet();
				Iterator<?> iterator = set.iterator(); // allows for set modification during iteration
				double x = this.mGroundX - this.mScaleXFactor;
	
				for (int i =0 ; i < set.size(); i++)
				{
					if (iterator.hasNext())
					{
						Object o = iterator.next();
						x = i* this.mScaleXFactor;
						double y = this.mGroundY;
						double value = Double.NaN;
						if (set.isNumber(o))
						{
							value = set.parseValue(o);
							y-= value * this.mScaleYFactor;
						}
						else // if not a number
							y = i * this.mScaleYFactor;
						
						this.drawAt(x,y, this.mScaleXFactor, this.mCanvas.getHeight() - y, DEFAULT_BLOCK_COLOR, null); // draws the specific block to scale with its value
						this.mContext.strokeRect(x, y, this.mScaleXFactor, this.mCanvas.getHeight() - y); // strokes an outline for block
					}
				
				}
				
				this.drawPointer(set); // draws the position of the pointer
			}
		}
		else
		{
			System.err.println("There is a vairbale that is null, Call the check error code method for more information");
		}
		
	}
	
	

	/**
	 * @param Data set
	 */
	@Override
	public void drawPointer(DataSet<?> set)
	{
		
		double value = set.parseValue((Comparable) set.get(set.getPointerInfo().getPointerPosition()));
		
		double x = (set.getPointerInfo().getPointerPosition() * this.mScaleXFactor);
	
		double y = this.mGroundY - this.mScaleYFactor * value;
		double h = this.mCanvas.getHeight() - y;
		
		this.mContext.setFill(DEFUALT_POINTER_BLOCK_COLOR);
		this.mContext.clearRect(x,y, this.mScaleXFactor, h);
		this.mContext.fillRect(x,y, this.mScaleXFactor, h);
		this.mContext.setFill(DEFAULT_BLOCK_COLOR);
		
	}


	/**
	 * Draws on canvas at specific location with A color deriving from the interface: Paint
	 * @param xLocation
	 * @param yLocation
	 * @param width
	 * @param height
	 * @param color
	 * @param previousColor
	 */
	public void drawAt(double x, double y, double w, double h, Color color, Color previousColor)
	{
		if (color == null) // error by programmer
			color = DEFAULT_BLOCK_COLOR;
		
		this.mContext.setFill(color);
		this.mContext.fillRect(x, y, w, h);
		
		
		if (previousColor != null) // does not want to revert back to color
			this.mContext.setFill(previousColor);
		
	}
	
	/**
	 * called when a modification to the list has occurred.
	 * Depending on the values of the parameters interpretation can be made about what was modified.
	 * Data moved, replaced , add, ect.
	 * @param oldObj
	 * @param newObj
	 * @param oldIndex
	 * @param newIndex;
	 */
	@Override
	public void onDataChanged(Object oldObj, Object newObj, int oldIndex,
			int newIndex)
	{
		if (oldObj == null && newObj != null) // new data added
		{
			if (oldIndex + 1 == newIndex) // data added
			{
				// add implementation
			}
			
		}
		else if (oldObj != null && newObj == null) // data removed
		{
			
		}
		
		//TODO : Finish method
		
	}

	@Override
	public void onDataAdded(Object newObj, int index, boolean sizeIncreased)
	{
		//TODO : Finish method
		
	}

	@Override
	public void onDataRemoved(Object oldObj, int index, boolean sizeDecreased)
	{
		
		//TODO : Finish method
	}

	@Override
	public void onDataMoved(Object obj, int oldIndex, int newIndex)
	{
		
		//TODO : Finish method
	}

}
