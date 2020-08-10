/*
 * File name:  LifeCycleCallbackListener.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 10, 2020
 *
 */
package com.botka.data.set.visualization.api;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public interface LifeCycleCallbackListener
{

	public abstract void onStart();
	public abstract void onPause();
	public abstract void onResume();
	public abstract void onStop();
	public abstract void onFinished();
	public abstract boolean isInitialized();
	public abstract boolean isRunning();
	public abstract boolean isStopped();
	public abstract boolean isPaused();
}
