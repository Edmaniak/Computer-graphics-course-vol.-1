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
        if (points.size() >= 3)
            edges.remove(edges.size() - 1);

        if (points.size() >= 2) {
            edges.add(new Edge(points.get(points.size() - 2), point).orientedEdge());
            edges.add(new Edge(point, getLastPoint()).orientedEdge());
        }

        points.add(point);

    }

    public void addPointOnce(Vertex2D point) {
        if (!points.contains(point))
            addPoint(point);
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

    // Method for removing points logically return edges
    // needed by renderer to remove edges in raster
    public List<Edge> removePoint(Vertex2D point) {
        List<Edge> edgesWithPoint = new ArrayList<>();
        if (points.contains(point))
            for (Edge edge : edges)
                if (edge.containsPoint(point))
                    edgesWithPoint.add(edge);
        edges.removeIf(edge -> edge.containsPoint(point));
        points.remove(point);
        List<Vertex2D> pointsToConnect = getOpenPoints();
        if (size() >= 2) {
            Edge newEdge = new Edge(pointsToConnect.get(0), pointsToConnect.get(1));
            edges.add(newEdge);
        }
        return edgesWithPoint;
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

    public List<Vertex2D> getOpenPoints() {
        List<Vertex2D> openPoints = new ArrayList<>(points);
        List<Vertex2D> notOpen = new ArrayList<>();
        int i = 0;
        for (Vertex2D point : points) {
            for (Edge edge : edges) {
                if (edge.containsPointAt(point.x, point.y))
                    i++;
                if (i == 2) {
                    notOpen.add(point);
                    i = 0;
                }
            }
            i = 0;
        }
        openPoints.removeAll(notOpen);
        return openPoints;
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
