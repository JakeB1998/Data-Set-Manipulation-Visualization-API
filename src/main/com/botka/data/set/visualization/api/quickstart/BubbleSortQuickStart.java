/*
 * File name:  BubbleSortQuickStart.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 5, 2020
 *
 */
package main.com.botka.data.set.visualization.api.quickstart;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import main.com.botka.data.set.visualization.api.ExecuteInMainThreadManager;
import main.com.botka.data.set.visualization.api.IRunOnMainThread;
import main.com.botka.data.set.visualization.api.data.DataSet;
import main.com.botka.data.set.visualization.api.render.engine.RenderEngine;
import main.com.botka.data.set.visualization.api.sort.BubbleSort;
import main.com.botka.data.set.visualization.api.sort.IFinishedListener;
import main.com.botka.data.set.visualization.api.sort.SelectionSort;
import main.com.botka.data.set.visualization.api.sort.Sort;
import main.com.botka.data.set.visualization.api.sound.engine.AudioEngine;
import main.com.botka.data.set.visualization.api.step.StepOperation;
import main.com.botka.data.set.visualization.api.visualizer.JavaFXVisualizer;
import main.com.botka.data.set.visualization.api.visualizer.Visualizer;

/**
 * Quickstart that a programmer can call to quickstart a base visualization
 * using Javafx.
 *
 * @author Jake Botka
 *
 */
public class BubbleSortQuickStart extends ApplicationQuickStarter  {

	
	
	
	/**
	 * 
	 */
	public BubbleSortQuickStart(DataSet<? extends Comparable> dataset) {
		super((DataSet<Comparable>) dataset, (StepOperation)new BubbleSort(dataset));
	}

	


}
