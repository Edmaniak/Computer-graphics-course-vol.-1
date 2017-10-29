package renderers;

import java.awt.Color;

import app.Vertex2D;
import gui.Canvas;

public class LineRenderer extends Renderer {

	public LineRenderer(Canvas canvas, Color c) {
		super(canvas, c);
	}

	public void render(Vertex2D o, Vertex2D e) {

		float dx = e.x - o.x;
		float dy = e.y - o.y;

		if (Math.abs(dy) <= Math.abs(dx)) {
			// one point line
			if ((o.x == e.x) && (o.y == e.y))
				myCanvas.putPixel(o.x, o.y, color);
			else if (e.x < o.x)
				Vertex2D.reverse(o, e);

			float k = dy / dx;
			float fy = (float) o.y;

			for (int x = o.x; x <= e.x; x++) {
				int y = Math.round(fy);
				myCanvas.putPixel(x, y, color);
				fy += k;
			}

		} else {
			if (e.y < o.y)
				Vertex2D.reverse(o, e);

			float k = dx / dy;
			float fx = (float) o.x;

			for (int y = o.y; y <= e.y; y++) {
				int x = Math.round(fx);
				myCanvas.putPixel(x, y, color);
				fx += k;
			}
		}
	}
}
