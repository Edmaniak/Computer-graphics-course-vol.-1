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
        scanLineRenderer = new ScanLineRenderer(canvas);
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Polygon polygonToFill = myCanvas.getPolygonAt(e.getX(),e.getY());
                if (polygonToFill != null) {
                    scanLineRenderer.fill(polygonToFill, color);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                }
            }
        });
    }


    @Override
    public void doAfterSwitchOut() {

    }

    @Override
    public void doOnSwitchIn() {

    }

    @Override
    public void clear() {

    }
}
