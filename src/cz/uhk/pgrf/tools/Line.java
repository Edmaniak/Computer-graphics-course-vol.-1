package cz.uhk.pgrf.tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.LineRenderer;

public class Line extends GraphicalObject {

	private Vector2D origin;

	public Line(Canvas canvas, Color color) {
		super(canvas, color);
		myRenderer = new LineRenderer(myCanvas, color);
		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					if (origin == null) {
						origin = new Vector2D(e.getX(), e.getY());
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();
			}
		});
		setMotionHandler(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {

					// Protoze reference!
					Vector2D point1 = new Vector2D(origin);
					Vector2D point2 = new Vector2D(e.getX(), e.getY());

					myCanvas.mix();
					((LineRenderer) myRenderer).render(point1, point2);
					myCanvas.repaint();
				}
			}
		});
	}

	@Override
	public void clear() {
		origin = null;
	}

}
