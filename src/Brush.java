
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Brush extends GraphicalObject {

	private Vector2D lastPosition;

	public Brush(Canvas canvas, Color color) {
		super(canvas, color);
		motionHandler = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					if (lastPosition != null)
						render(lastPosition, new Vector2D(e.getX(), e.getY()));
					lastPosition = new Vector2D(e.getX(), e.getY());
				}
			}
		};

		clickHandler = new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();
			}
		};
	}

	public void render(Vector2D x1y1, Vector2D x2y2) {
		if (x1y1 != null && lastPosition != null) {
			int dx = x2y2.x - x1y1.x;
			int dy = x2y2.y - x1y1.y;

			if (Math.abs(dy) <= Math.abs(dx)) {
				if (x2y2.x < x1y1.x)
					Vector2D.reverse(x1y1, x2y2);
				
				float k = (float) dy / dx;
				float fy = (float) x1y1.y;

				for (int x = x1y1.x; x <= x2y2.x; x++) {
					int y = (int) Math.round(fy);
					myCanvas.putPixel(x, y, color);
					fy += k;
				}
			} else {
				if (x2y2.y < x1y1.y)
					Vector2D.reverse(x1y1, x2y2);

				float k = (float) dx / dy;
				float fx = (float) x1y1.x;

				for (int y = x1y1.y; y <= x2y2.y; y++) {
					int x = (int) Math.round(fx);
					myCanvas.putPixel(x, y, color);
					fx += k;
				}

			}
		}
		myCanvas.repaint();
	}

	@Override
	protected void clear() {
		lastPosition = null;
	}

}
