/*
 * File name:  IDGenerator.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualization.api.util;

import java.util.Random;

import com.botka.data.set.visualization.api.loggers.ConsoleLogger;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class IDGenerator {

	public static String generateID(int number) {

		String id = "";
		Random ran = new Random();
		for (int i = 0; i < number; i++) {
			id += Integer.toString(ran.nextInt(9));
		}
		return id;
	}

	public static long generateLongID() {
		Random ran = new Random();
		return ran.nextLong();
	}

	/**
	 * Generated a randomized integer id
	 * 
	 * @param length The length of the id
	 * @return Generated integer id
	 */
	public static int generateIntegerID(int length) {
		Random ran = new Random();
		String stream = "";
		int id = -1;
		for (int i = 0; i < length; i++) {
			stream += String.valueOf(ran.nextInt(10));
		}
		id = Integer.parseInt(stream);
		printID(id);
		return id;
	}

	public static void printID(String id) {
		ConsoleLogger.Logger.log(IDGenerator.class, "\n Generated ID: " + id, true);
	}

	public static void printID(int id) {
		printID(String.valueOf(id));
	}

	public static void printID(long id) {
		printID(String.valueOf(id));
	}
}
