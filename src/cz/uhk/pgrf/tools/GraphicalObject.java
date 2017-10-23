package cz.uhk.pgrf.tools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.Renderer;

public abstract class GraphicalObject {

	protected Color color = Color.white;
	private MouseAdapter clickHandler;
	private MouseAdapter motionHandler;
	protected final Canvas myCanvas;
	public Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	protected Renderer myRenderer;

	public GraphicalObject(Canvas canvas, Color color) {
		myCanvas = canvas;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.myRenderer.setColor(color);
	}

	public abstract void clear();

	protected boolean canDrawAt(Vector2D point) {
		if (point.x >= 0 && point.x < myCanvas.getWidth() && point.y >= 0 && point.y < myCanvas.getHeight()) {
			return true;}
		clear();
		return false;
	}

	public MouseAdapter getMotionHandler() {
		return motionHandler;
	}

	public void setMotionHandler(MouseAdapter motionHandler) {
		this.motionHandler = motionHandler;
	}

	public MouseAdapter getClickHandler() {
		return clickHandler;
	}

	public void setClickHandler(MouseAdapter clickHandler) {
		this.clickHandler = clickHandler;
	}

}
