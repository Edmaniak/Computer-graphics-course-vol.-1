package objects;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private final List<Vertex2D> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public Polygon(Polygon polygon) {
        points = new ArrayList<>(polygon.getPoints());
    }

    public Polygon(List<Vertex2D> polyPoints) {
        points = new ArrayList<>(polyPoints);
    }

    /**
     * Method automatically creates edges when creating polygon
     *
     * @param point new point to add
     */
    public void addPoint(Vertex2D point) {

        // Adding points
        if (points.size() < 2)
            points.add(point);
        else
            points.add(points.size() - 1, point);
    }

    /**
     * Number of points in the polygon
     * @return
     */
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
     * Method for removing points logically.
     *
     * @param point
     * @return edges needed by renderer to remove edges in raster
     */
    public void removePoint(Vertex2D point) {
        if (points.contains(point))
            points.remove(point);
    }

    public List<Vertex2D> getPoints() {
        return points;
    }

    public void clear() {
        points.clear();
    }

    public Vertex2D getTopPoint() {
        Vertex2D yMax = getFirstPoint();
        for (Vertex2D point : points) {
            if (point.y > yMax.y)
                yMax = point;
        }
        return yMax;
    }

    public Vertex2D getBottomPoint() {
        Vertex2D yMin = getFirstPoint();
        for (Vertex2D point : points) {
            if (point.y < yMin.y)
                yMin = point;
        }
        return yMin;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("POINTS: ");
        for (Vertex2D point : points)
            output.append(point).append(" ");
        return output.toString();
    }

}
