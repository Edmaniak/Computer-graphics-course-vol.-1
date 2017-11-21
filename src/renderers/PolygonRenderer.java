package renderers;


import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;
import renderers.line.LineRenderer;

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
        for (int i = 0; i < points.size(); i++) {
            lr.render(new Vertex2D(points.get(i)), new Vertex2D(points.get((i + 1) % points.size())), color);
        }
    }


}
