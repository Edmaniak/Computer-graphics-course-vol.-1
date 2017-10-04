
public class Vector2D {

	int x;
	int y;

	Vector2D(int x, int y) {
		this.x = (int) x;
		this.y = (int) y;
	}
	
	@Override
	public String toString() {
		return "[" + "X: " + x + " Y: " + y + "]";
	}

}
