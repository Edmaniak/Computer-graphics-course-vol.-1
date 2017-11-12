package tools;

import gui.Canvas;
import objects.Edge;
import objects.Vertex2D;
import renderers.CircleRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EditPolygonTool extends PolygonTool {

    private CircleRenderer cr;
    private final int RADIUS = 5;
    private boolean drawing;
    private Vertex2D foundPoint;

    public EditPolygonTool(Canvas canvas, Color color) {
        super(canvas, color);
        cr = new CircleRenderer(canvas);
        // because we inherit the handlers we don't need
        destroyHandlers();
        instruction = "*LMB* ADD and EDIT point || *RMB* REMOVE point";
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawing = true;
                if (e.getButton() == MouseEvent.BUTTON1)
                    if (polygon.size() == 0) {
                        Vertex2D point = new Vertex2D(e.getX(), e.getY());
                        polygon.addPoint(point);
                        cr.render(new Vertex2D(point), RADIUS, Color.yellow);
                        myCanvas.repaint();
                        myCanvas.drawInto();
                    }

                if (foundPoint != null && e.getButton() == MouseEvent.BUTTON3) {
                    removePoint(foundPoint);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drawing = false;
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Vertex2D point = new Vertex2D(e.getX(), e.getY());
                    polygon.addPoint(point);
                    cr.render(new Vertex2D(point), RADIUS, Color.yellow);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                }
            }
        });
        defineMotionHandler(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawing = true;
                myCanvas.mix();
                // Line
                if (polygon.size() == 1) {
                    Vertex2D newP = new Vertex2D(e.getX(), e.getY());
                    Vertex2D p2 = new Vertex2D(polygon.getLastPoint());
                    lr.render(newP, p2, color);
                }

                // Triangle or polygon
                if (polygon.size() > 1) {

                    Vertex2D newP = new Vertex2D(e.getX(), e.getY());
                    Vertex2D p1 = new Vertex2D(polygon.getLastPoint());
                    Vertex2D p2 = new Vertex2D(polygon.getPoint(polygon.size() - 2));

                    // Erasing connecting line
                    if (polygon.size() > 2)
                        lr.render(new Vertex2D(p1), new Vertex2D(p2), Color.BLACK);

                    lr.render(new Vertex2D(newP), new Vertex2D(p1), color);
                    lr.render(new Vertex2D(newP), new Vertex2D(p2), color);

                }

                myCanvas.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!drawing && foundPoint == null) {
                    for (Vertex2D point : polygon.getPoints()) {
                        Vertex2D cursorPos = new Vertex2D(e.getX(), e.getY());
                        if (cursorPos.isNear(point, RADIUS)) {
                            myCanvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            foundPoint = point;
                        }
                    }
                } else if (!foundPoint.isNear(new Vertex2D(e.getX(), e.getY()), RADIUS)) {
                    myCanvas.setCursor(cursor);
                    foundPoint = null;
                }
            }
        });
    }

    // Method for removing one point logically and from the raster respectively
    private void removePoint(Vertex2D point) {
        cr.render(new Vertex2D(point), RADIUS, Color.BLACK);
        List<Edge> removedEdges = polygon.removePoint(point);

        for (Edge edge : removedEdges)
            lr.render(new Vertex2D(edge.getOrigin()), new Vertex2D(edge.getEnd()), Color.BLACK);

        for (Vertex2D p : polygon.getPoints())
            cr.render(p, RADIUS, Color.yellow);

        pr.render(polygon, color);
        myCanvas.setCursor(cursor);
        foundPoint = null;
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
