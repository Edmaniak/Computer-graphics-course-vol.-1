package tools;

import gui.Canvas;
import objects.Edge;
import objects.Vertex2D;
import renderers.LineRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LineTool extends Tool {

	private Vertex2D origin;
	private Vertex2D end;
	private final LineRenderer lr;
	private List<Edge> edges;

	public LineTool(Canvas canvas, Color color) {
		super(canvas, color);
		instruction = "Drag the mouse.";
		lr = new LineRenderer(myCanvas);
		edges = new ArrayList<>();
		defineClickHandler(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (origin == null) {
					// Ugly block needed for one point line
					origin = new Vertex2D(e.getX(), e.getY());
					end = new Vertex2D(e.getX(), e.getY());
					myCanvas.mix();
					lr.render(new Vertex2D(origin), new Vertex2D(end), color);
					myCanvas.repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				edges.add(new Edge(origin, end));
				myCanvas.drawInto();
				clear();
				if(edges.size() == 2) {
					Vertex2D inter = edges.get(0).getIntersection(edges.get(1));
					System.out.println(inter);
				}
			}
		});
		setMotionHandler(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				end = new Vertex2D(e.getX(), e.getY());
				myCanvas.mix();
				lr.render(new Vertex2D(origin), new Vertex2D(end), color);
				myCanvas.repaint();
			}
		});
	}

	@Override
	public void doAfterSwitch() {

	}

	@Override
	public void clear() {
		origin = null;
		end = null;
	}
}
