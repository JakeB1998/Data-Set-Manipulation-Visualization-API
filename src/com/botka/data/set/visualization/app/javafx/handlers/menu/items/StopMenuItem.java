/*
 * File name:  StopMenuItem.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 4, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.handlers.menu.items;

import com.botka.data.set.visualization.api.render.engine.RenderEngine;
import com.botka.data.set.visualization.app.ControllerTransferCallback;
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
public class StopMenuItem extends MenuItemControl
{

	
	private RenderEngine mRenderEngine;

	/**
	 * @param controllerTransferCallback 
	 * 
	 */
	public StopMenuItem(Stage stage, ControllerTransferCallback controllerTransferCallback, MenuItem menuItem, FireEventListener callback, RenderEngine renderEngine)
	{
		super(stage,controllerTransferCallback, menuItem, callback);
		this.mRenderEngine = renderEngine;
	}

	@Override
	public void handle(ActionEvent arg0)
	{
		super.handle(arg0);
		if (this.mRenderEngine != null)
			this.mRenderEngine.haltRenderer();
	}
	
	
}
