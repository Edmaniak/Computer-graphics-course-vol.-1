package renderers;

import gui.Canvas;
import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;

import java.util.List;

public class Clipper extends Renderer {

    private Polygon clippingArea;

    public Clipper(Canvas canvas) {
        super(canvas);
    }

    public Polygon clip(Polygon in) {
        List<Edge> edges = clippingArea.getEdges();
        List<Vertex2D> inPolygonPoints = in.getPoints();
        Polygon newPolygon = new Polygon();

        for(Edge edge : edges) {
            newPolygon.clear();
            Vertex2D v1 = in.getLastPoint();
            for (Vertex2D v2 : inPolygonPoints) {
                if (edge.isInside(new Vertex2D(v2))) {
                    if (!edge.isInside(new Vertex2D(v1))) {
                        Edge newEdge = new Edge(new Vertex2D(v1), new Vertex2D(v2)).orientedEdge();
                        newPolygon.addPoint(edge.getIntersection(newEdge));
                    }
                    newPolygon.addPoint(new Vertex2D(v2));
                } else {
                    if (edge.isInside(new Vertex2D(v1))) {
                        Edge newEdge = new Edge(new Vertex2D(v1), new Vertex2D(v2));
                        newPolygon.addPoint(edge.getIntersection(newEdge));
                    }
                    v1 = v2;
                }
            }
        }
        return newPolygon;
    }

}
