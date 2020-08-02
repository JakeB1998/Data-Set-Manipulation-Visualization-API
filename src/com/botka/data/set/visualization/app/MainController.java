/*
 * File name:  MainController.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package com.botka.data.set.visualization.app;

import java.util.Random;

import com.botka.data.set.visualization.api.IRunOnMainThread;
import com.botka.data.set.visualization.api.data.DataSet;
import com.botka.data.set.visualization.api.data.IDataPeekListener;
import com.botka.data.set.visualization.api.loggers.ConsoleLogger;
import com.botka.data.set.visualization.api.render.engine.RenderEngine;
import com.botka.data.set.visualization.api.sort.BubbleSort;
import com.botka.data.set.visualization.api.sort.IFinishedListener;
import com.botka.data.set.visualization.api.step.StepOperation;
import com.botka.data.set.visualization.api.visualizer.JavaFXVisualizer;
import com.botka.data.set.visualization.api.visualizer.Visualizer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class MainController implements IRunOnMainThread, IFinishedListener, IDataPeekListener<Number>
{

	private Stage mStage;
	private Scene mScene;
	private Visualizer mVisualizer;
	
	@FXML
	VBox mainNode;
	@FXML
	VBox mainDetailsView;
	@FXML
	ScrollPane scrollPane;
	@FXML
	Canvas canvas;

	@FXML
	public void initialize() throws InterruptedException 
	{
	
	}
	
	/**
	 * Inits after the setParams method is called
	 */
	private void init()
	{
		DataSet<Double> dataSet = new DataSet(0);
		
		// insert data here start
		Random ran = new Random();
		for (int i = 0; i < 10; i++) // generates random data
		{
			dataSet.add(ran.nextDouble() * 100);
		}
		//insert data here end
	
		 mVisualizer = new JavaFXVisualizer(dataSet, this.mStage, this.mScene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet, this);
		RenderEngine engine = new RenderEngine(mVisualizer,stepOp, 45);
		this.mVisualizer.registerOnDataPeekCallback(this);
		
		AppDriver.MANAGER.setMainThreadCallback(this);
		mVisualizer.init();
		engine.init();
		
	}
	
	/**
	 * 
	 * @param stage
	 * @param scene
	 */
	public void setParams(Stage stage, Scene scene)
	{
		ConsoleLogger.Logger.log(getClass(), "ParamsSet", true);
		this.mStage = stage;
		this.mScene = scene;
		this.init();
		
	}

	@Override
	public void runOnMainThread(Runnable run)
	{
		Platform.runLater(run); //javafx implementation of communicating to main thread
		
	}

	@Override
	public void onFinished()
	{
		if (mVisualizer != null)
		{
			mVisualizer.onFinished();
		}
		
	}

	@Override
	public void onPeak(Number data)
	{
		String value = Double.toString(data.doubleValue());
		ConsoleLogger.Logger.log(getClass(), "Data peeked: " + value, true);
		mainDetailsView.getChildren().add(new Label(value));
		
	}

}
