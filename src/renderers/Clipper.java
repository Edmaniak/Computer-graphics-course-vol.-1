package renderers;

import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;

import java.util.ArrayList;
import java.util.List;

public class Clipper {

    private Polygon clippingArea;

    public Clipper(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }
    
    public Polygon clip(Polygon in) {
        Polygon newPolygon = new Polygon();
        List<Edge> edges = new ArrayList<>(clippingArea.getEdges());
        for (Edge edge : edges) {
            for (Edge edgePol : in.getEdges()) {
                Vertex2D v1 = new Vertex2D(edgePol.getOrigin());
                Vertex2D v2 = new Vertex2D(edgePol.getEnd());
                if (edge.isInside(v1.x, v1.y)) {
                    if (!edge.isInside(v2.x, v2.y)) {
                        Vertex2D inter = edge.getIntersection(edgePol);
                        newPolygon.addPoint(inter);
                    }
                } else {
                    if (edge.isInside(v2.x, v2.y)) {
                        Vertex2D inter = edge.getIntersection(edgePol);
                        newPolygon.addPoint(inter);
                    }

                }
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
