/*
 * File name:  Loggable.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 6, 2020
 *
 */
package main.com.botka.data.set.visualization.api.util;

/**
 * Interface representing handling log requests.
 *
 * @author Jake Botka
 *
 */
public interface Loggable {

	void setLogActivity(boolean value);
	boolean isLoggingActivity();
}
