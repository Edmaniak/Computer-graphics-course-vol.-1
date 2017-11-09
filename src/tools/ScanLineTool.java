package tools;

import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;
import renderers.ScanLineRenderer;

import java.awt.*;
import java.util.List;
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
                Polygon polygon = myCanvas.getPolygons().get(0);
                scanLineRenderer.fill(polygon,color);
                myCanvas.repaint();
                myCanvas.drawInto();
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
