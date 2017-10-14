import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends GraphicalObject {

	private List<Vector2D> points;
	private Vector2D newPoint;
	private Vector2D oldPoint;

	public Polygon(Canvas canvas, Color color) {
		super(canvas, color);
		points = new ArrayList<Vector2D>();
		clickHandler = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				newPoint = new Vector2D(e.getX(), e.getY());
			}
		};
	}

	private Vector2D getPrevious(int iteration) {
		if (points.size() > 0)
			return points.get(points.size() - iteration);
		return points.get(0);
	}

	public void render(Vector2D newPoint) {
		if (points.size() >= 2) {
			new Line(myCanvas, color).render(getPrevious(1), newPoint);
			if (points.size() > 2)
				new Line(myCanvas, color).render(getPrevious(2), newPoint);
		}
		points.add(newPoint);
	}

	@Override
	protected void clear() {
		// TODO Auto-generated method stub

	}

}
