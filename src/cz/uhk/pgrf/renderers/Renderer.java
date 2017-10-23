package cz.uhk.pgrf.renderers;
import java.awt.Color;

import cz.uhk.pgrf.gui.Canvas;

public abstract class Renderer {
	
	protected Color color = Color.white;
	protected final Canvas myCanvas;
	
	public Renderer(Canvas canvas, Color c) {
		myCanvas = canvas;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
