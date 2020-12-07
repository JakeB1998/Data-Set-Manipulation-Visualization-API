/*
 * File name:  TestDriver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 * Out Of Class Personal Program
 */
package main.com.botka.data.set.visualization.api.implementation.javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import main.com.botka.data.set.visualization.api.ExecuteInMainThreadManager;
import main.com.botka.data.set.visualization.api.IRunOnMainThread;
import main.com.botka.data.set.visualization.api.data.DataSet;

import main.com.botka.data.set.visualization.api.readers.FileReader;
import main.com.botka.data.set.visualization.api.render.engine.RenderEngine;
import main.com.botka.data.set.visualization.api.sort.ArraySorter;
import main.com.botka.data.set.visualization.api.sort.BubbleSort;
import main.com.botka.data.set.visualization.api.sort.IFinishedListener;
import main.com.botka.data.set.visualization.api.sort.SelectionSort;
import main.com.botka.data.set.visualization.api.sort.Sort;
import main.com.botka.data.set.visualization.api.sound.engine.Audio;
import main.com.botka.data.set.visualization.api.sound.engine.AudioEngine;
import main.com.botka.data.set.visualization.api.sound.engine.AudioFile;
import main.com.botka.data.set.visualization.api.sound.engine.IAudioListener;
import main.com.botka.data.set.visualization.api.sound.engine.IPlayAudio;
import main.com.botka.data.set.visualization.api.step.StepOperation;
import main.com.botka.data.set.visualization.api.visualizer.JavaFXVisualizer;
import main.com.botka.data.set.visualization.api.visualizer.Visualizer;

/**
 * Test driver with main method for the Javafx test implementation
 *
 * @author Jake Botka
 *
 */
public class JavaFXMainDriver extends Application implements IRunOnMainThread, IFinishedListener {

	private static final ExecuteInMainThreadManager MANAGER = ExecuteInMainThreadManager
			.getInstance();
	private static Visualizer visualizer = null;
	public static final AudioEngine AUDIO_ENGINE = new AudioEngine();
	private static DataSet<Comparable> mDataset = null;

	/**
	 * @param command line arguments
	 */
	public static void main(String[] args) {
		if (args.length < 1)
			mDataset = randomizedData();
		else {
			// read args and determine course of action
		}

		Application.launch(args); // calls the start method below

	}

	/**
	 * Starting point for JavaFX Application
	 * 
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Scene scene = null;
			Canvas canvas = new Canvas(1000, 1000);
			Group root = new Group();
			root.getChildren().add(canvas);
			scene = new Scene(root);

			visualizer = new JavaFXVisualizer(mDataset, stage, scene, canvas);

			StepOperation stepOp = null;
			stepOp = new BubbleSort(mDataset, this); // this being the IFInishedListener
														// Implementation
			stepOp = new SelectionSort(mDataset, this); // this being the IFInishedListener
														// Implementation
			RenderEngine engine = new RenderEngine(visualizer, stepOp, 200);
			Sort sort = (Sort) stepOp;

			visualizer.setPrefixTitle(sort.getAlgorithm());

			MANAGER.setMainThreadCallback(this);
			visualizer.init();
			engine.init();

			engine.startRenderer();

			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// objectSortTest();
	}

	public static DataSet<Comparable> randomizedData() {
		DataSet<Comparable> set = new DataSet();
		// insert data here start
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			Object value = ran.nextDouble() * 100;
			set.add((Comparable) value);
		}

		return set;
		// insert data here end
	}

	/**
	 * Option 1 is driven by randoimly generated data
	 * 
	 * @param stage
	 */
	public void option1(Stage stage, Scene scene, Canvas canvas) {

		DataSet<Double> dataSet = new DataSet();

		// inser data here start
		Random ran = new Random();
		for (int i = 0; i < 100; i++) // generates random data
		{
			dataSet.add(ran.nextDouble() * 100);
		}
		// insert data here end

		Visualizer visual = new JavaFXVisualizer(dataSet, stage, scene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet, this); // this being the IFInishedListener
																// Implementation
		RenderEngine engine = new RenderEngine(visual, stepOp, 120);

		MANAGER.setMainThreadCallback(this);
		visual.init();
		engine.init();

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * option 2 is ran with data that was extracted from a file
	 * 
	 * @param stage
	 * @param scene
	 * @param canvas
	 * @param file
	 */
	public void option2(Stage stage, Scene scene, Canvas canvas, File file) {

		DataSet<Double> dataSet = readDataFromFile(file);
		Visualizer visual = new JavaFXVisualizer(dataSet, stage, scene, canvas);
		StepOperation stepOp = new BubbleSort(dataSet, this); // this being the IFInishedListener
																// Implementation
		RenderEngine engine = new RenderEngine(visual, stepOp, 45);

		MANAGER.setMainThreadCallback(this);
		visual.init();
		engine.init();

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * reads data from file
	 * 
	 * @param file
	 * @return
	 */
	public static DataSet<Double> readDataFromFile(File file) {
		try {
			FileReader reader = new FileReader(file);
			DataSet<Double> dataset = null;
			double[] arr = reader.readAllDoubles();
			dataset = new DataSet<Double>();
			if (arr != null) {
				for (double d : arr) {
					dataset.add(new Double(d));
				}
			}

			return dataset;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return null;

	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	@Override
	public void stop() {
		System.out.println("Stage is closing");
		// Save file
		System.exit(0);
	}

	public static void objectSortTest() {
		Integer[] arr = new Integer[2];
		arr[0] = 3;
		arr[1] = -2;
		Integer max = ArraySorter.<Integer>findMaxinArray(arr);
		Double d = new Double(3.5);
		System.out.println(max.intValue());
		System.out.println(d.toString());
	}

	@Override
	public void runOnMainThread(Runnable run) {
		Platform.runLater(run); // javafx implementation of communicating to main thread

	}

	@Override
	public void onFinished() {
		if (visualizer != null) {
			visualizer.onFinished();
		}

	}

}
