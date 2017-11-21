package renderers;

import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;

import java.util.ArrayList;
import java.util.List;

public class Clipper {

    private Polygon clippingArea;

    public Clipper(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }

    public Polygon clip(Polygon in) {

        List<Vertex2D> inPoints = new ArrayList<>(in.getPoints());
        List<Vertex2D> result = new ArrayList<>(inPoints);
        List<Vertex2D> clipper = new ArrayList<>(clippingArea.getPoints());

        int len = clipper.size();
        for (int i = 0; i < len; i++) {

            int len2 = result.size();
            List<Vertex2D> input = result;
            result = new ArrayList<>(len2);

            Vertex2D A = clipper.get((i + len - 1) % len);
            Vertex2D B = clipper.get(i);

            for (int j = 0; j < len2; j++) {

                Vertex2D P = input.get((j + len2 - 1) % len2);
                Vertex2D Q = input.get(j);

                Edge e1 = new Edge(A, B);
                Edge e2 = new Edge(P, Q);

                if (e1.isInside(Q)) {
                    if (!e1.isInside(P))
                        result.add(e1.getIntersection(e2));
                    result.add(Q);
                } else if (e1.isInside(P))
                    result.add(e1.getIntersection(e2));
            }
        }
        return new Polygon(result);
    }

    public void setClippingArea(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }

    public Polygon getClippingArea() {
        return clippingArea;
    }
}
