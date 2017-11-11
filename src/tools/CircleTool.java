package tools;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import objects.Vertex2D;
import gui.Canvas;
import renderers.CircleRenderer;

public class CircleTool extends Tool {

	private Vertex2D center;
	private Vertex2D radPoint;
	private int radius;
	private final CircleRenderer cr;

	public CircleTool(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the mouse";
		cr = new CircleRenderer(canvas);
		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (center == null)
					center = new Vertex2D(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();

			}
		});

		defineMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				radPoint = new Vertex2D(e.getX(), e.getY());
				radius = (int) sqrt(pow((radPoint.x - center.x), 2) + pow((radPoint.y - center.y), 2));
				myCanvas.mix();
				cr.render(center, radius, color);
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
		center = null;
		radPoint = null;
		radius = 0;
	}
}