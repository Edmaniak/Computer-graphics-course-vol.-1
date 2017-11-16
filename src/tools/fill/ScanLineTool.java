package tools.fill;

import gui.Canvas;
import objects.Polygon;
import renderers.fill.ScanLineRenderer;
import tools.Tool;

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
                Polygon polygonToFill = myCanvas.getPolygons().get(0);
                if (polygonToFill != null) {
                    scanLineRenderer.fill(polygonToFill, color);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                }
            }
        });
    }

    @Override
    public String getInstruction() {
        return null;
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
