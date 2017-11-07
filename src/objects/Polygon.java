package objects;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private List<Vertex2D> points;
    private List<Edge> edges;

    public Polygon(List<Vertex2D> points) {
        this.points = points;
    }

    public Polygon() {
        points = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addPoint(Vertex2D point) {
        if (points.size() == 1) {
            Edge edge = new Edge(getFirstPoint(), point).orientedEdge();
            System.out.println(edge);
            edges.add(edge);
        }
        if (points.size() > 1) {
            Edge e1 = new Edge(point, getLastPoint()).orientedEdge();
            System.out.println(e1);
            Edge e2 = new Edge(points.get(points.size()-2), point);
            System.out.println(e2);
            edges.add(e1);
            edges.add(e2);
        }
        points.add(point);
    }

    public int size() {
        return points.size();
    }

    public Vertex2D getBottomPoint(int i) {
        return points.get(i);
    }

    public Vertex2D getLastPoint() {
        return points.get(points.size() - 1);
    }

    public Vertex2D getFirstPoint() {
        return points.get(0);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Vertex2D getTopPoint() {
        Vertex2D yMax = getFirstPoint();
        for (Vertex2D point : points){
            if(point.y > yMax.y)
                yMax = point;
        }
        return yMax;
    }

    public Vertex2D getBottomPoint() {
        Vertex2D yMin = getFirstPoint();
        for (Vertex2D point : points){
            if(point.y < yMin.y)
                yMin = point;
        }
        return yMin;
    }

    @Override
    public String toString() {
        String polygon = "POINTS: ";
        for (Vertex2D point : points)
            polygon += point + " ";
        return polygon;
    }
}
