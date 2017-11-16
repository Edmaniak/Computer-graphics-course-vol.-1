package tools;

import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;
import renderers.CircleRenderer;
import renderers.Clipper;
import renderers.PolygonRenderer;
import renderers.line.DashLineRenderer;
import tools.polygon.PolygonTool;

import javax.swing.*;
import java.awt.*;

public class ClipperTool extends PolygonTool {

    private JButton clip;
    private Polygon clippingArea;
    private Clipper clipper;
    private CircleRenderer cr;

    public ClipperTool(Canvas canvas, Color color) {
        super(canvas, color);
        clippingArea = polygon;
        clipper = new Clipper(clippingArea);
        lr = new DashLineRenderer(canvas, 5);
        pr = new PolygonRenderer(canvas,5);
        cr = new CircleRenderer(canvas);
    }

    @Override
    public void doAfterSwitchOut() {
        myCanvas.remove(clip);
    }

    @Override
    public void doOnSwitchIn() {
        clip = new JButton("- CLIP -");
        clip.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clip.addActionListener(e -> {
            if (myCanvas.getPolygons().get(0) != null) {
                Polygon in = myCanvas.getPolygons().get(0);
                clipper.clip(in);


            }
        });
        myCanvas.add(clip);
    }

    @Override
    public void clear() {

    }
}
