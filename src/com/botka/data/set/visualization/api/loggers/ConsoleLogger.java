package com.botka.data.set.visualization.api.loggers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class ConsoleLogger extends Logger implements Logging {
	private boolean mLogTimePermission;

	/**
	 * 
	 */
	public ConsoleLogger(boolean logTime) {
		this.mLogTimePermission = logTime;
	}

	/**
	 * Logs string. Implemented by ILogger
	 */
	@Override
	public void logString(String str) {
		this.logString(str, this.mLogTimePermission);

	}

	/**
	 * Logs string
	 * 
	 * @param str
	 * @param logTime
	 */
	@Override
	public void logString(String str, boolean logTime) {
		if (logTime) {
			this.logTime();

		}

		System.out.print(str);
		System.out.println();
	}

	/**
	 * 
	 */
	@Override
	public void log(String log, boolean logTime) {
		this.logString(log, logTime);
	}

	/**
	 * Logs string data by calling the Ilogger interface impleentation of logString
	 */
	@Override
	public void log(String log) {
		this.logString(log, false);

	}

	/**
	 * Logs the time in format
	 */
	@Override
	public void logTime() {

	}

	@Override
	public void logLines(int num) {
		String log = "";
		if (num > 0) {
			for (int i = 0; i < num; i++) {
				log += "\n";
			}

			System.out.println(log);
		}

	}

	/**
	 * 
	 * public static internal class for static console logging
	 *
	 * @author Jake Botka
	 *
	 */
	public static class Logger {
		/**
		 * 
		 * @param message The message to be logged
		 */
		public static void log(String message) {
			log(null, message, false);
		}

		/**
		 * 
		 * @param message The message to be logged
		 * @param logTime whether or not time should be inside the log
		 */
		public static void log(String message, boolean logTime) {
			log(null, message, logTime);
		}

		/**
		 * 
		 * @param c       The class where the log has been called from
		 * @param message The message to be logged
		 */
		public static void log(Class<?> c, String message) {
			log(c, message, false);
		}

		/**
		 * 
		 * @param c       The class where the log has been called from
		 * @param message The message to be logged
		 * @param logTime whether or not time should be inside the log
		 */
		public static void log(Class<?> c, String message, boolean logTime) {

			String log = "";
			if (logTime)
				log += LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME) + ": ";
			if (c != null)
				log += c.getName() + ": ";
			if (message != null)
				log += message;

			System.out.println(log);
		}
	}

}
