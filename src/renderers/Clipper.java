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

    public void clip(Polygon subject) {
        List<Edge> edges = clippingArea.getEdges();
        List<Vertex2D> outputPolygon = new ArrayList<>(subject.getPoints());

        for(Edge edge : edges) {
            outputPolygon = new ArrayList<>();
            Vertex2D v1 = subject.getLastPoint();
            for (Vertex2D v2 : subject.getPoints()) {
                Edge newEdge = new Edge(v1, v2);
                if (edge.isInside(v2.x, v2.y)) {
                    if (!edge.isInside(v1.x, v1.y)) {
                        Vertex2D inter = edge.getIntersection(newEdge);
                        outputPolygon.add(inter);
                    }
                    outputPolygon.add(v2);

                } else {
                    if (edge.isInside(v1.x, v1.y)) {
                        Vertex2D inter = edge.getIntersection(newEdge);
                        outputPolygon.add(inter);
                    }
                }
                v1 = v2;
            }
        }

        /*
        for(Edge edge : edges) {

            for (Edge edg : inEdges) {
                if (edge.isInside(edg.getOrigin().x, edg.getOrigin().y)) {
                    if (!edge.isInside(edg.getEnd().x, edg.getEnd().y)) {
                        Edge newEdge = new Edge(edg.getOrigin(),edg.getEnd());
                        Vertex2D inter =  new Vertex2D(edge.getIntersection(newEdge));
                        newPolygon.addPoint(inter);
                    }
                    newPolygon.addPoint(new Vertex2D(edg.getEnd()));
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

        return newPolygon
        */
    }

    public void setClippingArea(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }

    public Polygon getClippingArea() {
        return clippingArea;
    }
}
