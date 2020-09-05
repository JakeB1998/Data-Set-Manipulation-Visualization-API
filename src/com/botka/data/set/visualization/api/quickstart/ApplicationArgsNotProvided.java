/*
 * File name:  ApplicationArgsNotProvided.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 5, 2020
 *
 */
package com.botka.data.set.visualization.api.quickstart;

/**
 * Thrown if the application command line args is not provided toa  system that requires them
 *
 * @author Jake Botka
 *
 */
public class ApplicationArgsNotProvided extends Exception {

	private final static  String DEFAULT_ERROR_MESSAGE = "Application Args from the parameters inside Main method was not provided";
	/**
	 * 
	 */
	public ApplicationArgsNotProvided() {
		super(DEFAULT_ERROR_MESSAGE);
	}

	/**
	 * @param message
	 */
	public ApplicationArgsNotProvided(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ApplicationArgsNotProvided(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationArgsNotProvided(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApplicationArgsNotProvided(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
