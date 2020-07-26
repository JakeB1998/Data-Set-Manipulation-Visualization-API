/*
 * File name:  IDGenerator.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 26, 2020
 *
 */
package com.botka.data.set.visualizer.util;

import java.util.Random;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class IDGenerator
{

	 public static String generateID(int number)
     {

        String id = "";
        Random ran = new Random();
        for (int i = 0; i < number; i++)
        {
            id += Integer.toString(ran.nextInt(9));
        }
        return id;
     }
	 
	 public static long generateLongID()
	 {
		 Random ran = new Random();
		 return ran.nextLong();
	 }
}
