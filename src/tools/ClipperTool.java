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
                Polygon in = myCanvas.getPolygons().get(0);
                Polygon newPol = clipper.clip(in);
                for (Vertex2D p : newPol.getPoints())
                    cr.render(p, 5, Color.yellow);
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
