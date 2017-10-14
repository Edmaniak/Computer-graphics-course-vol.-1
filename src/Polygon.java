import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends GraphicalObject {

	private List<Vector2D> points;
	private Vector2D point;
	private Vector2D oldPoint;

	public Polygon(Canvas canvas, Color color) {
		super(canvas, color);
		points = new ArrayList<Vector2D>();
		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point = new Vector2D(e.getX(), e.getY());
				points.add(point);
				render(point);
			}
		};
	}

	private Vector2D getPrevious(int i) {
		System.out.println(points.size() - i - 1);
		return points.get(points.size() - i - 1);
	}

	public void render(Vector2D newPoint) {

		if (points.size() == 2) {
			renderArm(getPrevious(1), newPoint);
		}
		if (points.size() > 2) {
			renderArm(getPrevious(2), newPoint);
			renderArm(getPrevious(1), newPoint);
		}
		myCanvas.repaint();

	}

	private void renderArm(Vector2D x1y1, Vector2D x2y2) {

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
			if (x2y2.y < x1y1.y)
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

	@Override
	protected void clear() {
		point = null;
		points.clear();
	}

}
