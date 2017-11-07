package renderers;

import gui.Canvas;

import java.awt.*;
import objects.Polygon;

public class ScanLineRenderer extends Renderer {

    public ScanLineRenderer(Canvas canvas, Color c) {
        super(canvas);
    }

    public void fill(Polygon polygon, Color c) {

        // Inicializace potencionalne nejvestich bodu v polygonu
        int yMax = polygon.getTopPoint().y;
        int yMin = polygon.getBottomPoint().y;

        System.out.println(yMax);
        System.out.println(yMin);




    }


}
