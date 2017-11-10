package renderers;


import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;

import java.awt.*;
import java.util.List;

public class PolygonRenderer extends Renderer {

    private LineRenderer lr;

    public PolygonRenderer(Canvas canvas) {
        super(canvas);
        lr = new LineRenderer(canvas);
    }

    public void render(Polygon polygon, Color color) {
        List<Vertex2D> points = polygon.getPoints();
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            Vertex2D origin = new Vertex2D(points.get(i));
            Vertex2D end = new Vertex2D(points.get((i + 1) % points.size())); System.out.println((i + 1) % points.size());
            lr.render(origin, end, color);
        }
    }


}
