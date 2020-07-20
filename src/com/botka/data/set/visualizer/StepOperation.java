/*
 * File name:  StepOperation.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 19, 2020
 *
 */
package com.botka.data.set.visualizer;

/**
 * Abstract class that represents the basic structure of executing a step and reading its results.
 * Must be implemented by subclass.
 *
 * @author Jake Botka
 *
 */
public abstract class StepOperation
{

	public abstract StepResult onStep(int step);

}
