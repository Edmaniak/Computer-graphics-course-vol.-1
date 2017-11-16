package renderers.fill;

import gui.Canvas;
import renderers.Renderer;

import java.awt.*;

public class SeedFillRenderer extends Renderer {

    protected int bgColor;
    protected Color fillColor;

    public SeedFillRenderer(Canvas canvas, Color c) {
        super(canvas);
    }

    public void fill(int x, int y) {
        if (myCanvas.canDrawAt(x, y)) {
            if (myCanvas.getActiveColorAt(x, y) == bgColor && myCanvas.getActiveColorAt(x, y) != fillColor.hashCode()) {
                myCanvas.putPixel(x, y, fillColor);
                fill(x, y + 1);
                fill(x, y - 1);
                fill(x + 1, y);
                fill(x - 1, y);
            }
        }
    }


    public void setBgColor(int toFillColor) {
        this.bgColor = toFillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}

