package cz.uhk.pgrf.tools;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.ArcRenderer;
import cz.uhk.pgrf.renderers.CircleRenderer;
import cz.uhk.pgrf.renderers.LineRenderer;

public class Arc extends GraphicalObject {

	Vector2D center;
	Vector2D radPoint;
	Vector2D initRadPoint;
	CircleRenderer cr;
	LineRenderer lr;
	int radius;

	public Arc(Canvas canvas, Color color) {
		super(canvas, color);
		myRenderer = new ArcRenderer(canvas, color);
		cr = new CircleRenderer(canvas, Color.RED);
		lr = new LineRenderer(canvas, Color.RED);
		setClickHandler(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (center == null) {
					center = new Vector2D(e.getX(), e.getY());
				} else if (initRadPoint == null) {
					initRadPoint = new Vector2D(e.getX(), e.getY());
					radius = (int) sqrt(pow((initRadPoint.x - center.x), 2) + pow((initRadPoint.y - center.y), 2));
					lr.render(new Vector2D(center), new Vector2D(initRadPoint));
					((ArcRenderer) myRenderer).render(radius, center, initRadPoint, initRadPoint);
					myCanvas.repaint();
				}
			}

		});
		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (center != null && initRadPoint != null && radius > 0
						&& canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					myCanvas.mix();
					radPoint = new Vector2D(e.getX(), e.getY());
					Vector2D init = new Vector2D(initRadPoint.x - center.x, center.y - initRadPoint.y);
					System.out.println(init);
					Vector2D newV = new Vector2D(radPoint.x - center.x, center.y - radPoint.y);
					System.out.println(newV);
					cr.render(radPoint, 5);
					cr.render(initRadPoint, 5);
					lr.render(new Vector2D(center), new Vector2D(radPoint));
					((ArcRenderer) myRenderer).render(radius, center, init, newV);
					myCanvas.repaint();
				}
			}
		});
	}

	@Override
	public void clear() {
		center = null;
		radPoint = null;

	}

}
