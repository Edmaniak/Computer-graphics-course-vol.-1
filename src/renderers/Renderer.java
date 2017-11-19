package renderers;

import gui.Canvas;

import java.awt.*;

public abstract class Renderer {

	protected final Canvas myCanvas;
	protected Color color;

	public Renderer(Canvas canvas) {
		myCanvas = canvas;
	}


}
