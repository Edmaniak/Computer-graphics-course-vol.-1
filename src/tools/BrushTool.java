package tools;

import objects.Vertex2D;
import gui.Canvas;
import renderers.line.LineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BrushTool extends Tool {

	private Vertex2D lastPosition;
	private final LineRenderer lr;

	public BrushTool(Canvas canvas, Color color) {
		super(canvas, color);
		lr = new LineRenderer(canvas);
		defineMotionHandler(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (lastPosition != null)
					lr.render(lastPosition, new Vertex2D(e.getX(), e.getY()), color);
				lastPosition = new Vertex2D(e.getX(), e.getY());
				myCanvas.repaint();

			}
		});

		defineClickHandler(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();
			}
		});
	}

	@Override
	public String getInstruction() {
		return "Drag the mouse";
	}

	@Override
	public void doAfterSwitchOut() {

	}

	@Override
	public void doOnSwitchIn() {

	}

	@Override
	public void clear() {
		lastPosition = null;
	}
}
