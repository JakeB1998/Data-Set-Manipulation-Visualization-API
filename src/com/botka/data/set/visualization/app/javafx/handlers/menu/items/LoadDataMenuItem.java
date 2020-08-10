/*
 * File name:  LoadDataMenuItem.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 10, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.handlers.menu.items;

import java.io.File;

import com.botka.data.set.visualization.app.ControllerTransferCallback;
import com.botka.data.set.visualization.app.javafx.components.FileChooserComponent;
import com.botka.data.set.visualization.app.javafx.handlers.FireEventListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class LoadDataMenuItem extends MenuItemControl 
{
	private FileChooserComponent mFileChooserComponent;

	public LoadDataMenuItem(Stage stage, ControllerTransferCallback controllerTransferCallback, MenuItem menuItem, FireEventListener callback, FileChooserComponent fileChooserComponent)
	{
		super(stage, controllerTransferCallback, menuItem,callback);
		this.mFileChooserComponent= fileChooserComponent;
		
	}
	
	@Override
	public void handle(ActionEvent arg0)
	{
		super.handle(arg0);
		if (this.mFileChooserComponent != null)
		{
			File file = null;
			file = this.mFileChooserComponent.accessFiles(mStage);
			if (file != null)
				mControllerTransferCallback.onTransfer(file, file.getClass());
		}
	}

	

}
