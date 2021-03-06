/*
 * File name:  SortStep.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 19, 2020
 *
 */
package main.com.botka.data.set.visualization.api.step;

/**
 * Class providing implementation to the abstract class: StepResult
 *
 * @author Jake Botka
 *
 */
public class SortStep extends StepResult {
	private int mIndex, mStep;

	/**
	 * Main constructor
	 * 
	 * @param index
	 * @param step
	 * @param done
	 */
	public SortStep(int index, int step, boolean done) {
		super(step, done);
		this.mIndex = index;
		this.mStep = step;

	}

	/**
	 * 
	 * @return index
	 */
	public int getIndex() {
		return this.mIndex;
	}

}
