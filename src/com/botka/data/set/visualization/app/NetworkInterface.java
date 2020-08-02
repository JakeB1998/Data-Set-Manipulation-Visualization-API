/*
 * File name:  NetworkInterface.java
 *
 * Programmer : Jake Botka
 *
 * Date: Aug 2, 2020
 *
 */
package com.botka.data.set.visualization.app;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.botka.data.set.visualization.api.loggers.ConsoleLogger;

/**
 * 
 *
 * @author Jake Botka
 *
 */
public class NetworkInterface
{
	public static final NetworkInterface INSTANCE = new NetworkInterface();
	public static NetworkInterface getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Default constructor
	 */
	private NetworkInterface()
	{
		
	}
	
	/**
	 * Connects to the url google.com to test if an internet connection is present.
	 * @return true if is connected to internet, false otherwise
	 */
	public boolean hasInternetConnection()
	{

	    try {
	        final URL url = new URL("http://www.google.com");
	        final URLConnection conn = url.openConnection();
	        conn.connect();
	        conn.getInputStream().close();
	        ConsoleLogger.Logger.log(JavaFXMainDriver.class, "connected to internet", true);
	        return true;
	    } catch (MalformedURLException e) {
	        throw new RuntimeException(e);
	    } catch (IOException e) {
	        ConsoleLogger.Logger.log(JavaFXMainDriver.class, "Not connected to internet", true);
	    }
	    return false;
	}

}
