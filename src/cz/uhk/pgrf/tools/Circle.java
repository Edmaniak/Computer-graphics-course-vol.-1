package cz.uhk.pgrf.tools;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.CircleRenderer;

public class Circle extends GraphicalObject {

	private Vector2D center;
	private Vector2D radPoint;
	private int radius;

	public Circle(Canvas canvas, Color color) {
		super(canvas, color);
		myRenderer = new CircleRenderer(canvas, color);

		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (center == null)
					center = new Vector2D(e.getX(), e.getY());
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
				radPoint = new Vector2D(e.getX(), e.getY());
				radius = (int) sqrt(pow((radPoint.x - center.x), 2) + pow((radPoint.y - center.y), 2));
				myCanvas.mix();
				((CircleRenderer) myRenderer).render(center, radius);
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