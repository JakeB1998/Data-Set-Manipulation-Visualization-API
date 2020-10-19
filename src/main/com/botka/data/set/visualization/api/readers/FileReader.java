/*
 * File name:  FileReader.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 20, 2020
 *
 */
package main.com.botka.data.set.visualization.api.readers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class FileReader extends Reader {

	private File mFile;

	/**
	 * 
	 * @param the file to be scanned
	 * @throws FileNotFoundException
	 */
	public FileReader(File file) throws FileNotFoundException {
		super(new Scanner(file));
		this.mFile = file;
	}

	/**
	 * 
	 * @param path to file
	 * @throws FileNotFoundException
	 */
	public FileReader(String path) throws FileNotFoundException {
		super(new Scanner(new File(path)));
	}

	/**
	 * @return string reprensentation of the contents of the file
	 * @Override
	 */
	@Override
	public String quickRead(Scanner scanner) {
		String result = "";
		while (super.getScanner().hasNext()) {
			result += super.getScanner().next();
		}

		return result;
	}

	@Override
	public String readEntire() {

		return this.quickRead(super.getScanner());
	}

	@Override
	public String readString() {
		return super.getScanner().next();
	}

	@Override
	public int readInt() {
		return super.getScanner().nextInt();
	}

	@Override
	public double readDouble() {
		return super.getScanner().nextDouble();
	}

	@Override
	public long readLong() {
		return super.getScanner().nextLong();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return super.getScanner().hasNext();
	}

	@Override
	public boolean hasNextInt() {
		// TODO Auto-generated method stub
		return super.getScanner().hasNextInt();
	}

	@Override
	public boolean hasNextDouble() {
		return super.getScanner().hasNextDouble();
	}

	@Override
	public boolean hasNextString() {

		return super.getScanner().hasNextLine();
	}

	@Override
	public boolean hasNextLong() {
		return super.getScanner().hasNextLong();
	}

	@Override
	public boolean hasNextByte() {

		return super.getScanner().hasNextByte();
	}

	/**
	 * Reads all the lines inside of the file Does not use a Vector but rather uses
	 * a increments growth array that grows when certain lengths are exceeded. When
	 * reading is completed then the array is s to size.
	 * 
	 * @param array of lines read from the file
	 */
	@Override
	public String[] readAllLines() {
		String[] arr = new String[1000];
		int index = 0;
		while (this.hasNextLine()) {
			if (index < arr.length) {
				arr[index] = this.readLine();
				index++;
			} else {
				arr = Arrays.copyOf(arr, arr.length * 2);
			}

		}

		return Arrays.copyOf(arr, index + 1);
	}

	/**
	 * Reads next token which is represented as a string
	 */
	public String readNext() {
		return super.getScanner().next();
	}

	@Override
	public String readLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] readAllInts() {
		int[] arr = new int[1000];
		int index = 0;
		while (this.hasNext()) {
			if (this.hasNextInt()) {
				if (index < arr.length) {
					arr[index] = this.readInt();
					index++;
				} else {
					arr = Arrays.copyOf(arr, arr.length * 2);
				}
			} else
				this.readNext();

		}

		return Arrays.copyOf(arr, index + 1);

	}

	/**
	 * @return an array of all doubles in the document
	 */
	@Override
	public double[] readAllDoubles() {
		double[] arr = new double[1000];
		int index = 0;
		while (this.hasNext()) {
			if (this.hasNextDouble()) {
				if (index < arr.length) {
					arr[index] = this.readDouble();
					index++;
				} else {
					arr = Arrays.copyOf(arr, arr.length * 2);
				}
			} else
				this.readNext();

		}

		return Arrays.copyOf(arr, index + 1);
	}

	@Override
	public long[] readAllLongs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte readByte() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] readAllBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasNextLine() {
		// TODO Auto-generated method stub
		return super.getScanner().hasNextLine();
	}

	@Override
	public void resetReader() {
		try {
			super.setScanner(new Scanner(this.mFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
