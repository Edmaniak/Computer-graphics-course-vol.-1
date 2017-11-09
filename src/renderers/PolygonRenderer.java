package renderers;


import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;

import java.awt.*;

public class PolygonRenderer extends Renderer {

    private LineRenderer lr;

    public PolygonRenderer(Canvas canvas) {
        super(canvas);
        lr = new LineRenderer(canvas);
    }

    public void renderPolygon(Polygon polygon, Color color) {
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            if (polygon.size() == 2) {
                Vertex2D newP = polygon.getPoint(0);
                Vertex2D p2 = polygon.getPoint(1);
                lr.render(newP, p2, color);
            }

            // Triangle or polygon
            if (polygon.size() > 2) {

                Vertex2D newP = polygon.getLastPoint();
                Vertex2D p1 = polygon.getPoint(polygon.size() - 1);
                Vertex2D p2 = polygon.getPoint(polygon.size() - 2);

                // Erasing connecting line when it is square
                if (polygon.size() > 3)
                    lr.renderInvisible(new Vertex2D(p1), new Vertex2D(p2));

                lr.render(new Vertex2D(newP), new Vertex2D(p1), color);
                lr.render(new Vertex2D(newP), new Vertex2D(p2), color);

            }
        }
    }


}
