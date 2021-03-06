package tools.line;

import gui.Canvas;
import objects.Vertex2D;
import renderers.line.LineRenderer;
import tools.Tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineTool extends Tool {

    private Vertex2D origin;
    private Vertex2D end;
    protected LineRenderer lr;

    public LineTool(Canvas canvas, Color color) {
        super(canvas, color);
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
    public String getInstruction() {
        return "Drag the mouse from point A to B";
    }

    @Override
    public void doAfterSwitchOut() {

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
