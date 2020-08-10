/*
 * File name:  AboutMenuItem.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 4, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.handlers.menu.items;

import java.util.Properties;

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
public class AboutMenuItem extends MenuItemControl
{

	private MenuItem mMenuItem;
	private FireEventListener mFireEventCallback;
	private RenderEngine mRenderEngine;

	/**
	 * 
	 */
	public AboutMenuItem(Stage stage,ControllerTransferCallback controllerTransferCallback, MenuItem menuItem, FireEventListener callback)
	{
		super(stage, controllerTransferCallback, menuItem,callback);
		
	}

	@Override
	public void handle(ActionEvent arg0)
	{
		super.handle(arg0);
		final String author = "Jake Michael Botka";
		Properties systemProp = System.getProperties();
		if (systemProp != null)
		{
			String[] arr = new String[systemProp.size()];
		}
		//TODO Finish Method
	}
	
	
}
