package renderers;

import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;

import java.util.List;

public class Clipper {

    private Polygon clippingArea;

    public Clipper(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }

    public Polygon clip(Polygon in) {
        List<Edge> edges = clippingArea.getEdges();
        List<Vertex2D> inPolygonPoints = in.getPoints();
        Polygon newPolygon = new Polygon();

        for(Edge edge : edges) {
            Vertex2D v1 = in.getLastPoint();
            for (Vertex2D v2 : inPolygonPoints) {
                if (edge.isInside(v2.x, v2.y)) {
                    if (!edge.isInside(v1.x, v1.y)) {
                        Edge newEdge = new Edge(v1,v2);
                        Vertex2D inter =  new Vertex2D(edge.getIntersection(newEdge));
                        newPolygon.addPoint(inter);
                    }
                    newPolygon.addPoint(new Vertex2D(v2));
                } else {
                    if (edge.isInside(v1.x, v1.y)) {
                        Edge newEdge = new Edge(v1, v2);
                        Vertex2D inter =  edge.getIntersection(newEdge);
                        newPolygon.addPoint(inter);
                    }
                }
                v1 = v2;
            }
        }
        return newPolygon;
    }

    public void setClippingArea(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }

    public Polygon getClippingArea() {
        return clippingArea;
    }
}
