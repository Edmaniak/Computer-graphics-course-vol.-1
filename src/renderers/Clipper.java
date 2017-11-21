package renderers;

import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Sutherland-Hogdman algorithm for clipping
 */
public class Clipper {

    private Polygon clippingArea;

    public Clipper(Polygon clippingArea) {
        this.clippingArea = clippingArea;
    }

    public Polygon clip(Polygon in) {

        List<Vertex2D> inPoints = new ArrayList<>(in.getPoints());
        List<Vertex2D> result = new ArrayList<>(inPoints);
        List<Vertex2D> clipper = new ArrayList<>(clippingArea.getPoints());

        for (int i = 0; i < clipper.size(); i++) {

            List<Vertex2D> input = result;
            result = new ArrayList<>();

            Vertex2D cv1 = clipper.get((i));
            Vertex2D cv2 = clipper.get((i + 1) % clipper.size());
            Edge e1 = new Edge(cv1, cv2);

            for (int j = 0; j < input.size(); j++) {

                Vertex2D pv1 = input.get(j);
                Vertex2D pv2 = input.get((j + 1) % input.size());
                Edge e2 = new Edge(pv1, pv2);

                if (e1.isInside(pv2)) {
                    if (!e1.isInside(pv1))
                        result.add(e1.getIntersection(e2));
                    result.add(pv2);
                } else if (e1.isInside(pv1))
                    result.add(e1.getIntersection(e2));
            }
        }
        return new Polygon(result);
    }

}
