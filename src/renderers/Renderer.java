package renderers;

import gui.Canvas;

public abstract class Renderer {

	protected final Canvas myCanvas;

	public Renderer(Canvas canvas) {
		myCanvas = canvas;
	}


}
