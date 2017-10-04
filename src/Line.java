import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Line implements GraphicalObject {

	private Vector2D origin;
	private Vector2D end;
	private Color color = Color.white;
	private MouseAdapter handler;
	private Canvas myCanvas;

	Line(int x1, int y1, int x2, int y2) {
		origin = new Vector2D(x1, y1);
		end = new Vector2D(x2, y2);
	}

	Line(int x1, int y1, int x2, int y2, Color color) {
		origin = new Vector2D(x1, y1);
		end = new Vector2D(x2, y2);
		this.color = color;
	}

	public Line(Canvas canvas) {
		myCanvas = canvas;
		handler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (origin == null) {
					origin = new Vector2D(e.getX(), e.getY());
					Brush b = new Brush(myCanvas);
					b.render(origin);
				} else {
					end = new Vector2D(e.getX(), e.getY());
					Brush b = new Brush(myCanvas);
					b.render(end);
				}
				render();
			}
		};
	}

	public void render() {
		if (origin != null && end != null) {
			System.out.println(origin + " " + end);
			float k = (float) (origin.y - end.y) / (float) (end.x - origin.x);
			System.out.println("K:" + k);
			if (k < 1) {
				int deltaX = end.x - origin.x;
				for (int x = 0; x <= deltaX; x++) {
					int y = (int) (k * (int) x);
					myCanvas.getFill().setRGB(origin.x + x, origin.y - y, color.hashCode());
				}
			}
			if (k > 1) {
				int deltaY = origin.y - end.y;
				for (int y = 0; y <= deltaY; y++) {
					int x = (int) (y / (int) k);
					myCanvas.getFill().setRGB(origin.x + x, origin.y - y, color.hashCode());
				}
			}
			myCanvas.repaint();
			origin = null;
			end = null;
		}

	}

	@Override
	public MouseAdapter getHandler() {
		return handler;
	}
}
