package cz.uhk.pgrf.renderers;
import java.awt.Color;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;

public class CircleRenderer extends Renderer {

	public CircleRenderer(Canvas canvas, Color c) {
		super(canvas, c);
	}
	
	public void render(Vector2D center, int radius) {
		int x = 0;
		int y = radius;
		int d = 1 - radius;
		drawSymetrically(center.x, center.y, x, y, 90);
		while (x < y) {
			x++;
			if (d < 0) {
				d += 2 * x + 1;
			} else {
				y--;
				d += 2 * (x - y) + 1;
			}
			drawSymetrically(center.x, center.y, x, y, 90);
		}
	}

	private void drawSymetrically(int xcenter, int ycenter, int x, int y, int angleNeeded) {
		// 1 (0 - 45)
		myCanvas.putPixel(xcenter + y, ycenter - x, color);
		// 2
		myCanvas.putPixel(xcenter + x, ycenter - y, color);
		// 3
		myCanvas.putPixel(xcenter - x, ycenter - y, color);
		// 4
		myCanvas.putPixel(xcenter - y, ycenter - x, color);
		// 5
		myCanvas.putPixel(xcenter - y, ycenter + x, color);
		// 6
		myCanvas.putPixel(xcenter - x, ycenter + y, color);
		// 7
		myCanvas.putPixel(xcenter + x, ycenter + y, color);
		// 8
		myCanvas.putPixel(xcenter + y, ycenter + x, color);
	}

}
