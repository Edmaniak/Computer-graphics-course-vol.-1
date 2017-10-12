import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;

public abstract class GraphicalObject {
	
	protected Color color = Color.white;
	public MouseAdapter clickHandler;
	public MouseAdapter motionHandler;
	protected final Canvas myCanvas;
	public Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
	
	public GraphicalObject(Canvas canvas,Color color) {
		myCanvas = canvas;
		this.color = color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
