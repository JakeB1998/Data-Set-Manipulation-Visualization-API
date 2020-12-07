/*
 * File name:  JavaFXVisualizer.java
 *
 * Programmer : Jake Botka
 *
 * Date: Jul 16, 2020
 *
 */
package main.com.botka.data.set.visualization.api.visualizer;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.com.botka.data.set.visualization.api.data.DataPeekListener;
import main.com.botka.data.set.visualization.api.data.DataSet;

import main.com.botka.data.set.visualization.api.sound.engine.IAudioListener;
import main.com.botka.data.set.visualization.api.sound.engine.IPlayAudio;

/**
 * Integrated controller class for the JavaFX framework extending from abstract
 * class: Visualizer
 *
 * @author Jake Botka
 *
 */
public class JavaFXVisualizer extends Visualizer {
	private Stage mStage;
	private Scene mScene;

	private Canvas mCanvas;
	private GraphicsContext mContext;
	private double mGroundX, mGroundY, mScaleXFactor, mScaleYFactor;
	private boolean mReady, mInitialized, mStarted, mRunning, mStopped, mPaused;

	private final Color DEFAULT_BLOCK_COLOR = Color.GREY;
	private final Color DEFAULT_BLOCK_STROKE_COLOR = Color.WHITESMOKE;
	private final Color DEFUALT_POINTER_BLOCK_COLOR = Color.BLUE;
	private final double MAX_SCALE_X = 50.0;
	private final double MAX_SCLAE_Y = 100.0;
	private double mTextX;

	private String mPrefixTitle;
	private String mTitle;

	private DataPeekListener mDataPeekListener;

	/**
	 * 
	 * @param the dataset the visualizer will work with
	 * @param the Javafx stage window
	 */
	public JavaFXVisualizer(DataSet<?> dataSet, Stage stage) {
		super(dataSet);
		this.mStage = stage;
	}

	/**
	 * 
	 * @param the dataset the visualizer will work with
	 * @param the Javafx stage window
	 * @param The JavaFX scene container
	 */
	public JavaFXVisualizer(DataSet<?> dataSet, Stage stage, Scene scene) {
		this(dataSet, stage);
		this.mScene = scene;

	}

	/**
	 *
	 * @param the Javafx stage window
	 * @param The JavaFX scene container
	 * @param The canvas container to draw on
	 */
	public JavaFXVisualizer(DataSet<?> dataSet, Stage stage, Scene scene, Canvas canvas) {
		this(dataSet, stage, scene);
		this.mCanvas = canvas;
		// if canvas null then assign context null otherwise call canvas getContext
		// method;

	}

	/**
	 * 
	 */
	@Override
	public void init() {
		this.mContext = this.mCanvas != null ? this.mCanvas.getGraphicsContext2D() : null;
		this.mGroundX = this.mCanvas != null ? 0 : -1;
		this.mGroundY = this.mCanvas != null ? (int) this.mCanvas.getHeight() : -1;
		this.setTitle("JavaFX Implementation Data Set Visuliztion");
		if (this.mPrefixTitle == null)
			this.setPrefixTitle("");
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();

		this.mReady = this.checkIfReady();
		if (mReady) {

			DataSet<?> set = super.getWorkingDataSet();
			if (set != null) {
				if (set.isNumberArray()) {

					double max = set.parseValue(set.getMax());
					double min = set.parseValue(set.getMin());

					this.mScaleYFactor = this.mCanvas.getHeight() / ((max - min));
					this.mScaleXFactor = this.mCanvas.getWidth() / set.size();
					if (this.mScaleXFactor > MAX_SCALE_X)
						this.mScaleXFactor = MAX_SCALE_X;

					while (this.mCanvas.getHeight() - this.mScaleYFactor * max < 0) // the value can
																					// not be
																					// represented
																					// in the
																					// current scale
																					// without
																					// cutting off
																					// screen
					{
						System.out.println("Scale factor was to large from the larges value at : "
								+ this.mScaleYFactor);
						this.mScaleYFactor /= 2;
					}
				}
			}
		}

		this.mInitialized = true;
	}

