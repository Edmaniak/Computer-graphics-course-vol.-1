import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Line extends GraphicalObject {

	private Vector2D origin;
	private Vector2D end;
	private List<Vector2D> renderedPoints;

	public Line(Canvas canvas, Color color) {
		super(canvas, color);
		renderedPoints = new ArrayList<>();
		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (origin == null) {
					origin = new Vector2D(e.getX(), e.getY());
					renderedPoints.add(new Vector2D(e.getX(), e.getY()));
				}
				myCanvas.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				clear();
			}

		};

		motionHandler = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				end = new Vector2D(e.getX(), e.getY());
				renderedPoints.add(end);
				render(origin, end);
				for (int i = 0; i < renderedPoints.size()-1; i+=2) {
					render(renderedPoints.get(i),renderedPoints.get(i+1));
				}
				myCanvas.repaint();
			};
		};

	}

	private void clear() {
		origin = null;
		end = null;
	}

	public void render(Vector2D x1y1, Vector2D x2y2) {
		if (x1y1 != null && end != null) {
			int dx = x2y2.x - x1y1.x;
			int dy = x2y2.y - x1y1.y;

			if (Math.abs(dy) <= Math.abs(dx)) {
				if (x2y2.x < x1y1.x)
					Vector2D.reverse(x1y1, x2y2);

				float k = (float) dy / dx;
				float fy = (float) x1y1.y;

				for (int x = x1y1.x; x <= x2y2.x; x++) {
					int y = (int) Math.round(fy);
					myCanvas.drawPixel(x, y, color);
					fy += k;
				}
			} else {
				if (end.y < x1y1.y)
					Vector2D.reverse(x1y1, x2y2);

				float k = (float) dx / dy;
				float fx = (float) x1y1.x;

				for (int y = x1y1.y; y <= x2y2.y; y++) {
					int x = (int) Math.round(fx);
					myCanvas.drawPixel(x, y, color);
					fx += k;
				}

			}
		}
	}
}
