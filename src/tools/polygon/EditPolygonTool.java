package tools.polygon;

import gui.Canvas;
import objects.Edge;
import objects.Polygon;
import objects.Vertex2D;
import renderers.CircleRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EditPolygonTool extends PolygonTool {

    private final CircleRenderer cr;
    private final int RADIUS = 5;
    private boolean drawing;
    private Vertex2D foundPoint;
    private JButton button;

    public EditPolygonTool(Canvas canvas, Color color) {
        super(canvas, color);
        cr = new CircleRenderer(canvas);
        // because we inherit the handlers we don't need
        destroyHandlers();
        defineClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawing = true;
                // Left button
                if (e.getButton() == MouseEvent.BUTTON1 && foundPoint == null) {
                    // First point draw
                    if (polygon.size() == 0) {
                        Vertex2D point = new Vertex2D(e.getX(), e.getY());
                        polygon.addPoint(point);
                        renderCircleAt(point, Color.yellow);
                        myCanvas.repaint();
                        myCanvas.drawInto();
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON1 && foundPoint != null) {
                    removeFromRaster(polygon);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Right button
                if (e.getButton() == MouseEvent.BUTTON3)
                    // Erasing founded point
                    if (foundPoint != null) {
                        removePoint(foundPoint);
                        myCanvas.repaint();
                        myCanvas.drawInto();
                    }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                drawing = false;
                // Drawing other points when left mouse button released
                if (e.getButton() == MouseEvent.BUTTON1 && foundPoint == null) {
                    Vertex2D point = new Vertex2D(e.getX(), e.getY());
                    polygon.addPoint(point);
                }
                renderPoints(polygon, Color.yellow);
                myCanvas.repaint();
                myCanvas.drawInto();
            }
        });
        defineMotionHandler(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                myCanvas.mix();

                // Adding new point
                if (foundPoint == null) {
                    drawing = true;

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
                }

                // Modifying point
                if (foundPoint != null)
                    movePointTo(new Vertex2D(e.getX(), e.getY()));

                myCanvas.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Finding a polygon point on the position of cursor
                if (!drawing && foundPoint == null) {
                    for (Vertex2D point : polygon.getPoints()) {
                        Vertex2D cursorPos = new Vertex2D(e.getX(), e.getY());
                        // Found => cursor change and setting the founded point
                        if (cursorPos.isNear(point, RADIUS)) {
                            myCanvas.setCursor(new Cursor(Cursor.HAND_CURSOR));
                            foundPoint = point;
                        }
                    }
                    // Leaving the founded point event
                } else if (!foundPoint.isNear(new Vertex2D(e.getX(), e.getY()), RADIUS)) {
                    myCanvas.setCursor(cursor);
                    foundPoint = null;
                }
            }
        });
    }

    /**
     * Removes the point logically and from the raster respectively
     * @param point point to remove
     */
    private void removePoint(Vertex2D point) {
        renderCircleAt(point, Color.BLACK);
        List<Edge> removedEdges = polygon.removePoint(point);

        for (Edge e : removedEdges)
            lr.render(new Vertex2D(e.getOrigin()), new Vertex2D(e.getEnd()), Color.BLACK);

        renderPoints(polygon, Color.yellow);

        pr.render(polygon, color);
        myCanvas.setCursor(cursor);
        foundPoint = null;
    }

    /**
     * Moves desired point in polygon and re-renders everything
     * @param point point to move
     */
    private void movePointTo(Vertex2D point) {
        foundPoint.x = point.x;
        foundPoint.y = point.y;
        pr.render(polygon, color);
    }

    private void removeFromRaster(Polygon polygon) {
        renderEdges(polygon, Color.BLACK);
        renderPoints(polygon, Color.BLACK);
    }

    private void renderPoints(Polygon polygon, Color color) {
        for (Vertex2D p : polygon.getPoints())
            renderCircleAt(p, color);
    }

    private void renderEdges(Polygon polygon, Color color) {
        for (Edge edge : polygon.getEdges())
            lr.render(new Vertex2D(edge.getOrigin()), new Vertex2D(edge.getEnd()), color);
    }

    /**
     * draws circle around point
     */
    private void renderCircleAt(Vertex2D point, Color color) {
        cr.render(new Vertex2D(point), RADIUS, color);
    }

    /**
     * After leaving the tool re-render polygon nicely
     */
    private void renderClean() {
        for (Vertex2D p : polygon.getPoints())
            renderCircleAt(p, Color.BLACK);
        pr.render(polygon, color);
        myCanvas.repaint();
        myCanvas.drawInto();
        myCanvas.addToPolygons(new Polygon(polygon));
    }

    @Override
    public String getInstruction() {
        return "*LMB* for ADDING and EDITING the point || *RMB* for REMOVING the point";
    }

    @Override
    public void doAfterSwitchOut() {
        myCanvas.remove(button);
        renderClean();
    }

    @Override
    public void doOnSwitchIn() {
        button = new JButton("- DONE EDITING -");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(e -> {
            doAfterSwitchOut();
            polygon.clear();
        });
        myCanvas.add(button);
    }

    @Override
    public void clear() {

    }
}
