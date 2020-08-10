/*
 * File name:  MainController.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package com.botka.data.set.visualization.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import com.botka.data.set.visualization.api.IRunOnMainThread;
import com.botka.data.set.visualization.api.data.DataSet;
import com.botka.data.set.visualization.api.data.DataPeekListener;
import com.botka.data.set.visualization.api.loggers.ConsoleLogger;
import com.botka.data.set.visualization.api.readers.FileReader;
import com.botka.data.set.visualization.api.render.engine.RenderEngine;
import com.botka.data.set.visualization.api.sort.BubbleSort;
import com.botka.data.set.visualization.api.sort.IFinishedListener;
import com.botka.data.set.visualization.api.step.StepOperation;
import com.botka.data.set.visualization.api.util.ArrayUtil;
import com.botka.data.set.visualization.api.util.IDGenerator;
import com.botka.data.set.visualization.api.visualizer.JavaFXVisualizer;
import com.botka.data.set.visualization.api.visualizer.Visualizer;
import com.botka.data.set.visualization.app.javafx.components.FileChooserComponent;
import com.botka.data.set.visualization.app.javafx.handlers.StageScaling;
import com.botka.data.set.visualization.app.javafx.handlers.button.ExitButtonHandler;
import com.botka.data.set.visualization.app.javafx.handlers.menu.items.AboutMenuItem;
import com.botka.data.set.visualization.app.javafx.handlers.menu.items.LoadDataMenuItem;
import com.botka.data.set.visualization.app.javafx.handlers.menu.items.NodeControls;
import com.botka.data.set.visualization.app.javafx.handlers.menu.items.StartMenuItem;
import com.botka.data.set.visualization.app.javafx.handlers.menu.items.StopMenuItem;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class MainController implements IRunOnMainThread, IFinishedListener, DataPeekListener<Number>, ControllerTransferCallback
{

	

	private final FileChooserComponent FILE_SYSTEM_INTERFACE = new FileChooserComponent();
	private Stage mStage;
	private Scene mScene;
	private ArrayList<NodeControls> mNodeContolItems;
	private Visualizer mVisualizer;
	
	
	
	@FXML VBox mainNode;
	@FXML VBox mainDetailsView;
	@FXML ScrollPane scrollPane;
	@FXML SplitPane paneDivider;
	@FXML Canvas canvas;
	
	@FXML MenuItem exitBtn;
	@FXML MenuItem aboutMenuItem;
	@FXML MenuItem startMenuItem;
	@FXML MenuItem stopMenuItem;
	@FXML MenuItem pauseMenuItem;
	@FXML MenuItem resumeMenuItem;
	@FXML MenuItem fileLoadMenuItem;
	
	
	

	@FXML
	public void initialize() throws InterruptedException 
	{
		ConsoleLogger.Logger.log(getClass(), "FXML documenet has initialized", true);
		FILE_SYSTEM_INTERFACE.addFileFilter(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		
		exitBtn.setOnAction((EventHandler<ActionEvent>)new ExitButtonHandler());
		this.mNodeContolItems = new ArrayList(0);
		resumeMenuItem.setDisable(true);
	
		//collapse
		scrollPane.setVisible(false);
		scrollPane.setManaged(false);
		//paneDivider.setDividerPosition(0, 1.0);
		
		
		
	}
	
	/**
	 * Inits after the setParams method is called
	 */
	private void init()
	{
		final StageScaling stageScaling = new StageScaling();
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
		RenderEngine engine = new RenderEngine(mVisualizer,stepOp, 10);
		this.mVisualizer.registerOnDataPeekCallback(this);
		AppDriver.MANAGER.setMainThreadCallback(this);
		
		AboutMenuItem aboutMenuItem = new AboutMenuItem(this.mStage,(ControllerTransferCallback)this, this.aboutMenuItem, null);
		StartMenuItem startMenuItem = new StartMenuItem(this.mStage,(ControllerTransferCallback)this,this.startMenuItem, null, engine);
		StopMenuItem stopMenuItem = new StopMenuItem(this.mStage,(ControllerTransferCallback)this,this.stopMenuItem, null, engine);
		LoadDataMenuItem loadFileMenuItem = new LoadDataMenuItem(this.mStage,(ControllerTransferCallback)this,this.fileLoadMenuItem, null, FILE_SYSTEM_INTERFACE);
		
		this.mNodeContolItems.add(startMenuItem);
		this.mNodeContolItems.add(stopMenuItem);
		
		mVisualizer.init();
		engine.init();
		this.mStage.heightProperty().addListener(stageScaling.getHeightScaling());
		this.mScene.widthProperty().addListener(stageScaling.getWidthScaling());
		
		
		/*
		File file = FILE_SYSTEM_INTERFACE.accessFiles(this.mStage);
		String fileS = "File: ";
		fileS = file != null ? fileS + file.getAbsolutePath() : fileS + "NULL";
		ConsoleLogger.Logger.log(getClass(), fileS, true);
		*/
		
		
		
	}
	
	/**
	 * ObservableValue
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

	/**
	 * 
	 */
	@Override
	public void onFinished()
	{
		if (mVisualizer != null)
		{
			mVisualizer.onFinished();
		}
		
	}

	/**
	 * 
	 */
	@Override
	public void onPeak(int index,Number data)
	{
		String value = Double.toString(data.doubleValue());
		ConsoleLogger.Logger.log(getClass(), "Data peeked: " + value, true);
		mainDetailsView.getChildren().add(new Label("Index: " + String.valueOf(index) + " Value: " + value));
		scrollPane.setVvalue(scrollPane.getVmax());
		
	}

	@Override
	public void onTransfer(Object data, Class type)
	{
		if (data != null && type != null)
		{
			
			System.out.println(type.toString());
			if (type.equals(File.class))
			{
				File transferFile = null;
				transferFile = (File)data;
				ConsoleLogger.Logger.log(getClass(), "File transfered path: " + transferFile.getAbsolutePath(), true);
				this.insertNewData(transferFile);
			}
		}
			
		
	}
	
	/**
	 * 
	 * @param file
	 */
	public void insertNewData(File file)
	{
		DataSet set = null;
		Comparable[] arr = null;
		try
		{
			FileReader fileReader = new FileReader(file); //my API not java.io
			double[] dataArr = fileReader.readAllDoubles();
			arr = (Comparable[])ArrayUtil.converToWrapperArray(dataArr);
		
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (arr != null)
			set = new DataSet<>(arr);
		if (set != null)
		{
			this.mVisualizer.insertData(set);
			System.out.println("Inserted data");
			System.out.println(this.mVisualizer.isInitialized() + "," + this.mVisualizer.isRunning());
			if (this.mVisualizer.isInitialized()&& this.mVisualizer.isRunning() == false)
				this.mVisualizer.onRender();
		}
	}

}
