package tools;

import gui.Canvas;
import objects.Vertex2D;
import renderers.LineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineTool extends Tool {

    protected Vertex2D origin;
    protected Vertex2D end;
    protected LineRenderer lr;

    public LineTool(Canvas canvas, Color color) {
        super(canvas, color);
        instruction = "Drag the mouse.";
        lr = new LineRenderer(myCanvas);
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (origin == null) {
                    // Ugly block needed for one point line
                    origin = new Vertex2D(e.getX(), e.getY());
                    end = new Vertex2D(e.getX(), e.getY());
                    myCanvas.mix();
                    lr.render(new Vertex2D(origin), new Vertex2D(end), color);
                    myCanvas.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                myCanvas.drawInto();
                clear();
            }
        });
        defineMotionHandler(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                end = new Vertex2D(e.getX(), e.getY());
                myCanvas.mix();
                lr.render(new Vertex2D(origin), new Vertex2D(end), color);
                myCanvas.repaint();
            }
        });
    }

    @Override
    public void doAfterOut() {

    }

    @Override
    public void doOnSwitchIn() {

    }

    @Override
    public void clear() {
        origin = null;
        end = null;
    }
}
