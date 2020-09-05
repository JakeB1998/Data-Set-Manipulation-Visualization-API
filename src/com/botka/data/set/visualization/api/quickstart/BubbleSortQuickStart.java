/*
 * File name:  BubbleSortQuickStart.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 5, 2020
 *
 */
package com.botka.data.set.visualization.api.quickstart;

import com.botka.data.set.visualization.api.ExecuteInMainThreadManager;
import com.botka.data.set.visualization.api.IRunOnMainThread;
import com.botka.data.set.visualization.api.data.DataSet;
import com.botka.data.set.visualization.api.render.engine.RenderEngine;
import com.botka.data.set.visualization.api.sort.BubbleSort;
import com.botka.data.set.visualization.api.sort.IFinishedListener;
import com.botka.data.set.visualization.api.sort.SelectionSort;
import com.botka.data.set.visualization.api.sort.Sort;
import com.botka.data.set.visualization.api.sound.engine.AudioEngine;
import com.botka.data.set.visualization.api.step.StepOperation;
import com.botka.data.set.visualization.api.visualizer.JavaFXVisualizer;
import com.botka.data.set.visualization.api.visualizer.Visualizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

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
