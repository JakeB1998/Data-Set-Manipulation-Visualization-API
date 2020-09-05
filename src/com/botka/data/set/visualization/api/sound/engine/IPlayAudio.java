/*
 * File name:  IPlayAudio.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualization.api.sound.engine;

import java.io.File;

/**
 * Interface callback that plays an audio file based on programmers implementation and framework they are using.
 *
 * @author Jake Botka
 *
 */
public interface IPlayAudio {

	public boolean playAudio(File file);

}
