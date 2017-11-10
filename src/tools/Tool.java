package tools;

import gui.Canvas;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class Tool {

	private Color color = Color.white;
	private MouseAdapter clickHandler;
	private MouseAdapter motionHandler;
	protected final Canvas myCanvas;
	public final Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	protected String instruction;

	public Tool(Canvas canvas, Color color) {
		myCanvas = canvas;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void doAfterSwitch();

	public abstract void clear();


	public MouseAdapter getMotionHandler() {
		return motionHandler;
	}

	public void setMotionHandler(MouseAdapter motionHandler) {
		this.motionHandler = motionHandler;
	}

	public MouseAdapter getClickHandler() {
		return clickHandler;
	}

	public void defineClickHandler(MouseAdapter clickHandler) {
		this.clickHandler = clickHandler;
	}
	
	public String getInstruction() {
		return instruction;
	}
}


