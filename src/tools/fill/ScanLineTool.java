package tools.fill;

import app.SimpleDraw;
import gui.Canvas;
import objects.Polygon;
import objects.Vertex2D;
import renderers.PolygonRenderer;
import renderers.fill.ScanLineRenderer;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScanLineTool extends Tool {

    private final ScanLineRenderer slr;
    private final PolygonRenderer pr;

    public ScanLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        slr = new ScanLineRenderer(canvas);
        pr = new PolygonRenderer(canvas);
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Polygon polygonToFill = myCanvas.getPolygonAt(new Vertex2D(e.getX(),e.getY()));
                if (polygonToFill != null) {
                    slr.fill(polygonToFill, color);
                    pr.render(polygonToFill, color);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                } else
                    JOptionPane.showMessageDialog(
                            SimpleDraw.gui,
                            "There is no polygon around this position " + new Vertex2D(e.getX(),e.getY()),
                            "Cannot fill",
                            JOptionPane.ERROR_MESSAGE
                    );

            }
        });
    }

    @Override
    public String getInstruction() {
        return "Click into the outlined polygon";
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
