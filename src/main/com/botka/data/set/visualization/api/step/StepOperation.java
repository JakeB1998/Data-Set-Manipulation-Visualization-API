/*
 * File name:  StepOperation.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 19, 2020
 *
 */
package main.com.botka.data.set.visualization.api.step;

import main.com.botka.data.set.visualization.api.data.DataSet;
import main.com.botka.data.set.visualization.api.sort.IFinishedListener;

/**
 * Abstract class that represents the basic structure of executing a step and
 * reading its results. Must be implemented by subclass.
 *
 * @author Jake Botka
 *
 */
public abstract class StepOperation {

	public abstract StepResult onStep(int step);

	public abstract void setDataSet(DataSet<Comparable> dataset);

	public abstract void registerOnFinishedListener(IFinishedListener finishedListener);

	public abstract String getCondensedOperationDetails();

}
