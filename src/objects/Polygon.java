package objects;

import app.Vertex2D;

import java.util.ArrayList;
import java.util.List;

public class Polygon {

    private List<Vertex2D> points;

    public Polygon(List<Vertex2D> points) {
        this.points = points;
    }

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(Vertex2D point) {
        points.add(point);
    }

    public int size() {
        return points.size();
    }

    public Vertex2D getPoint(int i) {
        return points.get(i);
    }

}
