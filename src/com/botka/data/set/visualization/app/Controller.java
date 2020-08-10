/*
 * File name:  Controller.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 10, 2020
 *
 */
package com.botka.data.set.visualization.app;

/**
 * Base class for all controllers
 *
 * @author Jake Botka
 *
 */
public abstract class Controller implements ControllerTransferCallback
{
	protected final static  int CONTROLLER_ID_LENGTH = 6;
	private final int ID;

	/**
	 * 
	 */
	public Controller(int id)
	{
		ID = id;
		// TODO Auto-generated constructor stub
	}

}
