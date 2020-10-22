/*
 * File name:  QuickStartTestDriver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Sep 5, 2020
 *
 */
package test;

import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import javafx.application.Preloader.StateChangeNotification;
import main.com.botka.data.set.visualization.api.data.DataSet;
import main.com.botka.data.set.visualization.api.quickstart.ApplicationArgsNotProvided;
import main.com.botka.data.set.visualization.api.quickstart.BubbleSortQuickStart;

/**
 * Class tests calling the quickstart application classes
 *
 * @author Jake Botka
 *
 */
public class QuickStartTestDriver {

	public static final String[] NO_ARGS = {};
	/**
	 * @param args
	 */
	@Test
	public  void  QuickStartTest() {
		BubbleSortQuickStart qStart = new BubbleSortQuickStart(randomizedData());
		try {
			qStart.startApplication(NO_ARGS);
		} catch (ApplicationArgsNotProvided e) {
			e.printStackTrace();
		}
		assertTrue(qStart.isShowing());

	}
	
	
	
	public  DataSet<Comparable> randomizedData() {
		DataSet<Comparable> set = new DataSet();
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
