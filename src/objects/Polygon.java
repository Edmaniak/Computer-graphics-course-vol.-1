package objects;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private List<Vertex2D> points;
    private List<Edge> edges;

    public Polygon() {
        points = new ArrayList<>();
        edges = new ArrayList<>();
    }

    // Method automatically creates edges when creating polygon
    public void addPoint(Vertex2D point) {

        if (points.size() == 1)
            edges.add(new Edge(getFirstPoint(), point).orientedEdge());

        // Removing the inner line from edges when it is not a triangle
        if (points.size() > 2)
            edges.remove(edges.size() - 1);

        if (points.size() > 1) {
            edges.add(new Edge(points.get(points.size() - 2), point).orientedEdge());
            edges.add(new Edge(point, getLastPoint()).orientedEdge());
        }

        points.add(point);
    }

    public int size() {
        return points.size();
    }

    public Vertex2D getPoint(int i) {
        return points.get(i);
    }

    public Vertex2D getLastPoint() {
        return points.get(points.size() - 1);
    }

    public Vertex2D getFirstPoint() {
        return points.get(0);
    }

    public List<Vertex2D> getPoints() {
        return points;
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
