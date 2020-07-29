/*
 * File name:  Reader.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 20, 2020
 *
 */
package com.botka.data.set.visualization.api.readers;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public abstract class Reader
{

	private Scanner mScanner;
	/**
	 * 
	 */
	public Reader(Scanner scanner)
	{
		this.mScanner = scanner;
	}
	
	public Scanner getScanner()
	{
		return this.mScanner;
	}
	protected void setScanner(Scanner scan)
	{
		this.mScanner = scan;
	}
	
	public abstract String quickRead(Scanner scanner);
	public abstract String readEntire();
	public abstract String[] readAllLines();
	public abstract String readNext();
	public abstract String readLine();
	public abstract String readString();
	public abstract int readInt();
	public abstract int[] readAllInts();
	public abstract double readDouble();
	public abstract double[] readAllDoubles();
	public abstract long readLong();
	public abstract long[] readAllLongs();
	public abstract byte readByte();
	public abstract byte[] readAllBytes();
	
	public abstract boolean hasNext();
	public abstract boolean hasNextLine();
	public abstract boolean hasNextInt();
	public abstract boolean hasNextDouble();
	public abstract boolean hasNextString();
	public abstract boolean hasNextLong();
	public abstract boolean hasNextByte();
	
	public abstract void resetReader();
	

}
