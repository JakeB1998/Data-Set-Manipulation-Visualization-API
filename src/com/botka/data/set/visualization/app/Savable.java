/*
 * File name:  Savable.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 3, 2020
 *
 */
package com.botka.data.set.visualization.app;

import java.io.File;
import java.io.Serializable;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public interface Savable<T extends Serializable>
{
	void save();
	void save(File file);
}
