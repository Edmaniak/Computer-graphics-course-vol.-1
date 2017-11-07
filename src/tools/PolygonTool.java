package tools;

import objects.Vertex2D;
import gui.Canvas;
import objects.Polygon;
import renderers.LineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PolygonTool extends Tool {

	private final LineRenderer lr;
	private final Polygon polygon;

	public PolygonTool(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the lines of desired polygon.";
		lr = new LineRenderer(canvas);
		polygon = new Polygon();
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
				myCanvas.mix();

				myCanvas.repaint();
				myCanvas.drawInto();
				polygon.addPoint(new Vertex2D(e.getX(), e.getY()));
			}
		});

		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

				myCanvas.mix();
				// Line
				if (polygon.size() == 1) {
					Vertex2D newP = new Vertex2D(e.getX(), e.getY());
					Vertex2D p2 = new Vertex2D(polygon.getBottomPoint(polygon.size() - 1));
					lr.render(newP, p2, color);
				}

				// Triangle or polygon
				if (polygon.size() > 1) {

					Vertex2D newP = new Vertex2D(e.getX(), e.getY());
					Vertex2D p1 = new Vertex2D(polygon.getBottomPoint(polygon.size() - 1));
					Vertex2D p2 = new Vertex2D(polygon.getBottomPoint(polygon.size() - 2));

					// Erasing connecting line
					if (polygon.size() > 2)
						lr.renderInvisible(new Vertex2D(p1), new Vertex2D(p2));

					lr.render(new Vertex2D(newP), new Vertex2D(p1),color);
					lr.render(new Vertex2D(newP), new Vertex2D(p2),color);

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
