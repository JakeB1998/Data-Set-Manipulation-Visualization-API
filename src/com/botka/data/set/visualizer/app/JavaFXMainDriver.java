/*
 * File name:  TestDriver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 * Out Of Class Personal Program
 */
package com.botka.data.set.visualizer.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.botka.data.set.visualizer.ExecuteInMainThreadManager;
import com.botka.data.set.visualizer.IRunOnMainThread;
import com.botka.data.set.visualizer.data.DataSet;
import com.botka.data.set.visualizer.readers.FileReader;
import com.botka.data.set.visualizer.render.engine.RenderEngine;
import com.botka.data.set.visualizer.sort.ArraySorter;
import com.botka.data.set.visualizer.sort.BubbleSort;
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
 * Test driver with main method for the Javafx test implementation
 *
 * @author Jake Botka
 *
 */
public class JavaFXMainDriver extends Application implements IRunOnMainThread
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
		for (int i = 0; i < 100; i++) // generates random data
		{
			dataSet.add(ran.nextDouble() * 100);
		}
		//insert data here end
	
		Visualizer visual = new JavaFXVisualizer(dataSet, stage, scene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet);
		RenderEngine engine = new RenderEngine(visual,stepOp, 200);
		
		MANAGER.setMainThreadCallback(this);
		visual.init();
		engine.init();
		
		stage.setScene(scene);
		stage.show();
		
		
		
		//objectSortTest();
	}
	
	/**
	 * Option 1 is driven by randoimly generated data
	 * @param stage
	 */
	public void option1(Stage stage,  Scene scene, Canvas canvas)
	{
		
		DataSet<Double> dataSet = new DataSet(0);
		
		// inser data here start
		Random ran = new Random();
		for (int i = 0; i < 100; i++) // generates random data
		{
			dataSet.add(ran.nextDouble() * 100);
		}
		//insert data here end
	
		Visualizer visual = new JavaFXVisualizer(dataSet, stage, scene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet);
		RenderEngine engine = new RenderEngine(visual,stepOp, 120);
		
		MANAGER.setMainThreadCallback(this);
		visual.init();
		engine.init();
		
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * option 2 is ran with data that was extracted from a file
	 * @param stage
	 * @param scene
	 * @param canvas
	 * @param file
	 */
	public void option2(Stage stage, Scene scene, Canvas canvas, File file)
	{
		
		
		DataSet<Double> dataSet = readDataFromFile(file);
		Visualizer visual = new JavaFXVisualizer(dataSet, stage, scene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet);
		RenderEngine engine = new RenderEngine(visual,stepOp, 45);
		
		MANAGER.setMainThreadCallback(this);
		visual.init();
		engine.init();
		
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * reads data from file
	 * @param file
	 * @return
	 */
	public static DataSet<Double> readDataFromFile(File file)
	{
		try
		{
			FileReader reader = new FileReader(file);
			DataSet<Double> dataset = null;
			double[] arr = reader.readAllDoubles();
			dataset = new DataSet<Double>(0);
			if (arr != null)
			{
				for (double d : arr)
				{
					dataset.add(new Double(d));
				}
			}
			
			return dataset;
		} catch (FileNotFoundException e)
		{
			
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    // Save file
	    System.exit(0);
	}
	
	public static void objectSortTest()
	{
		Integer[] arr = new Integer[2];
		arr[0] = 3;
		arr[1] = -2;
		Integer max = ArraySorter.<Integer>findMaxinArray(arr);
		Double d = new Double(3.5);
		System.out.println(max.intValue());
		System.out.println(d.toString());
	}

	@Override
	public void runOnMainThread(Runnable run)
	{
		Platform.runLater(run); //javafx implementation of communicating to main thread
		
	}

}
