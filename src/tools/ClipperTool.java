package tools;

import gui.Canvas;
import objects.Polygon;
import renderers.Clipper;
import renderers.PolygonRenderer;
import tools.polygon.PolygonTool;

import javax.swing.*;
import java.awt.*;

public class ClipperTool extends PolygonTool {

    private JButton btnClip;
    private Polygon clippingArea;
    private Polygon in;
    private Polygon result;
    private final Clipper clipper;

    public ClipperTool(Canvas canvas) {
        super(canvas, Color.BLUE);
        clippingArea = polygon;
        clipper = new Clipper(clippingArea);
        pr = new PolygonRenderer(canvas);
    }

    @Override
    public void doAfterSwitchOut() {
        myCanvas.remove(btnClip);
    }

    @Override
    public void doOnSwitchIn() {
        btnClip = new JButton("- CLIP -");
        btnClip.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClip.addActionListener(e -> {
            if (myCanvas.getFirstPolygon() != null) {
                in = myCanvas.getPolygons().get(0);
                result = clipper.clip(in);
                reRender();
            }
        });
        myCanvas.add(btnClip);
    }

    @Override
    public void clear() {

    }

    @Override
    public String getInstruction() {
        return "Define an area around the first polygon in the scene";
    }

    private void reRender() {
        if (result != null) {
            pr.render(in, Color.BLACK);
            pr.render(clippingArea, Color.BLACK);
            pr.render(result, color);
            myCanvas.repaint();
            myCanvas.drawInto();
            myCanvas.clearPolygons();
            myCanvas.addToPolygons(result);
        }
    }
}
