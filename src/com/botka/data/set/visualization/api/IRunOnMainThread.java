package com.botka.data.set.visualization.api;

/**
 * 
 * Interface to implement method of running background threaded code on the main thread. This is implement dependent on the front end framework being used.
 *
 * @author Jake Botka
 *
 */
public interface IRunOnMainThread {
	public void runOnMainThread(Runnable run);
}
