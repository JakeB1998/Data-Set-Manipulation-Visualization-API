package com.botka.data.set.visualizer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Platform;

/**
 * 
 * Backroudn thread that manages tasks that are to be called on the main thread by implements the IOnMainThread interface.
 * This allows programmers to implemnt their own method of transfering data to the main thread or UI thread.
 * This is frameworj dependent thus why implementation is required depending on the framework
 *
 * @author Jake Botka
 *
 */
public class ExecuteInMainThreadManager
{
	private final BlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<Runnable>();
	public static final ExecuteInMainThreadManager INSTANCE = new ExecuteInMainThreadManager();
	
	private IRunOnMainThread mCallback;
	public static ExecuteInMainThreadManager getInstance()
	{
		return INSTANCE;
	}
	
	private ExecuteInMainThreadManager()
	{
		Thread t = new Thread(new AsyncOperation());
		t.start();
	}
	
	public boolean addToQuest(Runnable runnable)
	{
		QUEUE.add(runnable);
		return true;
	}
	
	public BlockingQueue<Runnable> getQueue()
	{
		return QUEUE;
	}
	
	public void setMainThreadCallback(IRunOnMainThread callback)
	{
		this.mCallback = callback;
	}
	
	
	private class AsyncOperation implements Runnable
	{

		@Override
		public void run()
		{
			while (true)
			{
				synchronized(QUEUE)
				{
					if (QUEUE.isEmpty())
					{
						try
						{
							System.out.println("Waiting");
							QUEUE.wait();
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
						}
					}
					else
					{
						try
						{
							Runnable runnable = QUEUE.take(); //javafx implementation
							IRunOnMainThread callback = ExecuteInMainThreadManager.this.mCallback;
							if (callback != null)
							{
								callback.runOnMainThread(runnable);
							}
							
							runnable = null;
							callback = null;
							QUEUE.notifyAll();
						} catch (InterruptedException e)
						{
							
							e.printStackTrace();
						}
					}
				}
			}
			
		}
		
	}

}
