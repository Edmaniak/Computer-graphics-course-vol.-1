package cz.uhk.pgrf.renderers;

import java.awt.Color;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;

public class DotRenderer extends Renderer {

	public DotRenderer(Canvas canvas, Color c) {
		super(canvas, c);
	}

	public void render(Vector2D position) {
		myCanvas.putPixel(position, color);
	}

}
