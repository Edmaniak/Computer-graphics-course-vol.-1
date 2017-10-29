package renderers;

import java.awt.Color;

import gui.Canvas;

public abstract class Renderer {

	protected Color color = Color.white;
	protected final Canvas myCanvas;

	public Renderer(Canvas canvas, Color c) {
		myCanvas = canvas;
		this.color = c;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
