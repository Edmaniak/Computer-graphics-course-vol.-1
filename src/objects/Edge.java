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
        return y >= origin.y && y < end.y;
    }


    // Vraci prusecikovou souracnici x s vodorovnou lini Y
    public int getXIntersection(int y) {
        float dx = (float) (end.x - origin.x);
        float dy = (float) (end.y - origin.y);
        float k = dx / dy;
        float q = origin.x - k * origin.y;
        float x = ((k * y) + q);
        return (int) x;
    }

    public void shortIt() {
        this.end.y -= 1;
    }

    public Edge orientedEdge() {
        if (origin.y > end.y)
            return new Edge(end, origin);
        return this;
    }

    public boolean isInside(Vertex2D point) {
        return (end.y - origin.y) * point.x + (end.x - origin.x) * point.y + end.x * origin.y - end.y * origin.x > 0;
    }

    public Vertex2D getIntersection(Edge e) {

        double x = ((((origin.x * end.y) - (end.x * origin.y)) * (e.origin.x - e.end.x)) - (((e.origin.x * e.end.y) - (e.end.x * e.origin.y)) * (origin.x - end.x))) /
                // --------------------------------------------------------------------------------------------------------------------------------//
                ((origin.x - end.x) * (e.origin.y - e.end.y) - (origin.y - end.y) * (e.origin.x - e.end.x));

        double y = ((((origin.x * end.y) - (end.x * origin.y)) * (e.origin.y - e.end.y)) - (((e.origin.x * e.end.y) - (e.end.x * e.origin.y)) * (origin.y - end.y))) /
                // --------------------------------------------------------------------------------------------------------------------------------//
                ((origin.x - end.x) * (e.origin.y - e.end.y) - (origin.y - end.y) * (e.origin.x - e.end.x));

        return new Vertex2D((int) x, (int) y);
    }

    @Override
    public String toString() {
        return "EDGE: " + "{" + origin + " " + end + "}";
    }

}
