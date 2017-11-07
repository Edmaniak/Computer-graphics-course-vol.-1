package renderers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import app.Vertex2D;
import gui.Canvas;

public class ArcRenderer extends Renderer {

	private final List<Vertex2D> points;
	private final LineRenderer lr;

	public ArcRenderer(Canvas canvas) {
		super(canvas);
		points = new ArrayList<>();
		lr = new LineRenderer(canvas);
	}

	public void render(int radius, Vertex2D center, Vertex2D initRPoint, Vertex2D rPoint, Color color) {
		double angle = Vertex2D.angle(initRPoint, rPoint);
		double alfa = (double) 10 / radius;
		double y = -initRPoint.y;
		double x = initRPoint.x;
		double cos = Math.cos(alfa);
		double sin = Math.sin(alfa);
		double n = (2 * Math.PI) / alfa;
		double limit = angle / (360 / n);
		double control = 0;
		// Rendering points
		for (int k = 0; k < (int) limit; k++) {
			control += alfa;
			double xn = (x * cos - y * sin);
			double yn = (x * sin + y * cos);
			if (control <= angle) {
				points.add(new Vertex2D((int) (center.x + xn), (int) (center.y + yn)));
			}
			x = xn;
			y = yn;
			myCanvas.putPixel((int) (center.x + xn), (int) (center.y + yn), color);
		}
		connectPoints(color);
		points.clear();
	}

	private void connectPoints(Color color) {
		for (int i = 0; i < points.size() - 1; i++) {
			lr.render(new Vertex2D(points.get(i)), new Vertex2D(points.get(i + 1)), color);
		}

	}
	
	public LineRenderer getLr() {
		return lr;
	}


}
