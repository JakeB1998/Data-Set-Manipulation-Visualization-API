/*
 * File name:  DataSetItterator.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package com.botka.data.set.visualization.api.data;

import java.util.Iterator;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class DataSetPointer {

	private int mPointerPosition;
	private Iterator mItterator;

	/**
	 * 
	 */
	public DataSetPointer() {
		this.mPointerPosition = 0;
		// TODO Auto-generated constructor stub
	}

	public synchronized void incrementPosition() {
		this.mPointerPosition++;

	}

	public int getPointerPosition() {
		return this.mPointerPosition;
	}

	public void setPointerPosition(int position) {
		this.mPointerPosition = position;
	}

}
