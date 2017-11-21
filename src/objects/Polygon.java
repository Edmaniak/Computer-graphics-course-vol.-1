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

    public Polygon(Polygon polygon) {
        points = new ArrayList<>(polygon.getPoints());
        edges = new ArrayList<>(polygon.getEdges());
    }

    public Polygon(List<Vertex2D> polyPoints) {
        points = new ArrayList<>(polyPoints);
    }

    /**
     * Method automatically creates edges when creating polygon
     * @param point new point to add
     */
    public void addPoint(Vertex2D point) {

        // Adding points
        if (points.size() < 2)
            points.add(point);
        else
            points.add(points.size() - 1, point);

        // Adding edges
        if (points.size() == 2)
            addEdge(new Edge(getPoint(0), getPoint(1)).orientedEdge());

        // Removing inter-edge when polygon is not a triangle
        if (points.size() > 3)
            edges.remove(edges.size() - 1);

        // Connecting new added point to neighbour points by edges
        if (points.size() > 2) {
            addEdge(new Edge(point, getPoint(points.indexOf(point) - 1)).orientedEdge());
            addEdge(new Edge(point, getPoint(points.indexOf(point) + 1)).orientedEdge());
        }

    }

    private void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int size() {
        return points.size();
    }

    public Vertex2D getPoint(int i) {
        return new Vertex2D(points.get(i));
    }

    public Vertex2D getLastPoint() {
        return new Vertex2D(points.get(points.size() - 1));
    }

    public Vertex2D getFirstPoint() {
        return new Vertex2D(points.get(0));
    }

    /**
     * Method for removing points logically
     * Poznamka: sice by stacilo vymazat pozadi odebrat bod a prekreslit polygon
     * nicmene timto zpusobem muze raster zůstat vicemene neporusen a prekresli se
     * pouze polygon.
     * @param point
     * @return edges needed by renderer to remove edges in raster
     *
     */
    public List<Edge> removePoint(Vertex2D point) {
        List<Edge> edgesWithPoint = new ArrayList<>();
        if (points.contains(point))
            for (Edge edge : edges)
                if (edge.containsPointAt(point.x, point.y))
                    edgesWithPoint.add(edge);
        edges.removeIf(edge -> edge.containsPointAt(point.x, point.y));
        points.remove(point);
        List<Vertex2D> pointsToConnect = getOpenPoints();
        if (size() >= 2) {
            Edge newEdge = new Edge(pointsToConnect.get(0), pointsToConnect.get(1));
            edges.add(newEdge);
        }
        return edgesWithPoint;
    }

    public void setPoints(List<Vertex2D> points) {
        this.points = new ArrayList<>(points);
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
        String output = "POINTS: ";
        for (Vertex2D point : points)
            output += point + " ";
        for(Edge e : edges)
            output += "\n" + e ;
        return output;
    }

}
