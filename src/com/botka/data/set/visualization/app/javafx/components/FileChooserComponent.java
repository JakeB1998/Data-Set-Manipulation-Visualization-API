/*
 * File name:  FileChooserComponent.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 10, 2020
 *
 */
package com.botka.data.set.visualization.app.javafx.components;

import java.io.File;

import com.botka.data.set.visualization.api.util.IDGenerator;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * File chooser component to handle interfacing with the computer's file system
 *
 * @author Jake Botka
 *
 */
public class FileChooserComponent extends Component
{

	private static final int STATIC_ID = 28483;
	private final int UNIQUE_ID;
	private FileChooser mFileChooser;
	
	/**
	 * 
	 */
	public FileChooserComponent()
	{
		this(new FileChooser());
	}
	
	/**
	 * 
	 */
	public FileChooserComponent(FileChooser fileChooser)
	{
		super((STATIC_ID));
		UNIQUE_ID = IDGenerator.generateIntegerID(UNIQUE_ID_LENGTH);
		this.setFileChooser(fileChooser);
	}
	
	
	public void addFileFilter(FileChooser.ExtensionFilter filter)
	{
		if (this.mFileChooser != null)
			this.mFileChooser.getExtensionFilters().addAll( filter);
		else
		{
			this.mFileChooser = new FileChooser();
			this.addFileFilter(filter);
		}
	}
	
	public FileChooser getFileChooser()
	{
		return this.mFileChooser;
	}
	
	public void setFileChooser(FileChooser fileChooser)
	{
		this.mFileChooser = fileChooser;
	}
	
	public File accessFiles(Stage stage)
	{
		File file = null;
		file = this.mFileChooser.showOpenDialog(stage);
		return file;
	}
	
	/**
	 * 
	 * @param directoryPath
	 */
	public void setDirectory(String directoryPath)
	{
		File dir = new File(directoryPath);
		if (dir.exists())
			if (dir.isDirectory())
				this.setDirectory(dir);	
	}
	
	/**
	 * 
	 * @param directory
	 */
	public void setDirectory(File directory)
	{
		if (this.mFileChooser != null)
		{
			this.mFileChooser.setInitialDirectory(directory);
		}
		else
		{
			this.mFileChooser = new FileChooser();
			this.setDirectory(directory); // recall method
		}
	}

	@Override
	public int getStaticID()
	{
		return STATIC_ID;
	}

	@Override
	public int getUniqueID()
	{
		// TODO Auto-generated method stub
		return UNIQUE_ID;
	}
	
	/**
	 * @return
	 */
	public String toString()
	{
		return super.toString();
	}
	
	

}
