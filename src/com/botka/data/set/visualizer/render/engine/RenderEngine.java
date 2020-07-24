/*
 * File name:  RenderEngine.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer.render.engine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.botka.data.set.visualizer.ExecuteInMainThreadManager;
import com.botka.data.set.visualizer.app.JavaFXMainDriver;
import com.botka.data.set.visualizer.data.DataSet;
import com.botka.data.set.visualizer.sort.Sort;
import com.botka.data.set.visualizer.step.StepOperation;
import com.botka.data.set.visualizer.step.StepResult;
import com.botka.data.set.visualizer.visualizer.Visualizer;

/**
 * Can not exstend from.
 * Base class for the render engine.
 * Supports multiple UI frameworks through render interface vallbacks
 *
 * @author Jake Botka
 *
 */
public final class RenderEngine
{

	private final int DEFAULT_CYCLES_PER_SECOND = 30;
	private AsyncOperation mThreadRunner;
	private Render mRenderCallback;
	private int mCyclesPerSecond;
	private StepOperation mStepOperation;
	
	/**
	 * @param Render interface callback method
	 */
	public RenderEngine(Render renderCallback, StepOperation stepOp)
	{
		this.mRenderCallback = renderCallback;
		this.mCyclesPerSecond = -1; //init to null value;
		this.mStepOperation = stepOp;
	}
	/**
	 * 
	 * @param Render interface callback method
	 * @param cyclesPerSecond
	 */
	public RenderEngine(Render callback, StepOperation stepOp,  int cyclesPerSecond)
	{
		this(callback, stepOp);
		this.mCyclesPerSecond = cyclesPerSecond;
	}
	/**
	 * 
	 * @param visualizer
	 */
	public RenderEngine(Visualizer visualizer, StepOperation stepOp)
	{
		this((Render)visualizer, stepOp);
	}
	
	/**
	 * 
	 * @param renderCallback
	 * @param cyclesPerSecond
	 */
	public RenderEngine(Visualizer visualizer, StepOperation stepOp, int cyclesPerSecond)
	{
		this((Render)visualizer, stepOp, cyclesPerSecond);
		
	}
	
	/**
	 * inits object instance
	 */
	public void init()
	{
		 // if null then assign default otherwise keep same
		this.mCyclesPerSecond = this.mCyclesPerSecond == -1 ? DEFAULT_CYCLES_PER_SECOND : this.mCyclesPerSecond;
		this.startRenderer();
		
	}
	
	/**
	 * Called to manualy call a rendering operation
	 */
	public void onStep(int step)
	{
		if (this.mStepOperation != null)
		{
			StepResult result = this.mStepOperation.onStep(step);
			this.render();
			if (result.isDone())
			{
				System.out.println("Sort is done from step result");
				this.haltRenderer();
			}
		}
		else
		{
			this.haltRenderer();
		}
		
	}
	
	/**
	 * Stops the renderer
	 */
	public void haltRenderer()
	{
		if (this.mThreadRunner != null)
		{
			this.mThreadRunner.stopThread();
		}
	}
	
	/**
	 * Resets the renderer
	 */
	public void restartRenderer()
	{
		this.startRenderer();
	}
	
	/**
	 * Starts the rednerer
	 */
	public void startRenderer()
	{
		this.mThreadRunner = new AsyncOperation();
		//Thread thread = new Thread(this.mThreadRunner);
		//thread.setDaemon(true);
		//thread.start();
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(this.mThreadRunner);
		executor.shutdown(); //awaits for task to complete
	}
	
	/**
	 * Renders visualizer by calling rendering implementation
	 */
	private void render()
	{
		if (this.mRenderCallback != null)
		{
			this.mRenderCallback.onRender();
		}
	}
	
	/**
	 * 
	 * Thread builder that handles background calculations to the dataset before rendering
	 *
	 * @author Jake Botka
	 *
	 */
	private class AsyncOperation implements Runnable
	{

		private boolean mRunning;
		private int mCyclesPerSec;
		public AsyncOperation()
		{
			mCyclesPerSec = mCyclesPerSecond == -1 ? DEFAULT_CYCLES_PER_SECOND : mCyclesPerSecond;
		}
		@Override
		public void run()
		{
			mRunning = true;

			long loggedTime = System.currentTimeMillis();
			final long millisecondPerCycle = 1000 / mCyclesPerSec;
			long timeRec = -1;
			long deltaTime = 0;
			int steps = 0;
			 BlockingQueue<Runnable> queue = ExecuteInMainThreadManager.getInstance().getQueue();
			while (mRunning)
			{
				timeRec = System.currentTimeMillis() - loggedTime;
				if (timeRec >= millisecondPerCycle)
				{
					;
					loggedTime = System.currentTimeMillis();
					steps++;
					final int stepCount = steps; // for enclosing viarable
					 
					 synchronized(queue) //ensures that other objects will block if accesssing object at same time
					 {
						 queue.add(new Runnable(){
				                @Override
				                public void run() {
				                	onStep(stepCount);
				                	ExecuteInMainThreadManager.getInstance().getTaskDoneFlag().set(true);
				                	
				                }
				            });
						 queue.notifyAll(); //notifiies moniter that queue is avaliable to access
					 }
					 
					 deltaTime = System.currentTimeMillis() - loggedTime; // time difference between start and end execution
					
				}
				
			}
			
		}
		
		/**
		 * Stops thread. Must be called by outer class
		 */
		public void stopThread()
		{
			mRunning = false;
		}
		
	}
	

}
