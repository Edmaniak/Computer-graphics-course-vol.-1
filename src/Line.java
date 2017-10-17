import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Line extends GraphicalObject {

	private Vector2D origin;
	private Vector2D end;

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
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					myCanvas.drawInto();
					clear();
				}
			}
		};

		motionHandler = new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (canDrawAt(new Vector2D(e.getX(), e.getY()))) {
					end = new Vector2D(e.getX(), e.getY());
					myCanvas.mix();
					render();
					myCanvas.repaint();
				}
			}
		};

	}

	@Override
	protected void clear() {
		origin = null;
		end = null;
	}

	public void render() {
		System.out.println("o " + origin + " "+ "e"+ end );
		float dx = end.x - origin.x;
		float dy = end.y - origin.y;
		System.out.println("dx" + dx + " " + "dy" + dy);
		if (Math.abs(dy) <= Math.abs(dx)) {
			if (origin.x == end.x && origin.y == end.y) {
				myCanvas.putPixel(origin.x, origin.y, color);
			} else {
				if (end.x < origin.x) {
					int tmp = end.x;
					end.x = origin.x;
					origin.x = tmp;
					tmp = end.y;
					end.y = origin.y;
					origin.y = tmp;
				}

				float k = (float) dy / dx;
				float fy = (float) origin.y;

				for (int x = origin.x; x <= end.x; x++) {
					int y = (int) Math.round(fy);
					myCanvas.putPixel(x, y, color);
					fy += k;
				}
				System.out.println("F" + k);
			}
		} else {
			if (end.y < origin.y) {
				int tmp = end.x;
				end.x = origin.x;
				origin.x = tmp;
				tmp = end.y;
				end.y = origin.y;
				origin.y = tmp;
			}

			float k = (float) dx / dy;
			float fx = (float) origin.x;

			for (int y = origin.y; y <= end.y; y++) {
				int x = (int) Math.round(fx);
				myCanvas.putPixel(x, y, color);
				fx += k;
			}
			System.out.println("S" + k);

		}

	}
}
