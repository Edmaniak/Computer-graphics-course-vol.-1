package tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import renderers.LineRenderer;

public class Line extends GraphicalObject {

	private Vertex2D origin;
	private Vertex2D end;
	private final LineRenderer lr;

	public Line(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the mouse.";
		lr = new LineRenderer(myCanvas, color);
		setMainRenderer(lr, color);
		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (origin == null) {
					// Ugly block needed for one point line
					origin = new Vertex2D(e.getX(), e.getY());
					end = new Vertex2D(e.getX(), e.getY());
					myCanvas.mix();
					lr.render(new Vertex2D(origin), new Vertex2D(end));
					myCanvas.repaint();

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
				end = new Vertex2D(e.getX(), e.getY());
				myCanvas.mix();
				lr.render(new Vertex2D(origin), new Vertex2D(end));
				myCanvas.repaint();
			}
		});
	}

	@Override
	public void clear() {
		origin = null;
		end = null;
	}

}
