package renderers;

import gui.Canvas;

public abstract class Renderer {

	protected final Canvas myCanvas;

	protected Renderer(Canvas canvas) {
		myCanvas = canvas;
	}

}
