package com.botka.data.set.visualization.api.loggers;

public interface ILogger
{
	public void logString(String str);
	public void logLines(int num);
	void logString(String str, boolean logTime);
	
	

}
