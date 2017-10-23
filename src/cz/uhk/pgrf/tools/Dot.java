package cz.uhk.pgrf.tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.DotRenderer;

public class Dot extends GraphicalObject {

	public Dot(Canvas canvas, Color color) {
		super(canvas,color);
		myRenderer = new DotRenderer(canvas,color);
		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				((DotRenderer) myRenderer).render(new Vector2D(e.getX(), e.getY()));
				myCanvas.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
			}
		});
	}

	@Override
	public void clear() {
		
	}

}
