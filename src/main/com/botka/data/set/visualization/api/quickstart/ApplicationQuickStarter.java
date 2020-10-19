/*
 * File name:  ApplicationQuickStarter.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 5, 2020
 *
 */
package main.com.botka.data.set.visualization.api.quickstart;

import java.util.Random;

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
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class ApplicationQuickStarter {

	private DataSet<Comparable> mDataset;
	private StepOperation mDataOperation;
	
	
	/**
	 * 
	 */
	public ApplicationQuickStarter(DataSet<Comparable> dataset, StepOperation stepOp) {
		this.mDataset = dataset;
		this.mDataOperation = stepOp;
	}
	

	/**
	 * Starts the application in the javafx framework. If javafx is not available on the system than an exception will be thrown.
	 * @param args Application Command line arguments. Usually passed from the main method
	 * @throws ApplicationArgsNotProvided Thrown if not provided with the
	 *                                    application arguments
	 */
	public void startApplication(String[] args) throws ApplicationArgsNotProvided {
		if (args != null) {
			if (this.mDataset != null) {
				new ApplicationWrapper(this.mDataset,this.mDataOperation).execute(args);;
			}
			else {
				new ApplicationWrapper(randomizedData(), this.mDataOperation).execute(args);;
			}
		} else {
			throw new ApplicationArgsNotProvided();
		}
	}
	
	/**
	 * Starts the application in the javafx framework. If javafx is not available on the system than an exception will be thrown.
	 * @param args Application Command line argumenets. Ussualy passed from the main method
	 * @param dataset The dataset to be included into the visualization
	 * @throws ApplicationArgsNotProvided Thrown if not provided with the
	 *                                    application arguments
	 */
	public void startApplication(String[] args, DataSet<Comparable> dataset) throws ApplicationArgsNotProvided {
		this.mDataOperation.setDataSet(dataset);
		this.mDataset = dataset;
		this.startApplication(args);
	}

	private static DataSet<Comparable> randomizedData() {
		DataSet<Comparable> set = new DataSet(0);
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
	 * 
	 * Wrapper class for application
	 *
	 * @author Jake Botka
	 *
	 */
	public static class ApplicationWrapper extends Application
			implements IRunOnMainThread, IFinishedListener {

		private static DataSet<Comparable> mDataset;
		private static StepOperation mDataOperation;
		private static final ExecuteInMainThreadManager MANAGER = ExecuteInMainThreadManager
				.getInstance();
		private static Visualizer visualizer = null;
		public static final AudioEngine AUDIO_ENGINE = new AudioEngine();
		private boolean mDoNotRun;

		/**
		 * Cannot be called but is required for super Application
		 */
		public ApplicationWrapper() {
			this.mDoNotRun = true;
		}
		/**
		 * 
		 * @param dataset
		 * @param stepOp
		 */
		public ApplicationWrapper(DataSet<Comparable> dataset, StepOperation stepOp) {
			mDataset = dataset;
			mDataOperation = stepOp;
			if (mDataOperation != null) {
				mDataOperation.registerOnFinishedListener(this);
			}
		}

		@Override
		public void start(Stage stage) throws Exception {

			try {
				System.out.println(mDataOperation.toString());
				Scene scene = null;
				Canvas canvas = new Canvas(1000, 1000);
				Group root = new Group();
				root.getChildren().add(canvas);
				scene = new Scene(root);

				visualizer = new JavaFXVisualizer(mDataset, stage, scene, canvas);

				StepOperation stepOp = mDataOperation;
				RenderEngine engine = new RenderEngine(visualizer, stepOp, 200);
				if (visualizer != null) {
					visualizer.setPrefixTitle(stepOp.getCondensedOperationDetails());
				}

				MANAGER.setMainThreadCallback(this);
				visualizer.init();
				engine.init();

				engine.startRenderer();

				stage.setScene(scene);
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		public void execute(String[] args) {
			if (!this.mDoNotRun) {
				Application.launch(args);
			}
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

}
