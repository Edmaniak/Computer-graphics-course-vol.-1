package renderers;
import java.awt.Color;

import objects.Vertex2D;
import gui.Canvas;

public class CircleRenderer extends Renderer {

	public CircleRenderer(Canvas canvas) {
		super(canvas);
	}
	
	public void render(Vertex2D center, int radius, Color color) {
		int x = 0;
		int y = radius;
		int d = 1 - radius;
		// first point (0 r)
		drawSymetrically(center.x, center.y, x, y, color);
		while (x < y) {
			x++;
			if (d < 0) {
				d += 2 * x + 1;
			} else {
				y--;
				d += 2 * (x - y) + 1;
			}
			drawSymetrically(center.x, center.y, x, y, color);
		}
	}

	private void drawSymetrically(int xcenter, int ycenter, int x, int y, Color color) {
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
