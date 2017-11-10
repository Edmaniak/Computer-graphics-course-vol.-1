package objects;

import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;

public class Vertex2D {

	public int x;
	public int y;

	public Vertex2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Vertex2D(Vertex2D end) {
		x = end.x;
		y = end.y;
	}

	@Override
	public String toString() {
		return "[" + "X: " + x + " Y: " + y + "]";
	}

	public static double angle(Vertex2D first, Vertex2D second) {
		double angle = toDegrees(atan2(first.y, first.x) - atan2(second.y, second.x));

		if (angle < 0)
			angle += 360;

		return angle;
	}



	public static void reverse(Vertex2D first, Vertex2D second) {
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
