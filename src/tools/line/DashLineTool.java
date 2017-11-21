package tools.line;

import gui.Canvas;
import renderers.line.DashLineRenderer;

import java.awt.*;

public class DashLineTool extends LineTool {

    private final int space = 5;

    public DashLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        lr = new DashLineRenderer(canvas, space);
    }


}
