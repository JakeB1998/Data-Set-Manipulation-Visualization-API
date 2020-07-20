/*
 * File name:  RenderEngine.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package com.botka.data.set.visualizer.render.engine;

import com.botka.data.set.visualizer.sort.Sort;
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
	private Sort mSortingAlgorithm;
	
	/**
	 * @paramrenderCallback
	 */
	public RenderEngine(Render renderCallback)
	{
		this.mRenderCallback = renderCallback;
		this.mCyclesPerSecond = -1; //init to null value;
	}
	
	public RenderEngine(Render callback, int cyclesPerSecond)
	{
		this(callback);
		this.mCyclesPerSecond = cyclesPerSecond;
	}
	
	public RenderEngine(Visualizer visualizer)
	{
		this((Render)visualizer);
	}
	
	/**
	 * 
	 * @param renderCallback
	 * @param cyclesPerSecond
	 */
	public RenderEngine(Visualizer visualizer, int cyclesPerSecond)
	{
		this((Render)visualizer, cyclesPerSecond);
		
	}
	
	/**
	 * inits object instance
	 */
	public void init()
	{
		 // if null then assign default otherwise keep same
		this.mCyclesPerSecond = this.mCyclesPerSecond == -1 ? DEFAULT_CYCLES_PER_SECOND : this.mCyclesPerSecond;
		
		this.render();
		
	}
	
	/**
	 * Called to manualy call a rendering operation
	 */
	public void onStep()
	{
		this.render();
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
	
	private class AsyncOperation implements Runnable
	{

		private boolean mRunning;
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
