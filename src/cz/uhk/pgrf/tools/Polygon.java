package cz.uhk.pgrf.tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import cz.uhk.pgrf.app.Vector2D;
import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.renderers.LineRenderer;

public class Polygon extends GraphicalObject {

	private List<Vector2D> points;
	private Vector2D point;

	public Polygon(Canvas canvas, Color color) {
		super(canvas, color);
		
		myRenderer = new LineRenderer(canvas, color);
		points = new ArrayList<Vector2D>();
		
		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (points.size() == 0) {
					point = new Vector2D(e.getX(), e.getY());
					points.add(point);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				points.add(new Vector2D(e.getX(), e.getY()));
			}
		});
		
		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				myCanvas.mix();
				if (points.size() == 1) {
					Vector2D newP = new Vector2D(e.getX(), e.getY());
					Vector2D p2 = new Vector2D(points.get(points.size() - 1));
					((LineRenderer) myRenderer).render(newP, p2);
				} else {
					// Duplikace kvuli referencim...jinak nefunguje :(
					Vector2D newP1 = new Vector2D(e.getX(), e.getY());
					Vector2D newP2 = new Vector2D(e.getX(), e.getY());
					Vector2D p1 = new Vector2D(points.get(points.size() - 1));
					Vector2D p2 = new Vector2D(points.get(points.size() - 2));
					((LineRenderer) myRenderer).render(newP1, p1);
					((LineRenderer) myRenderer).render(newP2, p2); 
					
				}
				myCanvas.repaint();
			}
		});
	}

	@Override
	public void clear() {
		point = null;
		points.clear();
	}

}
