package tools.fill;

import gui.Canvas;
import patterns.Pattern;
import renderers.fill.SeedFillRenderer;
import tools.Tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SeedFillerTool extends Tool {

    protected SeedFillRenderer fillRenderer;
    protected Pattern pattern;

    public SeedFillerTool(Canvas canvas, Color color) {
        super(canvas, color);
        fillRenderer = new SeedFillRenderer(canvas, color);
        pattern = Pattern.generate(6, 6);
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                fillRenderer.setBgColor(myCanvas.getCanvasColorAt(e.getX(), e.getY()));
                fillRenderer.setFillColor(color);
                fillRenderer.fill(e.getX(), e.getY());
                myCanvas.repaint();
                myCanvas.drawInto();
                pattern = Pattern.generate(6, 6);
            }
        });
    }

    @Override
    public String getInstruction() {
        return "Click into outlined area";
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
