package renderers;

import java.awt.Color;

import app.Vertex2D;
import gui.Canvas;

public abstract class Renderer {

	protected final Canvas myCanvas;

	public Renderer(Canvas canvas) {
		myCanvas = canvas;
	}


}
