package tools;

import gui.Canvas;
import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;
import renderers.LineRenderer;
import renderers.PolygonRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PolygonTool extends Tool {

	private final LineRenderer lr;
	private final Polygon polygon;
	private final PolygonRenderer pr;

	public PolygonTool(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the lines of desired polygon.";
		lr = new LineRenderer(canvas);
		pr = new PolygonRenderer(canvas);
		polygon = new Polygon();
		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (polygon.size() == 0) {
					Vertex2D point = new Vertex2D(e.getX(), e.getY());
					polygon.addPoint(point);
				}
				if (polygon.size() > 2) {
					Edge ed = polygon.getEdges().get(0);
					//
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Vertex2D point = new Vertex2D(e.getX(), e.getY());
				polygon.addPoint(point);
				myCanvas.repaint();
				myCanvas.drawInto();

			}
		});

		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				myCanvas.mix();
				// Line
				if (polygon.size() == 1) {
					Vertex2D newP = new Vertex2D(e.getX(), e.getY());
					Vertex2D p2 = new Vertex2D(polygon.getPoint(polygon.size() - 1));
					lr.render(newP, p2, color);
				}
				/*
				// Triangle or polygon
				if (polygon.size() > 1) {

					Vertex2D newP = new Vertex2D(e.getX(), e.getY());
					Vertex2D p1 = new Vertex2D(polygon.getPoint(polygon.size() - 1));
					Vertex2D p2 = new Vertex2D(polygon.getPoint(polygon.size() - 2));

					// Erasing connecting line
					if (polygon.size() > 2)
						lr.renderInvisible(new Vertex2D(p1), new Vertex2D(p2));

					lr.render(new Vertex2D(newP), new Vertex2D(p1),color);
					lr.render(new Vertex2D(newP), new Vertex2D(p2),color);

				}
*/
				myCanvas.repaint();
			}
		});

	}

	@Override
	public void doAfterSwitch() {
		myCanvas.addToPolygons(polygon);
	}

	@Override
	public void clear() {

	}
}
