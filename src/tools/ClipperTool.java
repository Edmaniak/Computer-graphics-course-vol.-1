package tools;

import gui.Canvas;
import objects.Polygon;
import renderers.Clipper;
import renderers.DashLineRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClipperTool extends PolygonTool {

    private JButton clip;
    private Polygon clippingArea;
    private Clipper clipper;

    public ClipperTool(Canvas canvas, Color color) {
        super(canvas, color);
        clippingArea = polygon;
        clipper = new Clipper(clippingArea);
        lr = new DashLineRenderer(canvas, 5);
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
                System.out.println(newPol);
            }
        });
        myCanvas.add(clip);
    }

    @Override
    public void clear() {

    }
}
