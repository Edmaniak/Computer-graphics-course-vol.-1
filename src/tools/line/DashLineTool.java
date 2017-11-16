package tools.line;

import gui.Canvas;
import renderers.line.DashLineRenderer;

import java.awt.*;


public class DashLineTool extends LineTool {

    private int space = 5;

    public DashLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        lr = new DashLineRenderer(canvas, space);
    }

    public DashLineTool(Canvas canvas, Color color, int space) {
        super(canvas, color);
        lr = new DashLineRenderer(canvas, space);
    }

}
