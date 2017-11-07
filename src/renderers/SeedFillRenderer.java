package renderers;

import gui.Canvas;

import java.awt.*;

public class SeedFillRenderer extends Renderer {

    private int toFillColor;

    public SeedFillRenderer(Canvas canvas, Color c) {
        super(canvas);
    }

    public void fill(int x, int y) {
       /* if (myCanvas.getActiveColorAt(x, y) == toFillColor && myCanvas.getActiveColorAt(x,y) != color.hashCode()) {
            myCanvas.putPixel(x, y, color);
            fill(x, y + 1);
            fill(x, y - 1);
            fill(x + 1, y);
            fill(x - 1, y);
        }*/
    }

    public void toFillColor(int toFillColor) {
        this.toFillColor = toFillColor;
    }
}

