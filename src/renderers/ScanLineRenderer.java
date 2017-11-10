package renderers;

import gui.Canvas;
import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;
import utilities.InsertionSort;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScanLineRenderer extends Renderer {

    private List<Edge> relevantEdges;
    private LineRenderer lr;

    public ScanLineRenderer(Canvas canvas) {
        super(canvas);
        relevantEdges = new ArrayList<>();
        lr = new LineRenderer(canvas);
    }

    public void fill(Polygon polygon, Color color) {

        // List pruseciku staci mit lokalne v metode fill
        List<Integer> xIntersections = new ArrayList<>();

        // Inicializace potencionalne nejvestich bodu v polygonu
        int yMax = polygon.getTopPoint().y;
        int yMin = polygon.getBottomPoint().y;

        // Adding all not-horizontal edges to the working edges list
        for (Edge edge : polygon.getEdges())
            if (!edge.isHorizontal())
                relevantEdges.add(edge);



        for (int y = yMin; y < yMax; y++) {
            for (Edge e : relevantEdges)
                if (e.isIntersectional(y))
                    xIntersections.add(e.getXIntersection(y));

            // Serazeni podle x
            InsertionSort.sort(xIntersections);

            for (int i = 0; i < xIntersections.size(); i += 2) {
                if (xIntersections.size() < 3) {
                    Vertex2D origin = new Vertex2D(xIntersections.get(i), y);
                    Vertex2D end = new Vertex2D(xIntersections.get(i + 1), y);
                    lr.render(origin, end, color);
                }
            }

            xIntersections.clear();

        }

    }


}
