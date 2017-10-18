
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends GraphicalObject {

	private List<Vector2D> points;
	private Vector2D point;

	public Polygon(Canvas canvas, Color color) {
		super(canvas, color);
		points = new ArrayList<Vector2D>();
		
		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (points.size() == 0) {
					point = new Vector2D(e.getX(), e.getY());
					points.add(point);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
				points.add(new Vector2D(e.getX(), e.getY()));
			}
		};
		
		motionHandler = new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				myCanvas.mix();
				if (points.size() == 1) {
					Vector2D newP = new Vector2D(e.getX(), e.getY());
					Vector2D p2 = new Vector2D(points.get(points.size() - 1));
					renderArm(newP, p2);
				} else {
					// Duplikace kvuli referencim...jinak nefunguje :(
					Vector2D newP1 = new Vector2D(e.getX(), e.getY());
					Vector2D newP2 = new Vector2D(e.getX(), e.getY());
					Vector2D p1 = new Vector2D(points.get(points.size() - 1));
					Vector2D p2 = new Vector2D(points.get(points.size() - 2));
					renderArm(newP1, p1);
					renderArm(newP2, p2);
					
				}
				myCanvas.repaint();
			}
		};
	}

	public void renderArm(Vector2D o, Vector2D e) {

		float dx = e.x - o.x;
		float dy = e.y - o.y;

		if (Math.abs(dy) <= Math.abs(dx)) {
			if (o.x == e.x && o.y == e.y) {
				myCanvas.putPixel(o.x, o.y, color);
			} else {
				if (e.x < o.x) 
					Vector2D.reverse(o, e);

				float k = (float) dy / dx;
				float fy = (float) o.y;

				for (int x = o.x; x <= e.x; x++) {
					int y = (int) Math.round(fy);
					myCanvas.putPixel(x, y, color);
					fy += k;
				}
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

	@Override
	protected void clear() {
		point = null;
		points.clear();
	}

}
