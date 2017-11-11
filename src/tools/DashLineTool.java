package tools;

import gui.Canvas;
import objects.Vertex2D;
import renderers.DashLineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashLineTool extends LineTool {

    private int space = 5;

    public DashLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        lr = new DashLineRenderer(canvas, space);
    }

}
