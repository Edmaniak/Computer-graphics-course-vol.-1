package tools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.Vertex2D;
import gui.Canvas;
import renderers.LineRenderer;

public class BrushTool extends Tool {

	private Vertex2D lastPosition;
	private final LineRenderer lr;

	public BrushTool(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the mouse.";
		lr = new LineRenderer(canvas, color);
		setMainRenderer(lr, color);
		setMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (lastPosition != null)
					lr.render(lastPosition, new Vertex2D(e.getX(), e.getY()));
				lastPosition = new Vertex2D(e.getX(), e.getY());
				myCanvas.repaint();

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
	public void doAfterSwitch() {

	}

	@Override
	public void clear() {
		lastPosition = null;
	}
}
