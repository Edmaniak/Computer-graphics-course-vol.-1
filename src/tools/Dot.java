package tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import renderers.LineRenderer;

public class Dot extends Tool {
	
	private final LineRenderer lr;

	public Dot(Canvas canvas, Color color) {
		super(canvas,color);
		instruction = "Click the mouse.";
		lr = new LineRenderer(canvas,color);
		setMainRenderer(lr, color);
		setClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Vertex2D point = new Vertex2D(e.getX(), e.getY());
				lr.render(new Vertex2D(point),new Vertex2D(point));
				myCanvas.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				System.out.println(myCanvas.getColorAt(e.getX(),e.getY()));
			}
		});
	}

	@Override
	public void clear() {
		
	}

}
