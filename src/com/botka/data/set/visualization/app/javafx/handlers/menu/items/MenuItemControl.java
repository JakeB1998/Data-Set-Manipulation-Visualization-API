/*
 * File name:  MenuItem.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 10, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.handlers.menu.items;

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
public class MenuItemControl extends NodeControls implements EventHandler<ActionEvent>
{

	private MenuItem mMenuItem;
	private FireEventListener mFireEventCallback;
	/**
	 * @param controllerTransferCallback 
	 * 
	 */
	public MenuItemControl(Stage stage, ControllerTransferCallback controllerTransferCallback, MenuItem menuItem, FireEventListener callback)
	{
		super(stage, controllerTransferCallback);
		this.mMenuItem = menuItem;
		this.mFireEventCallback = callback;
		if (this.mMenuItem != null)
			this.mMenuItem.setOnAction(this);
	}
	
	/**
	 * Shows ui
	 * @return
	 */
	public boolean show()
	{
		if (this.mMenuItem != null)
		{
			this.mMenuItem.setVisible(true);
			return true;
		}
		return false;
	}
	
	/**
	 * hides ui
	 * @return
	 */
	public boolean hide()
	{
		if (this.mMenuItem != null)
		{
			this.mMenuItem.setVisible(false);
			return true;
		}
		return false;
	}

	@Override
	public String getID()
	{
		if (this.mMenuItem != null)
			return this.mMenuItem.getId();
		return null;
	}
	
	/**
	 * Overriden by sub
	 * But then called by subclass. 
	 * This allows the subclass to hand MORE implementation
	 */
	@Override
	public void handle(ActionEvent event)
	{
		if (this.mFireEventCallback!= null)
		{
			if (this.mMenuItem != null)
			{
				this.mFireEventCallback.onEventFire(this.mMenuItem.getId());
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public MenuItem getMenuItem()
	{
		return this.mMenuItem;
	}
	
	/**
	 * 
	 * @return
	 */
	public FireEventListener getFileEventListener()
	{
		return this.mFireEventCallback;
	}
	

}
