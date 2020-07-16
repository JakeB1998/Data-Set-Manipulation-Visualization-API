/*
 * File name:  TestDriver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 * Out Of Class Personal Program
 */
package com.botka.data.set.visualizer;

import java.util.ArrayList;
import java.util.Arrays;

import com.botka.data.set.visualizer.render.engine.RenderEngine;
import com.botka.data.set.visualizer.visualizer.JavaFXVisualizer;
import com.botka.data.set.visualizer.visualizer.Visualizer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class JavaFXTestDriver extends Application
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = null;
		Canvas canvas = new Canvas(1000,1000);
		Group root = new Group();
		root.getChildren().add(canvas);
		scene = new Scene(root);
		
		Visualizer visual = new JavaFXVisualizer(stage, scene, canvas );
		
		RenderEngine engine = new RenderEngine(visual, 30);
		
		visual.init();
		engine.init();
		stage.setScene(scene);
		stage.show();
		
		objectSortTest();
	}
	
	public static void objectSortTest()
	{
		Integer[] arr = new Integer[2];
		arr[0] = 3;
		arr[1] = -2;
		Integer max = ArraySorter.<Integer>findMaxinArray(arr);
		System.out.println(max.intValue());
	}

}
