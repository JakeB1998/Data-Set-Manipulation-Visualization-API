/*
 * File name:  Driver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 22, 2020
 *
 */
package com.botka.data.set.visualizer.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import com.botka.data.set.visualizer.ExecuteInMainThreadManager;
import com.botka.data.set.visualizer.IRunOnMainThread;
import com.botka.data.set.visualizer.data.DataSet;
import com.botka.data.set.visualizer.readers.FileReader;
import com.botka.data.set.visualizer.render.engine.RenderEngine;
import com.botka.data.set.visualizer.sort.ArraySorter;
import com.botka.data.set.visualizer.sort.BubbleSort;
import com.botka.data.set.visualizer.sort.IFinishedListener;
import com.botka.data.set.visualizer.step.StepOperation;
import com.botka.data.set.visualizer.visualizer.JavaFXVisualizer;
import com.botka.data.set.visualizer.visualizer.Visualizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * This is the drive for the main tool with full UI.
 * This will use the JavaFX framework
 *
 * @author Jake Botka
 *
 */
public class AppDriver extends Application implements IRunOnMainThread, IFinishedListener
{

	private  static final ExecuteInMainThreadManager MANAGER = ExecuteInMainThreadManager.getInstance();
	/**
	 * @param command line arguments
	 */
	public static void main(String[] args)
	{
		Application.launch(args); // calls the start method below

	}

	/**
	 * Starting point for JavaFX Application
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = null;
		Canvas canvas = new Canvas(1000,1000);
		Group root = new Group();
		root.getChildren().add(canvas);
		scene = new Scene(root);
		
		DataSet<Double> dataSet = new DataSet(0);
		
		// insert data here start
		Random ran = new Random();
		for (int i = 0; i < 1000; i++) // generates random data
		{
			dataSet.add(ran.nextDouble() * 100);
		}
		//insert data here end
	
		Visualizer visual = new JavaFXVisualizer(dataSet, stage, scene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet, this);
		RenderEngine engine = new RenderEngine(visual,stepOp, 45);
		
		MANAGER.setMainThreadCallback(this);
		visual.init();
		engine.init();
		
		stage.setScene(scene);
		stage.show();
		
		
		
		//objectSortTest();
	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    // Save file
	    System.exit(0);
	}
	
	

	@Override
	public void runOnMainThread(Runnable run)
	{
		Platform.runLater(run); //javafx implementation of communicating to main thread
		
	}

	@Override
	public void onFinished()
	{
		// TODO Auto-generated method stub
		
	}

}
