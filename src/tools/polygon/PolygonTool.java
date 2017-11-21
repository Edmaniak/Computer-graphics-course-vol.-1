package tools.polygon;

import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;
import renderers.line.LineRenderer;
import renderers.PolygonRenderer;
import tools.Tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PolygonTool extends Tool {

	protected LineRenderer lr;
	protected PolygonRenderer pr;
	protected final Polygon polygon;


	public PolygonTool(Canvas canvas, Color color) {
		super(canvas, color);

		lr = new LineRenderer(canvas);
		pr = new PolygonRenderer(canvas);
		polygon = new Polygon();
		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (polygon.size() == 0) {
					Vertex2D point = new Vertex2D(e.getX(), e.getY());
					polygon.addPoint(point);
					myCanvas.putText(point,e.getX(),e.getY());
					myCanvas.repaint();
					myCanvas.drawInto();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				Vertex2D point = new Vertex2D(e.getX(), e.getY());
				polygon.addPoint(point);
				pr.render(polygon, color);
				myCanvas.putText(point,e.getX(),e.getY());
				myCanvas.repaint();
				myCanvas.drawInto();

			}
		});

		defineMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {

                myCanvas.mix();
                // Line
                if (polygon.size() == 1) {
                    Vertex2D newP = new Vertex2D(e.getX(), e.getY());
                    Vertex2D p2 = new Vertex2D(polygon.getLastPoint());
                    lr.render(newP, p2, color);
                }

                // Triangle or polygon
                if (polygon.size() > 1) {

                    Vertex2D newP = new Vertex2D(e.getX(), e.getY());
                    Vertex2D p1 = new Vertex2D(polygon.getLastPoint());
                    Vertex2D p2 = new Vertex2D(polygon.getPoint(polygon.size() - 2));

                    // Erasing connecting line
                    if (polygon.size() > 2)
                        lr.render(new Vertex2D(p1), new Vertex2D(p2), Color.BLACK);

                    lr.render(new Vertex2D(newP), new Vertex2D(p1), color);
                    lr.render(new Vertex2D(newP), new Vertex2D(p2), color);

                }

                myCanvas.repaint();
			}
		});

	}

    @Override
    public String getInstruction() {
        return "Drag the lines of desired polygon";
    }

    @Override
	public void doAfterSwitchOut() {
		myCanvas.addToPolygons(new Polygon(polygon));
	}

	@Override
	public void doOnSwitchIn() {

	}

	@Override
	public void clear() {

	}


}
