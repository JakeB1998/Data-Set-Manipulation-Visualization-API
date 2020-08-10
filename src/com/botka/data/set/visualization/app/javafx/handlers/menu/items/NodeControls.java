/*
 * File name:  MenuItem.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 4, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.handlers.menu.items;

import com.botka.data.set.visualization.app.Controller;
import com.botka.data.set.visualization.app.ControllerTransferCallback;

import javafx.stage.Stage;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class NodeControls
{

	protected Stage mStage;
	protected ControllerTransferCallback mControllerTransferCallback;
	public NodeControls(Stage stage, ControllerTransferCallback callback)
	{
		this.mStage = stage;
		this.mControllerTransferCallback = callback;
	}
	
	public abstract boolean show();
	public abstract boolean hide();
	public abstract String getID();
	
	

}
