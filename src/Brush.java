import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Brush implements GraphicalObject {

	private Color color = Color.white;
	private Vector2D position;
	private MouseAdapter handler;
	private Canvas myCanvas;
	
	public Brush(Canvas canvas) {
		myCanvas = canvas;
		handler = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				render(new Vector2D(e.getX(),e.getY()));
			}
		};
	}

	public void render(Vector2D position) {
		System.out.println(position);
		myCanvas.getFill().setRGB((int) position.x, (int) position.y, color.hashCode());
		myCanvas.repaint();
	}

	@Override
	public MouseAdapter getHandler() {
		return handler;
	}

}
