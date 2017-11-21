package tools;

import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;
import renderers.CircleRenderer;
import renderers.Clipper;
import renderers.PolygonRenderer;
import tools.polygon.PolygonTool;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClipperTool extends PolygonTool {

    private JButton clip;
    private Polygon clippingArea;
    private Clipper clipper;
    private CircleRenderer cr;

    public ClipperTool(Canvas canvas, Color color) {
        super(canvas, Color.BLUE);
        clippingArea = polygon;
        clipper = new Clipper(clippingArea);
        pr = new PolygonRenderer(canvas);
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
                Polygon in = new Polygon(myCanvas.getPolygons().get(0));
                Polygon pol = clipper.clip(in);
                for (Vertex2D v : pol.getPoints()) {
                    cr.render(v, 5, Color.yellow);
                }
                myCanvas.repaint();
                myCanvas.drawInto();
            }
        });
        myCanvas.add(clip);
    }

    @Override
    public void clear() {

    }
}
