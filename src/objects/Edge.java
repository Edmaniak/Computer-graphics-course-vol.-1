package objects;

public class Edge {

    private Vertex2D origin;
    private Vertex2D end;

    public Edge(Vertex2D origin, Vertex2D end) {
        this.origin = origin;
        this.end = end;
    }

    public Vertex2D getEnd() {
        return end;
    }

    public Vertex2D getOrigin() {
        return origin;
    }

    public boolean isHorizontal() {
        return origin.y == end.y;
    }

    // Existuje prusecik s vodorovnou linii zadanou parametrem y
    public boolean isIntersectional(int y) {
        return y > origin.y && y < end.y;
    }

    public Edge orientedEdge() {
        if (origin.y >= end.y)
            return new Edge(end, origin);
        return this;
    }

    @Override
    public String toString() {
        return "EDGE: " + "{" + origin + " " + end + "}";
    }

}
