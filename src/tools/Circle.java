package tools;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import renderers.CircleRenderer;

public class Circle extends GraphicalObject {

	private Vertex2D center;
	private Vertex2D radPoint;
	private int radius;
	private final CircleRenderer cr;

	public Circle(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the mouse";
		cr = new CircleRenderer(canvas, color);
		setMainRenderer(cr, color);
		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (center == null)
					center = new Vertex2D(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();

			}
		});

		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				radPoint = new Vertex2D(e.getX(), e.getY());
				radius = (int) sqrt(pow((radPoint.x - center.x), 2) + pow((radPoint.y - center.y), 2));
				myCanvas.mix();
				cr.render(center, radius);
				myCanvas.repaint();

			}
		});
	}

	@Override
	public void clear() {
		center = null;
		radPoint = null;
		radius = 0;
	}


}