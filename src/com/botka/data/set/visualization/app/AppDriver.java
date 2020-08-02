/*
 * File name:  Driver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 22, 2020
 *
 */
package com.botka.data.set.visualization.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Random;

import com.botka.data.set.visualization.api.ExecuteInMainThreadManager;
import com.botka.data.set.visualization.api.IRunOnMainThread;
import com.botka.data.set.visualization.api.data.DataSet;
import com.botka.data.set.visualization.api.readers.FileReader;
import com.botka.data.set.visualization.api.render.engine.RenderEngine;
import com.botka.data.set.visualization.api.sort.ArraySorter;
import com.botka.data.set.visualization.api.sort.BubbleSort;
import com.botka.data.set.visualization.api.sort.IFinishedListener;
import com.botka.data.set.visualization.api.sound.engine.AudioEngine;
import com.botka.data.set.visualization.api.step.StepOperation;
import com.botka.data.set.visualization.api.visualizer.JavaFXVisualizer;
import com.botka.data.set.visualization.api.visualizer.Visualizer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
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

	public  static final ExecuteInMainThreadManager MANAGER = ExecuteInMainThreadManager.getInstance();
	private static Visualizer visualizer = null;
	public static final AudioEngine AUDIO_ENGINE = new AudioEngine();
	public static final ApplicationSettings APP_SETTINGS = new ApplicationSettings(null);
	
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
		try
		{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("app.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		MainController controller = fxmlLoader.<MainController>getController();
		
		Scene scene = null;
		scene = new Scene(root);
		controller.setParams(stage, scene);
	
		
		stage.setScene(scene);
		stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
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
