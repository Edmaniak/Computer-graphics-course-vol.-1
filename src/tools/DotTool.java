package tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import renderers.LineRenderer;

public class DotTool extends Tool {
	
	private final LineRenderer lr;

	public DotTool(Canvas canvas, Color color) {
		super(canvas,color);
		instruction = "Click the mouse.";
		lr = new LineRenderer(canvas);
		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Vertex2D point = new Vertex2D(e.getX(), e.getY());
				lr.render(new Vertex2D(point),new Vertex2D(point), color);
				myCanvas.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
			}
		});
	}

	@Override
	public void doAfterSwitch() {
		
	}

	@Override
	public void clear() {

	}
}
