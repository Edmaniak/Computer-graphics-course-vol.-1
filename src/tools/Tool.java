package tools;

import gui.Canvas;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class Tool implements Switchable {

	protected Color color = Color.white;
	private MouseAdapter clickHandler;
	private MouseAdapter motionHandler;
	protected final Canvas myCanvas;
	public final Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

	public Tool(Canvas canvas, Color color) {
		myCanvas = canvas;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Defines the instruction string that is used by tooltip bar
	 * @return instruction string
	 */
	public abstract String getInstruction();

	/**
	 * Defines what fields should be cleared
	 */
	public abstract void clear();

	/**
	 * @return handler for mouse motion
	 */
	public MouseAdapter getMotionHandler() {
		return motionHandler;
	}

	/**
	 * Allows defining the behaviour related to mouse motion events
	 * @param motionHandler
	 */
	public void defineMotionHandler(MouseAdapter motionHandler) {
		this.motionHandler = motionHandler;
	}

	/**
	 * @return handler for mouse clicking
	 */
	public MouseAdapter getClickHandler() {
		return clickHandler;
	}

	/**
	 * Allows defining the behaviour related to mouse clicking events
	 * @param clickHandler
	 */
	public void defineClickHandler(MouseAdapter clickHandler) {
		this.clickHandler = clickHandler;
	}

	/**
	 * Allows destroying all already defined handlers for redefinition
	 * Mainly for children of Tool
	 */
	protected void destroyHandlers() {
		clickHandler = null;
		motionHandler = null;
	}

}


