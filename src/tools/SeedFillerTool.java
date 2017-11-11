package tools;

import gui.Canvas;
import patterns.Pattern;
import renderers.SeedFillPatternRenderer;
import renderers.SeedFillRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeedFillerTool extends Tool {

    private SeedFillRenderer fillRenderer;

    public SeedFillerTool(Canvas canvas, Color color) {
        super(canvas, color);
        Pattern p = new Pattern(8, 8);
        fillRenderer = new SeedFillRenderer(canvas, color);

        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fillRenderer.setBgColor(myCanvas.getCanvasColorAt(e.getX(), e.getY()));
                fillRenderer.setFillColor(color);
                fillRenderer.fill(e.getX(), e.getY());
                myCanvas.repaint();
                myCanvas.drawInto();
            }
        });
    }

    public SeedFillerTool(Canvas canvas, Color color, Color color1, Color color2, Pattern pattern) {
        this(canvas, color);
        fillRenderer = new SeedFillPatternRenderer(canvas, color, pattern);
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
