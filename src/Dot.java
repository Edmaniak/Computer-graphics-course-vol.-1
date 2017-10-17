import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dot extends GraphicalObject {

	private Vector2D position;

	public Dot(Canvas canvas, Color color) {
		super(canvas,color);
		clickHandler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				render(new Vector2D(e.getX(), e.getY()));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				myCanvas.drawInto();
			}
		};
	}

	public void render(Vector2D position) {
		System.out.println(position);
		myCanvas.drawPixel(position, color);
		myCanvas.repaint();
	}

	@Override
	protected void clear() {
		
	}

}