	/**
	 * Inits the canvas settings ONly to be callled inside of this class
	 */
	private void initCanvasSettings() {

		this.mContext.setFill(Color.GREY);
		this.mContext.setStroke(Color.WHITESMOKE);
		this.mContext.setLineWidth(3);

		// this.drawTitle();
	}

	/**
	 * Checks whether the visualizer has all the neccassary data non nulled to
	 * operate.
	 * 
	 * @return boolean
	 */
	public boolean checkIfReady() {
		if (this.mCanvas != null && this.mContext != null && this.mGroundX >= 0
				&& this.mGroundY >= 0 && this.mScene != null && this.mStage != null)
			return true;

		return false;
	}

	/**
	 * Returns an error message that specifies why the visualizer is not ready. This
	 * is most liekly due to null objects however this method will return which
	 * objects are null
	 * 
	 * @return
	 */
	public String checkErrorCode() {
		String error = "";
		if (this.mReady) {
			return "No error";
		} else {
			error += "Error Summary:";
			error += "\nStage: ";
			error += this.mStage != null ? "Not null" : "Null";
			error += "\nScene: ";
			error += this.mScene != null ? "Not null" : "Null";
			error += "\nCanvas: ";
			error += this.mCanvas != null ? "Not null" : "Null";
			return error;
		}
	}

	private DecimalFormat df = new DecimalFormat("0.00##");

	@Override
	/**
	 * Overridden from Interface implementation of superclass Called on each frame
	 * rendered with this framework implementation of JAVAFX front end frame work
	 */
	public void onRender() {
		this.render(getWorkingDataSet());
	}

	@Override
	/**
	 * 
	 */
	public void render(DataSet<? extends Comparable> set) {

		if (this.mReady) {
			if (!this.mStarted)
				this.onStart();

			if (this.mStage.isShowing()) {
				this.renderData(set);

				this.drawPointer(set); // draws the position of the pointer
				this.handleUI();
			}
		} else {
			System.err.println(this.getClass().getName()
					+ ": There is a vairbale that is null, Call the check error code method for more information");
			
		}

	}

	public void renderData(DataSet<? extends Comparable> set) {
		System.out.println("On rendered");
		this.clearCanvas();
		Iterator<?> iterator = set.iterator(); // allows for set modification during iteration
		double x = this.mGroundX - this.mScaleXFactor;

		for (int i = 0; i < set.size(); i++) {
			if (iterator.hasNext()) {
				set.recordDataSet();
				Object o = iterator.next();
				x = i * this.mScaleXFactor;
				double y = this.mGroundY;
				double value = Double.NaN;

				if (set.isNumber(o)) {
					value = set.parseValue(o);
					y -= value * this.mScaleYFactor;
				} else // if not a number
					y = i * this.mScaleYFactor;

				if (this.mDataPeekListener != null)
					this.mDataPeekListener.onPeak(i, o);

				this.drawAt(x, y, this.mScaleXFactor, this.mCanvas.getHeight() - y,
						DEFAULT_BLOCK_COLOR, null); // draws the specific block to scale with its
													// value
				this.mContext.strokeRect(x, y, this.mScaleXFactor, this.mCanvas.getHeight() - y); // strokes
																									// an
																									// outline
																									// for
																									// block

				// TODO: Render text;
				// this.drawUI();

			}
		}
	}

	/**
	 * Handles the textual UI This is done not through the canvas but through FXML
	 * elements using a Label object
	 */
	public void handleUI() {
		this.setTitle(this.getPrefixtitle() + " Visualization: \tComparrisons: "
				+ super.getWorkingDataSet().getAmountOfComparrisons());
		this.mStage.setTitle(this.getTitle());
		;
	}

	/**
	 * Clears the entire canvas
	 */
	public void clearCanvas() {
		this.mContext.clearRect(0, 0, this.mCanvas.getWidth(), this.mCanvas.getHeight());
	}

