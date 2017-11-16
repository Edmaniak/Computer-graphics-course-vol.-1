package renderers;


import gui.Canvas;
import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;
import renderers.line.DashLineRenderer;
import renderers.line.LineRenderer;

import java.awt.*;
import java.util.List;

public class PolygonRenderer extends Renderer {

    private LineRenderer lr;

    public PolygonRenderer(Canvas canvas) {
        super(canvas);
        lr = new LineRenderer(canvas);
    }

    public PolygonRenderer(Canvas canvas, int space) {
        super(canvas);
        lr = new DashLineRenderer(canvas, space);
    }

    public void render(Polygon polygon, Color color) {
        List<Edge> edges = polygon.getEdges();
        for (int i = 0; i < polygon.getEdges().size(); i++) {
            Edge e = edges.get(i);
            lr.render(new Vertex2D(e.getOrigin()), new Vertex2D(e.getEnd()), color);
        }
    }


}
