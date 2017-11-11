package renderers;

import gui.Canvas;
import objects.Edge;
import objects.Polygon;

import java.util.List;

public class Clipper extends Renderer {

    private Polygon clippingArea;

    public Clipper(Canvas canvas) {
        super(canvas);
    }

    public Polygon clip(Polygon in) {
        List<Edge> edges = clippingArea.getEdges();
        Polygon newPolygon = new Polygon();
        for(Edge edge : edges) {
            newPolygon.
        }
    }

}