	/**
	 * Draws a pointer on the canvass using the graphics context associated with the
	 * canvas.
	 * 
	 * @param Data set
	 */
	@Override
	public void drawPointer(DataSet<?> set) {
		// System.out.println("Drawing pointer");
		int index = set.getPointerInfo().getPointerPosition();

		if (index < set.size() && index >= 0) {
			@SuppressWarnings("rawtypes")
			double value = set.parseValue((Comparable) set.get(index));
			double x = (index * this.mScaleXFactor);
			double y = this.mGroundY - this.mScaleYFactor * value;
			double h = this.mCanvas.getHeight() - y;
			System.out.println(x + "," + y + "," + h);
			this.mContext.setFill(DEFUALT_POINTER_BLOCK_COLOR);
			this.mContext.clearRect(x, y, this.mScaleXFactor, h);
			this.mContext.fillRect(x, y, this.mScaleXFactor, h);
			this.mContext.setFill(DEFAULT_BLOCK_COLOR);
		} else {
			System.out.println(this.getClass().getName() + ": " + index);
		}

	}

	/**
	 * Draws on canvas at specific location with A color deriving from the
	 * interface: Paint
	 * 
	 * @param xLocation
	 * @param yLocation
	 * @param width
	 * @param height
	 * @param color
	 * @param previousColor
	 */
	public void drawAt(double x, double y, double w, double h, Color color, Color previousColor) {
		if (color == null) // error by programmer
			color = DEFAULT_BLOCK_COLOR;

		this.mContext.setFill(color);
		this.mContext.fillRect(x, y, w, h);

		if (previousColor != null) // does not want to revert back to color
			this.mContext.setFill(previousColor);
	}

	/**
	 * The animation that plays when the visualiztion is finished wether it is a
	 * sorting manipulation ro just a real team visualtion
	 */
	@Override
	public void onFinishedAnimation() {
		GraphicsContext gc = this.mContext;
		System.out.println("finished animation called");
		DataSet<Comparable> set = getWorkingDataSet();
		Iterator<?> iterator = set.iterator(); // allows for set modification during iteration
		set.getPointerInfo().setPointerPosition(0);

		final double tickDuration = 10.0;
		final double duration = 0.02; // seconds per index
		final int totalCycles = (int) (set.size() * (1000 / tickDuration) * duration);

		final int cyclesPerIndex = totalCycles / set.size();
		Timeline oneSec = new Timeline(
				new KeyFrame(Duration.millis(tickDuration), new EventHandler<ActionEvent>() {

					double x = 0.0; // current x to draw at
					double y = 0.0; // current y to draw at
					int p = 0; // current index of the dataset array
					int cycles = 0; // current count of cycles
					double value = Double.NaN; // value to be used to scale the ui elements
					double a = mScaleXFactor / cyclesPerIndex;

					@Override
					public void handle(ActionEvent event) {
						if (cycles % cyclesPerIndex == 0) {
							if (iterator.hasNext()) {
								p = set.getPointerInfo().getPointerPosition();
								Object o = iterator.next();
								x = (p * mScaleXFactor);
								y = mGroundY;
								if (set.isNumber(o)) {
									value = set.parseValue(o);
									y -= value * mScaleYFactor;
								} else // if not a number
									y = p * mScaleYFactor;

								gc.strokeRect(x, y, a, mCanvas.getHeight() - y); // strokes outline
								set.getPointerInfo().setPointerPosition(p + 1);
							}
						}

						drawAt(x, y, a, mCanvas.getHeight() - y, Color.GREEN, null); // draws the
																						// specific
																						// block to
																						// scale
																						// with its
																						// value
						x += a;
						cycles++;

					}
				}));
		oneSec.setCycleCount(totalCycles);
		oneSec.play();
		System.out.println(this.getClass().getName() + ": " + oneSec.getCycleCount());

	}

	/**
	 * Called on first render call
	 */
	@Override
	public void onStart() {
		
		this.mStarted = true;
		this.render(getWorkingDataSet());

	}

	@Override
	public void onPause() {
		this.mPaused = true;
		this.mRunning = false;

	}

	@Override
	public void onStop() {
		this.mStopped = true;
		this.mRunning = false;
	}

	@Override
	public void onResume() {
		this.mStopped = false;
		this.mPaused = false;
		this.mRunning = true;
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return this.mRunning;

	}

	@Override
	public void onFinished() {
		System.out.println("On finished clalled");
		this.onFinishedAnimation();
		String str = this.getTitle();
		str += " Finished";
		this.setTitle(str);

	}

