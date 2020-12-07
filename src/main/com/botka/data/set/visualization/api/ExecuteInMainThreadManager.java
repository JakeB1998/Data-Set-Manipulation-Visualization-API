package main.com.botka.data.set.visualization.api;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * Background thread that manages tasks that are to be called on the main thread
 * by implements the IOnMainThread interface. This allows programmers to
 * Implement their own method of transferring data to the main thread or UI
 * thread. This is framework dependent thus why implementation is required
 * depending on the framework
 *
 * @author Jake Botka
 *
 */
public class ExecuteInMainThreadManager {
	private final BlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<Runnable>();
	public static final ExecuteInMainThreadManager INSTANCE = new ExecuteInMainThreadManager();
	public final AtomicBoolean mDoneOnUIThread = new AtomicBoolean();

	private IRunOnMainThread mCallback;

	/**
	 * 
	 * @return
	 *
	 */
	public static ExecuteInMainThreadManager getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 */
	private ExecuteInMainThreadManager() {
		Thread t = new Thread(new AsyncOperation());
		t.start();
	}

	/**
	 * 
	 * @param runnable
	 * @return
	 *
	 */
	public boolean addToQuest(Runnable runnable) {
		QUEUE.add(runnable);
		return true;
	}

	/**
	 * 
	 * @return
	 *
	 */
	public BlockingQueue<Runnable> getQueue() {
		return QUEUE;
	}

	/**
	 * 
	 * @param callback
	 *
	 */
	public void setMainThreadCallback(IRunOnMainThread callback) {
		this.mCallback = callback;
	}

	/**
	 * Get the flag that encompases the completion of the asyn task.
	 * 
	 * @return Flag of task completion.
	 *
	 */
	public AtomicBoolean getTaskDoneFlag() {
		return this.mDoneOnUIThread;
	}

	/**
	 * Class that encompases async operation
	 * 
	 * @author Jake Botka
	 *
	 */
	private class AsyncOperation implements Runnable {

		@Override
		public void run() {
			while (true) {
				synchronized (QUEUE) {
					if (QUEUE.isEmpty()) {
						try {
							System.out.println("Waiting");
							QUEUE.wait();
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					} else {
						try {
							Runnable runnable = QUEUE.take(); // javafx implementation
							IRunOnMainThread callback = ExecuteInMainThreadManager.this.mCallback;
							if (callback != null) {
								callback.runOnMainThread(runnable);
								while (mDoneOnUIThread.get() == false) {

								}
								System.out.println("Task finished in UI thread");
								mDoneOnUIThread.set(false);
							}

							runnable = null;
							callback = null;
							QUEUE.notifyAll();
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}
				}
			}

		}

	}

}
