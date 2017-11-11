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
            edges.add(new Edge(getFirstPoint(), new Vertex2D(point)).orientedEdge());

        // Removing the inner line from edges when it is not a triangle
        if (points.size() >= 3)
            edges.remove(edges.size() - 1);

        if (points.size() >= 2) {
            edges.add(new Edge(new Vertex2D(points.get(points.size() - 2)), new Vertex2D(point)).orientedEdge());
            edges.add(new Edge(new Vertex2D(point), new Vertex2D(getLastPoint())).orientedEdge());
        }

        points.add(new Vertex2D(point));




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

    public void clear() {
        points.clear();
        edges.clear();
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

    public boolean isInside(int x, int y) {
        for (Edge e : edges) {
            System.out.println(e + " " + e.isInside(x, y));
            if (!e.isInside(x, y))
                return false;
        }
        return true;
    }
}
