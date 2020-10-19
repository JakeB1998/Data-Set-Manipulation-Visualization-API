package main.com.botka.data.set.visualization.api.loggers;

/**
 * 
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public interface Logging {
	public void logString(String str);

	public void logLines(int num);

	void logString(String str, boolean logTime);

}
