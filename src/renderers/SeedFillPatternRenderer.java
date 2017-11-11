package renderers;

import gui.Canvas;
import patterns.Pattern;

import java.awt.*;

public class SeedFillPatternRenderer extends SeedFillRenderer {

    private Pattern pattern;
    private int[][] pd;

    public SeedFillPatternRenderer(Canvas canvas, Color c, Pattern pattern) {
        super(canvas, c);
        this.pattern = pattern;
        this.pd = pattern.getStructure();
    }

    @Override
    public void fill(int x, int y) {
        if (myCanvas.getActiveColorAt(x, y) == bgColor &&
                (myCanvas.getActiveColorAt(x, y) != pattern.color1.hashCode() || myCanvas.getActiveColorAt(x, y) != pattern.color2.hashCode())) {
            if (pd[x / 2 % pattern.getHeight()][y / 2 % pattern.getWidth()] == 1)
                myCanvas.putPixel(x, y, pattern.color1);
            else
                myCanvas.putPixel(x, y, pattern.color2);
            fill(x, y + 1);
            fill(x, y - 1);
            fill(x + 1, y);
            fill(x - 1, y);
        }
    }
}
