/*
 * File name:  Sort.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 18, 2020
 *
 */
package main.com.botka.data.set.visualization.api.sort;

import main.com.botka.data.set.visualization.api.data.DataSet;
import main.com.botka.data.set.visualization.api.step.SortStep;
import main.com.botka.data.set.visualization.api.step.StepOperation;

/**
 * Base class that represent all sorting algorithms. Meant to be extending by a
 * specific sorting implementation. Derives from base class StepOperation to be
 * utilized by the render engine class.
 *
 * @author Jake Botka
 *
 */
public abstract class Sort extends StepOperation {

	private String mSortAlgorithm;
	private DataSet<?> mDataSet;
	private int mNumberOfSteps;
	private IFinishedListener mFinishedListener;

	/**
	 * Object constructor.
	 * 
	 * @param sortAlgo      This is the sorting algorithm being implemented
	 * @param dataset       The dataset that the class will utilize
	 * @param numberOfSteps The number of steps to be taken, -1 if undeterminent
	 */
	public Sort(String sortAlgo, DataSet dataset, int numberOfSteps) {
		super();
		this.mSortAlgorithm = sortAlgo;
		this.mDataSet = dataset;
		this.mNumberOfSteps = numberOfSteps;
		// TODO Auto-generated constructor stub
	}

	public abstract boolean canStep(int currentStep);

	public abstract SortStep onSortingStep(int stepCount);

	public abstract void sort();

	/**
	 * Called when subclass has finished its operation
	 */
	public void onFinished() {

		if (this.mFinishedListener != null) {
			this.mFinishedListener.onFinished();
		}
	}

	/**
	 * 
	 * @return Data Set
	 */
	public DataSet<?> getDataSet() {
		return this.mDataSet;
	}
	
	/**
	 * 
	 */
	public void setDataSet(DataSet<Comparable> dataSet) {
		this.mDataSet = dataSet;
	}

	/**
	 * 
	 * @return Maximum number of step this sort can take.
	 */
	public int getMaxAmountOfSteps() {
		return this.mNumberOfSteps;
	}

	/**
	 * 
	 * @param listener
	 */
	@Override
	public void registerOnFinishedListener(IFinishedListener listener) {
		this.mFinishedListener = listener;
	}
	
	/**
	 * 
	 */
	@Override
	public String getCondensedOperationDetails() {
		return this.getAlgorithm();
	}

	/**
	 * 
	 * @return
	 */
	public String getAlgorithm() {
		return this.mSortAlgorithm;
	}

}
