package tools;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import renderers.ArcRenderer;
import renderers.LineRenderer;

public class Arc extends GraphicalObject {

    private Vertex2D center;
    private Vertex2D radPoint;
    private Vertex2D initRadPoint;
    private final ArcRenderer ar;
    private final LineRenderer lr;
    private int radius;

    public Arc(Canvas canvas, Color color) {
        super(canvas, color);
        instruction = "Drag an diameter and then move the mouse.";
        ar = new ArcRenderer(canvas, color);
        lr = new LineRenderer(canvas, Color.RED);
        setMainRenderer(ar, color);
        setClickHandler(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (center == null)
                    center = new Vertex2D(e.getX(), e.getY());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (radPoint != null) {
                    // mazani cerveneho voditka
                    lr.setColor(Color.BLACK);
                    lr.render(center, radPoint);
                    lr.setColor(Color.RED);
                    myCanvas.repaint();
                    myCanvas.drawInto();
                    clear();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (center != null && radPoint == null) {
                    radius = (int) sqrt(pow((initRadPoint.x - center.x), 2) + pow((initRadPoint.y - center.y), 2));
                    // initial render with initRadPoint and dynamical radPoint the same
                    myCanvas.mix();
                    ar.render(radius, center, initRadPoint, initRadPoint);
                    myCanvas.repaint();
                }
            }

        });
        setMotionHandler(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                initRadPoint = new Vertex2D(e.getX(), e.getY());
                myCanvas.mix();
                lr.render(new Vertex2D(center), new Vertex2D(initRadPoint));
                myCanvas.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (center != null && initRadPoint != null) {
                    if (canDrawAt(new Vertex2D(e.getX(), e.getY()))) {
                        radPoint = new Vertex2D(e.getX(), e.getY());
                        // Vectors for angle measuring
                        Vertex2D init = new Vertex2D(initRadPoint.x - center.x, center.y - initRadPoint.y);
                        Vertex2D newV = new Vertex2D(e.getX() - center.x, center.y - e.getY());
                        // Graphical hint dot for an arc + diameter line
                        myCanvas.mix();
                        lr.render(new Vertex2D(center), new Vertex2D(radPoint));
                        ar.render(radius, center, init, newV);
                        myCanvas.repaint();
                    } else
                        clear();
                }
            }
        });
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        ar.getLr().setColor(color);
    }

    @Override
    public void clear() {
        center = null;
        radPoint = null;
        initRadPoint = null;
    }

}
