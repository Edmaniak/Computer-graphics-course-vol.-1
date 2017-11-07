package tools;

import gui.Canvas;
import objects.Polygon;
import renderers.ScanLineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScanLineTool extends Tool {

    private ScanLineRenderer scanLineRenderer;

    public ScanLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        scanLineRenderer = new ScanLineRenderer(canvas, color);
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Polygon polygon = myCanvas.getPolygonLibrary().get(0);
                //scanLineRenderer.fill(polygon);
            }
        });
    }

    @Override
    public void doAfterSwitch() {

    }

    @Override
    public void clear() {

    }
}
