package renderers;

import gui.Canvas;
import objects.Polygon;

public class Clipper extends Renderer {

    private Polygon clippingArea;

    public Clipper(Canvas canvas) {
        super(canvas);
    }

}
