/*
 * File name:  IDataPeekListener.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package main.com.botka.data.set.visualization.api.data;

/**
 * Listener to peek at data in the dataset without dirrectly accessing it.
 *
 * @author Jake Botka
 *
 */
public interface DataPeekListener<T> {

	void onPeak(int index, T data);

}
