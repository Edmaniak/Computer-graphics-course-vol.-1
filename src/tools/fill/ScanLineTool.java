package tools.fill;

import gui.Canvas;
import objects.Polygon;
import renderers.PolygonRenderer;
import renderers.fill.ScanLineRenderer;
import tools.Tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ScanLineTool extends Tool {

    private ScanLineRenderer slr;
    private PolygonRenderer pr;

    public ScanLineTool(Canvas canvas, Color color) {
        super(canvas, color);
        slr = new ScanLineRenderer(canvas);
        pr = new PolygonRenderer(canvas);
        removeAllButFirstPolygon();
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Polygon polygonToFill = myCanvas.getPolygon();
                if (polygonToFill != null) {
                    slr.fill(polygonToFill, color);
                    pr.render(polygonToFill, color);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                }
            }
        });
    }

    @Override
    public String getInstruction() {
        return null;
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

    private void removeAllButFirstPolygon() {
        List<Polygon> pls = myCanvas.getPolygons();
        if (pls.size() > 1) {
            for (int i = 1; i < pls.size(); i++) {
                pr.render(pls.get(i), color.BLACK);
                pls.remove(i);
            }
        }
        myCanvas.repaint();
        myCanvas.drawInto();
    }
}
