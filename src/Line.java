
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Line extends GraphicalObject {

	private Vector2D origin;

	public Line(Canvas canvas, Color color) {
		super(canvas, color);
		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					if (origin == null) {
						origin = new Vector2D(e.getX(), e.getY());
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
					myCanvas.drawInto();
					clear();
			}
		};

		motionHandler = new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {

					// Protoze reference!
					Vector2D or = new Vector2D(origin);
					Vector2D en = new Vector2D(e.getX(), e.getY());

					myCanvas.mix();
					render(or, en);
					myCanvas.repaint();
				}
			}
			

		};
	}

	@Override
	protected void clear() {
		origin = null;
	}

	public void render(Vector2D o, Vector2D e) {

		float dx = e.x - o.x;
		float dy = e.y - o.y;

		if (Math.abs(dy) <= Math.abs(dx)) {
			if (e.x < o.x)
				Vector2D.reverse(o, e);

			float k = (float) dy / dx;
			float fy = (float) o.y;

			for (int x = o.x; x <= e.x; x++) {
				int y = (int) Math.round(fy);
				myCanvas.putPixel(x, y, color);
				fy += k;
			}

		} else {
			if (e.y < o.y)
				Vector2D.reverse(o, e);

			float k = (float) dx / dy;
			float fx = (float) o.x;

			for (int y = o.y; y <= e.y; y++) {
				int x = (int) Math.round(fx);
				myCanvas.putPixel(x, y, color);
				fx += k;
			}
		}
	}
}
