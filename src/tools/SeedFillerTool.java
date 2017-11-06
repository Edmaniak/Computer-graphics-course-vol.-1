package tools;

import gui.Canvas;
import renderers.SeedFillRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeedFillerTool extends Tool {

    private SeedFillRenderer fillRenderer;

    public SeedFillerTool(Canvas canvas, Color color) {
        super(canvas, color);
        fillRenderer = new SeedFillRenderer(canvas, color);
        setMainRenderer(fillRenderer, color);

        setClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fillRenderer.toFillColor(myCanvas.getCanvasColorAt(e.getX(), e.getY()));
                fillRenderer.fill(e.getX(), e.getY());
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
