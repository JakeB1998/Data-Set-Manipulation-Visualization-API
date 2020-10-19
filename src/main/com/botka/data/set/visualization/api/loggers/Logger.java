/*
 * File name:  Logger.java
 *
 * Programmer : Jake Botka
 * ULID: JMBOTKA
 *
 * Date: May 26, 2020
 *
 * Out Of Class Personal Program
 */
package main.com.botka.data.set.visualization.api.loggers;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class Logger {

	/**
	 * 
	 */
	public Logger() {

	}

	public abstract void log(String log, boolean logTime);

	public abstract void log(String log);

	public abstract void logTime();

	public abstract void logLines(int num);

}
