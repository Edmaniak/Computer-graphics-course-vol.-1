package tools;

import gui.Canvas;
import renderers.ScanLineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScanLineTool extends Tool {

    private ScanLineRenderer scanLineRenderer;

    public ScanLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        scanLineRenderer = new ScanLineRenderer(canvas, color);
        setMainRenderer(scanLineRenderer, color);
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                myCanvas.getPolygonLibrary();
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
