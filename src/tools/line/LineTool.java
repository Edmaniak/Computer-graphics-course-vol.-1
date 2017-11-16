package tools.line;

import gui.Canvas;
import objects.Edge;
import objects.Vertex2D;
import renderers.line.LineRenderer;
import tools.Tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LineTool extends Tool {

    protected Vertex2D origin;
    protected Vertex2D end;
    protected LineRenderer lr;
    private List<Edge> edges;

    public LineTool(Canvas canvas, Color color) {
        super(canvas, color);
        lr = new LineRenderer(myCanvas);
        edges = new ArrayList<>();
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (edges.size() < 2) {
                    if (origin == null) {
                        // Ugly block needed for one point line
                        origin = new Vertex2D(e.getX(), e.getY());
                        end = new Vertex2D(e.getX(), e.getY());
                        myCanvas.mix();
                        lr.render(new Vertex2D(origin), new Vertex2D(end), color);
                        myCanvas.repaint();
                    }
                } else {
                    System.out.println(edges.get(0));
                    System.out.println(edges.get(0).isInside(e.getX(),e.getY()));
                    System.out.println(edges.get(0).getIntersection(edges.get(1)));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                myCanvas.drawInto();
                Edge ed = new Edge(origin,end).orientedEdge();
                edges.add(ed);
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
        return "Drag the mouse";
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
