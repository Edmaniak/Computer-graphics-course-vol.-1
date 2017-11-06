package tools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import app.Vertex2D;
import gui.Canvas;
import renderers.Renderer;

public abstract class Tool {

	private Color color = Color.white;
	private MouseAdapter clickHandler;
	private MouseAdapter motionHandler;
	protected final Canvas myCanvas;
	public final Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	protected String instruction;
	// Dulezity atribut pro zmenu informace o barve pro Renderer
	// Jinak ma kazdy potomek vlastni specificky Renderer
	// Main renderer slouzi tedy pouze ke zmene barvy v rendereru
	private Renderer mainRenderer;

	public Tool(Canvas canvas, Color color) {
		myCanvas = canvas;
		this.color = color;
	}
	
	public void setMainRenderer(Renderer renderer,Color color) {
		mainRenderer = renderer;
		setColor(color);
	}

	public void setColor(Color color) {
		this.color = color;
		mainRenderer.setColor(color);
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
