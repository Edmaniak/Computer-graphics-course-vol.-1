package tools;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import objects.Polygon;
import renderers.LineRenderer;

public class PolygonTool extends Tool {

	private final LineRenderer lr;
	private final Polygon polygon;

	public PolygonTool(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the lines of desired polygon.";
		lr = new LineRenderer(canvas, color);
		polygon = new Polygon();

		setMainRenderer(lr, color);
		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (polygon.size()== 0) {
					Vertex2D point = new Vertex2D(e.getX(), e.getY());
					polygon.addPoint(point);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				polygon.addPoint(new Vertex2D(e.getX(), e.getY()));
			}
		});

		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				myCanvas.mix();
				if (polygon.size() == 1) {
					Vertex2D newP = new Vertex2D(e.getX(), e.getY());
					Vertex2D p2 = new Vertex2D(polygon.getPoint(polygon.size() - 1));
					lr.render(newP, p2);
				} else {
					Vertex2D newP1 = new Vertex2D(e.getX(), e.getY());
					Vertex2D newP2 = new Vertex2D(e.getX(), e.getY());
					Vertex2D p1 = new Vertex2D(polygon.getPoint(polygon.size() - 1));
					Vertex2D p2 = new Vertex2D(polygon.getPoint(polygon.size() - 2));

					lr.render(newP1, p1);
					lr.render(newP2, p2);

				}
				myCanvas.repaint();
			}
		});

	}

	@Override
	public void doAfterSwitch() {
		myCanvas.addToPolygonLibrary(polygon);
	}

	@Override
	public void clear() {

	}
}
