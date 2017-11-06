package tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import app.Vertex2D;
import gui.Canvas;
import renderers.LineRenderer;

public class Polygon extends Tool {

	private Vertex2D point;
	private final LineRenderer lr;

	public Polygon(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the lines of desired polygon.";
		lr = new LineRenderer(canvas, color);
		setMainRenderer(lr, color);
		points = new ArrayList<>();

		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (points.size() == 0) {
					point = new Vertex2D(e.getX(), e.getY());
					points.add(point);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				points.add(new Vertex2D(e.getX(), e.getY()));
			}
		});

		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				myCanvas.mix();
				if (points.size() == 1) {
					Vertex2D newP = new Vertex2D(e.getX(), e.getY());
					Vertex2D p2 = new Vertex2D(points.get(points.size() - 1));
					lr.render(newP, p2);
				} else {
					Vertex2D newP1 = new Vertex2D(e.getX(), e.getY());
					Vertex2D newP2 = new Vertex2D(e.getX(), e.getY());
					Vertex2D p1 = new Vertex2D(points.get(points.size() - 1));
					Vertex2D p2 = new Vertex2D(points.get(points.size() - 2));

					lr.render(newP1, p1);
					lr.render(newP2, p2);

				}
				myCanvas.repaint();
			}
		});
	}

	@Override
	public void clear() {
		point = null;
		points.clear();
	}

}
