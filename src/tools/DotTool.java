package tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import objects.Vertex2D;
import gui.Canvas;
import renderers.line.LineRenderer;

public class DotTool extends Tool {
	
	private final LineRenderer lr;

	public DotTool(Canvas canvas, Color color) {
		super(canvas,color);

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
				myCanvas.getPolygonAt(new Vertex2D(e.getX(),e.getY()));
			}
		});
	}

	@Override
	public String getInstruction() {
		return "Click the mouse to make point dots";
	}

	@Override
	public void doAfterSwitchOut() {
		
	}

	@Override
	public void doOnSwitchIn() {

	}

	@Override
	public void clear() {

	}
}