	/**
	 * called when a modification to the list has occurred. Depending on the values
	 * of the parameters interpretation can be made about what was modified. Data
	 * moved, replaced , add, ect.
	 * 
	 * @param oldObj
	 * @param newObj
	 * @param oldIndex
	 * @param          newIndex;
	 */
	@Override
	public void onDataChanged(Object oldObj, Object newObj, int oldIndex, int newIndex) {
		if (oldObj == null && newObj != null) // new data added
		{
			if (oldIndex + 1 == newIndex) // data added
			{
				// add implementation
			}

		} else if (oldObj != null && newObj == null) // data removed
		{

		}

		// TODO : Finish method

	}

	@Override
	public void onDataAdded(Object newObj, int index, boolean sizeIncreased) {
		// TODO : Finish method

	}

	@Override
	public void onDataRemoved(Object oldObj, int index, boolean sizeDecreased) {

		// TODO : Finish method
	}

	@Override
	public void onDataMoved(Object obj, int oldIndex, int newIndex) {

		// TODO : Finish method
	}

	public void registerOnDataPeekCallback(DataPeekListener callback) {
		this.mDataPeekListener = callback;
	}

	/**
	 * @return the mScaleXFactor
	 */
	public double getScaleXFactor() {
		return mScaleXFactor;
	}

	/**
	 * @param mScaleXFactor the mScaleXFactor to set
	 */
	public void setScaleXFactor(double scaleXFactor) {
		this.mScaleXFactor = scaleXFactor;
	}

	/**
	 * @return the mReady
	 */
	public boolean isReady() {
		return mReady;
	}

	@Override
	public boolean isInitialized() {
		return this.mInitialized;
	}

	@Override
	public boolean isStopped() {
		return this.mStopped;
	}

	@Override
	public boolean isPaused() {
		return this.mPaused;
	}

	/**
	 * @param mReady the mReady to set
	 */
	public void setReady(boolean ready) {
		this.mReady = ready;
	}

	public String getPrefixtitle() {
		return this.mPrefixTitle;
	}

	public void setPrefixTitle(String value) {
		this.mPrefixTitle = value;
	}

	/**
	 * @return the mTitle
	 */
	public String getTitle() {
		return mTitle;
	}

	/**
	 * @param mTitle the mTitle to set
	 */
	public void setTitle(String title) {
		this.mTitle = title;
		if (this.mStage != null)
			this.mStage.setTitle(this.getTitle());
		// this.drawTitle();
	}

	/**
	 * @return the mStage
	 */
	public Stage getStage() {
		return mStage;
	}

	/**
	 * @return the mScene
	 */
	public Scene getScene() {
		return mScene;
	}

	/**
	 * @return the mCanvas
	 */
	public Canvas getCanvas() {
		return mCanvas;
	}

	/**
	 * @return the mContext
	 */
	public GraphicsContext getContext() {
		return mContext;
	}

	/**
	 * @return the mGroundX
	 */
	public double getGroundX() {
		return mGroundX;
	}

	/**
	 * @return the mGroundY
	 */
	public double getGroundY() {
		return mGroundY;
	}

	/**
	 * @return the mScaleYFactor
	 */
	public double getScaleYFactor() {
		return mScaleYFactor;
	}

	/**
	 * @return the dEFAULT_BLOCK_COLOR
	 */
	public Color getDEFAULT_BLOCK_COLOR() {
		return DEFAULT_BLOCK_COLOR;
	}

	/**
	 * @return the dEFAULT_BLOCK_STROKE_COLOR
	 */
	public Color getDEFAULT_BLOCK_STROKE_COLOR() {
		return DEFAULT_BLOCK_STROKE_COLOR;
	}

	/**
	 * @return the dEFUALT_POINTER_BLOCK_COLOR
	 */
	public Color getDEFUALT_POINTER_BLOCK_COLOR() {
		return DEFUALT_POINTER_BLOCK_COLOR;
	}

	/**
	 * @return the mAX_SCALE_X
	 */
	public double getMAX_SCALE_X() {
		return MAX_SCALE_X;
	}

	/**
	 * @return the mAX_SCLAE_Y
	 */
	public double getMAX_SCLAE_Y() {
		return MAX_SCLAE_Y;
	}

}
