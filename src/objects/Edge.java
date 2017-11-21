package objects;

public class Edge {

    private Vertex2D origin;
    private Vertex2D end;

    public Edge(Vertex2D origin, Vertex2D end) {
        this.origin = origin;
        this.end = end;
    }

    public Edge(Edge edge) {
        origin = edge.getOrigin();
        end = edge.getEnd();
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

    /**
     * Existuje prusecik s vodorovnou linii zadanou parametrem y
     * @param y coordinate
     * @return
     */
    public boolean isIntersectional(int y) {
        return y >= origin.y && y < end.y;
    }

    /**
     * Vraci prusecikovou souradnici x s vodorovnou lini Y
     * @param y
     * @return
     */
    public int getXIntersection(int y) {
        float dx = (float) (end.x - origin.x);
        float dy = (float) (end.y - origin.y);
        float k = dx / dy;
        float q = (float) origin.x - k * (float) origin.y;
        float x =   ((float)(k * (float) y) + q);
        return (int) x;
    }

    public Edge orientedEdge() {
        if (origin.y > end.y)
            return new Edge(end, origin);
        return this;
    }

    public Edge xSortedEdge() {
        if(origin.x > end.x)
            return new Edge(end,origin);
        return this;
    }

    public boolean isInside(Vertex2D p) {
        return (end.x - origin.x) * (p.y - origin.y) - (end.y - origin.y) * (p.x - origin.x) > 0;
        // ((end.y - origin.y) * x) + ((end.x - origin.x) * y) + ((end.x * origin.y) - (end.y - origin.x)) > 0;
    }

    public Vertex2D getIntersection(Edge e) {

        double x = ((((origin.x * end.y) - (end.x * origin.y)) * (e.origin.x - e.end.x)) - (((e.origin.x * e.end.y) - (e.end.x * e.origin.y)) * (origin.x - end.x))) /
                // -------------------------------------------------------------------------------------------------------------------------------------------------//
                ((origin.x - end.x) * (e.origin.y - e.end.y) - (origin.y - end.y) * (e.origin.x - e.end.x));

        double y = ((((origin.x * end.y) - (end.x * origin.y)) * (e.origin.y - e.end.y)) - (((e.origin.x * e.end.y) - (e.end.x * e.origin.y)) * (origin.y - end.y))) /
                // ---------------------------------------------------------------------------------------------------------------------------------------------- ---//
                ((origin.x - end.x) * (e.origin.y - e.end.y) - (origin.y - end.y) * (e.origin.x - e.end.x));

        return new Vertex2D((int) x, (int) y);
    }

    public boolean containsPoint(Vertex2D point) {
        return point == origin || point == end;
    }

    public boolean containsPointAt(int x, int y) {
        return (origin.x == x && origin.y == y) || (end.x == x && end.y == y);
    }

    @Override
    public String toString() {
        return "{" + origin + " " + end + "} \n";
    }

}
