package cz.uhk.pgrf.renderers;

import java.awt.Color;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;

public class ArcRenderer extends Renderer {

	public ArcRenderer(Canvas canvas, Color c) {
		super(canvas, c);
		// TODO Auto-generated constructor stub
	}

	public void render(int radius, Vector2D center, Vector2D initRPoint, Vector2D rPoint) {
		double angle = Vector2D.angle(initRPoint, rPoint);
		System.out.println(angle);
		double alfa = (double) 10 / radius;
		double y = -initRPoint.y;
		double x = initRPoint.x;
		double cos = Math.cos(alfa);
		double sin = Math.sin(alfa);
		double n = (double) ((2 * Math.PI) / alfa);
		double limit = angle / (360 / n);
		for (int k = 0; k < limit; k++) {
			double xn = (x * cos - y * sin);
			double yn = (x * sin + y * cos);
			x = xn;
			y = yn;
			myCanvas.putPixel((int) (center.x + xn), (int) (center.y + yn), color);
		}

	}
}
