package tools;

import objects.Vertex2D;
import gui.Canvas;
import renderers.ArcRenderer;
import renderers.line.LineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class ArcTool extends Tool {

	private Vertex2D center;
	private Vertex2D radPoint;
	private Vertex2D initRadPoint;
	private final ArcRenderer ar;
	private final LineRenderer lr;
	private int radius;

	public ArcTool(Canvas canvas, Color color) {
		super(canvas, color);
		ar = new ArcRenderer(canvas);
		lr = new LineRenderer(canvas);

		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (center == null)
					center = new Vertex2D(e.getX(), e.getY());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (radPoint != null) {
					// mazani cerveneho voditka
					lr.render(center, radPoint, color.BLACK);
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
					ar.render(radius, center, initRadPoint, initRadPoint, color);
					myCanvas.repaint();
				}
			}

		});
		defineMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				initRadPoint = new Vertex2D(e.getX(), e.getY());
				myCanvas.mix();
				lr.render(new Vertex2D(center), new Vertex2D(initRadPoint), Color.RED);
				myCanvas.repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (center != null && initRadPoint != null) {
					radPoint = new Vertex2D(e.getX(), e.getY());
					// Vectors for angle measuring
					Vertex2D init = new Vertex2D(initRadPoint.x - center.x, center.y - initRadPoint.y);
					Vertex2D newV = new Vertex2D(e.getX() - center.x, center.y - e.getY());
					// Graphical hint dot for an arc + diameter line
					myCanvas.mix();
					lr.render(new Vertex2D(center), new Vertex2D(radPoint), color);
					ar.render(radius, center, init, newV, color);
					myCanvas.repaint();
				}
			}
		});
	}

	@Override
	public String getInstruction() {
		return "Drag an diameter and then move the mouse.";
	}

	@Override
	public void doAfterSwitchOut() {

	}

	@Override
	public void doOnSwitchIn() {

	}

	@Override
	public void clear() {
		center = null;
		radPoint = null;
		initRadPoint = null;
	}
}
