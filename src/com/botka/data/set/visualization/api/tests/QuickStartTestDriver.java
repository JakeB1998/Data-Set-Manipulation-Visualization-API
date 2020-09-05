/*
 * File name:  QuickStartTestDriver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 5, 2020
 *
 */
package com.botka.data.set.visualization.api.tests;

import java.util.Random;

import com.botka.data.set.visualization.api.data.DataSet;
import com.botka.data.set.visualization.api.quickstart.ApplicationArgsNotProvided;
import com.botka.data.set.visualization.api.quickstart.BubbleSortQuickStart;

/**
 * Class tests calling the quickstart application classes
 *
 * @author Jake Botka
 *
 */
public class QuickStartTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BubbleSortQuickStart qStart = new BubbleSortQuickStart(randomizedData());
		try {
			qStart.startApplication(args);
		} catch (ApplicationArgsNotProvided e) {
			e.printStackTrace();
		}

	}

	public static DataSet<Comparable> randomizedData() {
		DataSet<Comparable> set = new DataSet(0);
		// insert data here start
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			Object value = ran.nextDouble() * 100;
			set.add((Comparable) value);
		}

		return set;
		// insert data here end
	}
}
