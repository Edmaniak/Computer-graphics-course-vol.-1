import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

public abstract class GraphicalObject {

	protected Color color = Color.white;
	public MouseAdapter clickHandler;
	public MouseAdapter motionHandler;
	protected final Canvas myCanvas;
	public Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

	public GraphicalObject(Canvas canvas, Color color) {
		myCanvas = canvas;
		this.color = color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	protected abstract void clear();

	protected boolean canDrawAt(Vector2D point) {
		if (point.x >= 0 && point.x < myCanvas.getWidth() && point.y >= 0 && point.y < myCanvas.getHeight())
			return true;
		clear();
		return false;
	}

}
