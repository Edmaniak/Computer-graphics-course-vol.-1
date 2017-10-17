
public class Vector2D {

	int x;
	int y;

	Vector2D(int x, int y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	public Vector2D(Vector2D end) {
		x = end.x;
		y = end.y;
	}

	@Override
	public String toString() {
		return "[" + "X: " + x + " Y: " + y + "]";
	}

	public static void reverse(Vector2D first, Vector2D second) {
		// x values switch
		int val = first.x;
		first.x = second.x;
		second.x = val;
		// y values switch
		val = first.y;
		first.y = second.y;
		second.y = val;
	}

}
