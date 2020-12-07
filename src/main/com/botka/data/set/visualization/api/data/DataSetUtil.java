/*
 * File name:  DataSetUtils.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 23, 2020
 *
 */
package main.com.botka.data.set.visualization.api.data;

import java.io.File;
import java.io.FileNotFoundException;

import main.com.botka.data.set.visualization.api.readers.FileReader;

/**
 * Utility class for DataSet.java
 *
 * @author Jake Botka
 *
 */
public class DataSetUtil {

	/**
	 * reads data from file using my FileReader APi reads doubles into dataset
	 * 
	 * @param file to be read from
	 * @return Dataset object with corresponding Type
	 */
	public static DataSet<Double> readDoublesFromFile(File file) {
		try {
			FileReader reader = new FileReader(file);
			DataSet<Double> dataset = null;
			double[] arr = reader.readAllDoubles();
			dataset = new DataSet<Double>();
			if (arr != null) {
				for (double d : arr) {
					dataset.add(new Double(d));
				}
			}

			return dataset;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return null;

	}

	/**
	 * reads data from file using my FileReader APi reads integers into dataset
	 * 
	 * @param file to be read from
	 * @return Dataset object with corresponding Type
	 */
	public static DataSet<Integer> readIntegersFromFile(File file) {
		try {
			FileReader reader = new FileReader(file);
			DataSet<Integer> dataset = null;
			int[] arr = reader.readAllInts();
			dataset = new DataSet<Integer>();
			if (arr != null) {
				for (int i : arr) {
					dataset.add(new Integer(i));
				}
			}

			return dataset;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return null;

	}

}
