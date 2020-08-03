package com.botka.data.set.visualization.api.loggers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends Logger 
{
	private File mLoggerFile;
	private BufferedWriter mBufferedWriter;
	private FileWriter mFileWriter;
	private FileReader mFileReader;
	
	private DateTimeFormatter mTimeFormat;
	
	private boolean mTimeStamps;
	
	/**
	 * 
	 * @param file
	 */
	public FileLogger(File file)
	{
		this.init(file, false);
	}
	

	/**
	 * 
	 * @param file
	 * @param timeStamps
	 */
	public FileLogger(File file, boolean timeStamps)
	{
		this(file);
		this.mTimeStamps = timeStamps;
		
	}
	/**
	 * 
	 * @param file
	 * @param timeStamps
	 * @param clearDoc
	 */
	public FileLogger(File file, boolean timeStamps,  boolean clearDoc)
	{
		this.init(file, clearDoc);
		this.mTimeStamps = timeStamps;
		
	}
	
	/**
	 * 
	 * @param path
	 */
	public FileLogger(String path)
	{
		this.init(new File(path), false);
	}
	
	/**
	 * 
	 * @param path
	 * @param timeStamps
	 */
	public FileLogger(String path, boolean timeStamps)
	{
		this(path);
		this.mTimeStamps = timeStamps;
	}
	
	/**
	 * 
	 * @param path
	 * @param timeStamps
	 */
	public FileLogger(String path, boolean timeStamps, boolean clearDoc)
	{
		this(new File(path), timeStamps, clearDoc);
	}
	/**
	 * Inits the File logger class by creating the neccassary stream writers.
	 * Catches only because this is a private method being called inside the constructors
	 * @param file
	 * @param clearDoc
	 */
	private void init(File file, boolean clearDoc)
	{
		mLoggerFile = file;
		try
		{
			mFileWriter = new FileWriter(mLoggerFile, clearDoc);
			mBufferedWriter = new BufferedWriter(mFileWriter);
		} catch (IOException e)
		{
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void logTime()
	{
		this.logTime(DateTimeFormatter.BASIC_ISO_DATE);
	}
	
	/**
	 * @param format
	 */
	public void logTime(DateTimeFormatter format)
	{
		String time = LocalDateTime.now().format(format);
		this.logStringSingleLine(time);
	}
	
	/**
	 * writes string to file
	 * @param str
	 */
	@Override
	public void log(String str)
	{
		this.log(str, this.mTimeStamps);	
	}
	
	/**
	 * writes string to file
	 * @param str
	 */
	@Override
	public void log(String str, boolean logTime)
	{
		try
		{
			this.logTime();
			mBufferedWriter.write(str);
			mBufferedWriter.flush();
			this.logLines(1);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void logStringSingleLine(String str)
	{
		try
		{
			mBufferedWriter.write(str);
			mBufferedWriter.flush();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/**
	 * Logs emty lines to create line breaks and/or spaces
	 * @param num
	 */
	@Override
	public void logLines(int num)
	{
		try
		{
			for (int i =0 ; i < num; i++)
			{
				mBufferedWriter.newLine();
				mBufferedWriter.flush();
			}
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * closes the stream to the file.
	 */
	public void close()
	{
		try
		{
			mFileWriter.close();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @return
	 */
	public DateTimeFormatter getFormat()
	{
		return mTimeFormat;
	}


	/**
	 * 
	 * @param mTimeFormat
	 */
	public void setTimeFormat(DateTimeFormatter mTimeFormat)
	{
		this.mTimeFormat = mTimeFormat;
	}
	
	/**
	 * 
	 */
	public String toString()
	{
		return super.toString();
	}

	

}
