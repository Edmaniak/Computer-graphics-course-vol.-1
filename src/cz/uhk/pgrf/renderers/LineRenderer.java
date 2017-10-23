package cz.uhk.pgrf.renderers;
import java.awt.Color;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;

public class LineRenderer extends Renderer {
	
	public LineRenderer(Canvas canvas, Color c) {
		super(canvas, c);
	}
	
	public void render(Vector2D o, Vector2D e) {

		float dx = e.x - o.x;
		float dy = e.y - o.y;

		if (Math.abs(dy) <= Math.abs(dx)) {
			if (e.x < o.x)
				Vector2D.reverse(o, e);

			float k = (float) dy / dx;
			float fy = (float) o.y;

			for (int x = o.x; x <= e.x; x++) {
				int y = (int) Math.round(fy);
				myCanvas.putPixel(x, y, color);
				fy += k;
			}

		} else {
			if (e.y < o.y)
				Vector2D.reverse(o, e);

			float k = (float) dx / dy;
			float fx = (float) o.x;

			for (int y = o.y; y <= e.y; y++) {
				int x = (int) Math.round(fx);
				myCanvas.putPixel(x, y, color);
				fx += k;
			}
		}
	}
}
