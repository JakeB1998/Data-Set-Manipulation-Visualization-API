/*
 * File name:  JavaFXVisualizer.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer.visualizer;

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
	private double mGroundX, mGroundY, mScaleXFactor, mScaleYFactor;
	private boolean mReady;
	

	/**
	 * 
	 * @param stage
	 */
	public JavaFXVisualizer(Stage stage)
	{
		this.mStage = stage;
		
	}
	
	/**
	 * 
	 * @param stage
	 * @param scene
	 */
	public JavaFXVisualizer(Stage stage, Scene scene)
	{
		this(stage);
		this.mScene = scene;
		this.mStage.setScene(this.mScene);
	}
	

	/**
	 * 
	 * @param stage
	 * @param scene
	 * @param canvas
	 */
	public JavaFXVisualizer(Stage stage, Scene scene, Canvas canvas)
	{
		this(stage,scene);
		this.mCanvas = canvas;
		// if canvas null then assign context null otherwise call canvas getContext method;
		this.mContext = this.mCanvas != null ? this.mCanvas.getGraphicsContext2D() : null;
		this.mGroundX = this.mCanvas != null ? 0 : -1;
		this.mGroundY = this.mCanvas != null ? (int) this.mCanvas.getHeight() : -1;
	
		
	}
	
	/**
	 * 
	 */
	@Override
	public void init()
	{
		this.mReady = this.checkIfReady();
	
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
			System.out.println(this.mCanvas.getHeight());
			this.mContext.setFill(Color.BLACK);
			this.mContext.fillRect(this.mGroundX,this.mGroundY - 100, 100, 100);
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
