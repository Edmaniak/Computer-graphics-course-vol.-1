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
				render(new Vector2D(e.getX(), e.getY()));
			}
		};
	}
	
	public void render(Vector2D position) {
		myCanvas.drawPixel(position, color);
		myCanvas.repaint();
	}

}
