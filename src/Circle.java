
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Circle extends GraphicalObject {

	private Vector2D center;
	private Vector2D radPoint;

	public Circle(Canvas canvas, Color color) {
		super(canvas, color);
		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (center == null)
					center = new Vector2D(e.getX(), e.getY());
				else {
					radPoint = new Vector2D(e.getX(), e.getY());
					int radius = (int)Math.sqrt(Math.pow((radPoint.x - center.x),2)+Math.pow((radPoint.y - center.y),2));
					render(center, radius);	
				}
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
		drawSymetrically(center.x, center.y, x, y);
		while (x < y) {
			x++;
			if (d < 0) {
				d += 2 * x + 1;
			} else {
				y--;
				d += 2 * (x - y) + 1;
			}
			drawSymetrically(center.x, center.y, x, y);
		}
		myCanvas.repaint();
		clear();
	}

	public void drawSymetrically(int xcenter, int ycenter, int x, int y) {
		myCanvas.drawPixel(xcenter + x, ycenter + y, color);
		myCanvas.drawPixel(xcenter - x, ycenter + y, color);
		myCanvas.drawPixel(xcenter + x, ycenter - y, color);
		myCanvas.drawPixel(xcenter - x, ycenter - y, color);
		myCanvas.drawPixel(xcenter + y, ycenter + x, color);
		myCanvas.drawPixel(xcenter - y, ycenter + x, color);
		myCanvas.drawPixel(xcenter + y, ycenter - x, color);
		myCanvas.drawPixel(xcenter - y, ycenter - x, color);
	}

}
