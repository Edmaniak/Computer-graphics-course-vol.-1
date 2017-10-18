
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Circle extends GraphicalObject {

	private Vector2D center;
	private Vector2D radPoint;
	private int radiusss;

	public Circle(Canvas canvas, Color color) {
		super(canvas, color);

		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (center == null)
					center = new Vector2D(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				clear();

			}
		};

		motionHandler = new MouseAdapter() {

			public void mouseDragged(MouseEvent e) {
				radPoint = new Vector2D(e.getX(), e.getY());
				int radius = (int) Math
						.sqrt(Math.pow((radPoint.x - center.x), 2) + Math.pow((radPoint.y - center.y), 2));
				myCanvas.mix();
				render(center, radius);
				myCanvas.repaint();
				radiusss = radius;
			}

		};

	}

	@Override
	protected void clear() {
		center = null;
		radPoint = null;
	}

	public void render(Vector2D center, int radius) {
		int x = 0;
		int y = radius;
		int d = 1 - radius;
		drawSymetrically(center.x, center.y, x, y, 90);
		while (x < y) {
			x++;
			if (d < 0) {
				d += 2 * x + 1;
			} else {
				y--;
				d += 2 * (x - y) + 1;
			}
			drawSymetrically(center.x, center.y, x, y, 90);
		}
	}

	public void drawSymetrically(int xcenter, int ycenter, int x, int y, int angleNeeded) {
		// 1 (0 - 45)
		myCanvas.putPixel(xcenter + y, ycenter - x, color);
		// 2
		myCanvas.putPixel(xcenter + x, ycenter - y, color);
		// 3
		myCanvas.putPixel(xcenter - x, ycenter - y, color);
		// 4
		myCanvas.putPixel(xcenter - y, ycenter - x, color);
		// 5
		myCanvas.putPixel(xcenter - y, ycenter + x, color);
		// 6
		myCanvas.putPixel(xcenter - x, ycenter + y, color);
		// 7
		myCanvas.putPixel(xcenter + x, ycenter + y, color);
		// 8
		myCanvas.putPixel(xcenter + y, ycenter + x, color);

	}

}
