package cz.uhk.pgrf.tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.LineRenderer;

public class Brush extends GraphicalObject {

	private Vector2D lastPosition;

	public Brush(Canvas canvas, Color color) {
		super(canvas, color);
		myRenderer = new LineRenderer(canvas, color);
		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					if (lastPosition != null)
						((LineRenderer) myRenderer).render(lastPosition, new Vector2D(e.getX(), e.getY()));
					lastPosition = new Vector2D(e.getX(), e.getY());
					myCanvas.repaint();
				}
			}
		});

		setClickHandler(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();
			}
		});
	}

	@Override
	public void clear() {
		lastPosition = null;
	}

}
