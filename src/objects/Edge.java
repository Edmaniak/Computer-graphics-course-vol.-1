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
        // Shortening it here IS PROBABLY BAD
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

    @Override
    public String toString() {
        return "EDGE: " + "{" + origin + " " + end + "}";
    }

}
