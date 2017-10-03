import java.awt.Color;
import java.awt.image.BufferedImage;

public class Line implements GraphicalObject {

	private Vector2D origin;
	private Vector2D end;
	private Color color = Color.white;

	Line(int x1, int y1, int x2, int y2) {
		origin = new Vector2D(x1, y1);
		end = new Vector2D(x2, y2);
	}

	Line(int x1, int y1, int x2, int y2, Color color) {
		origin = new Vector2D(x1, y1);
		end = new Vector2D(x2, y2);
		this.color = color;
	}

	@Override
	public void render(BufferedImage bf) {
		double k = ((end.y - origin.y) / (end.x - origin.x));
		for (float x = origin.x; x <= end.x; x++) {
			int y = (int)k * (int)x;
			bf.setRGB((int) x, y, color.hashCode());
		}
	}
}
