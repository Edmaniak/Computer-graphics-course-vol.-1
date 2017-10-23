package cz.uhk.pgrf.app;

import static java.lang.Math.acos;
import static java.lang.Math.atan2;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
public class Vector2D {

	public int x;
	public int y;

	public Vector2D(int x, int y) {
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

	public Vector2D sum(Vector2D second) {
		x += second.x;
		y += second.y;
		return this;
	}

	public static double angle(Vector2D first, Vector2D second) {
		double angle =  toDegrees(atan2(first.y, first.x) - atan2(second.y,  second.x));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}

	public int scale() {
		return (int) sqrt(pow(x, 2) + pow(y, 2));
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

	public static int distance(Vector2D one, Vector2D second) {
		return (int) sqrt(pow(one.x - second.x, 2) + pow(one.y - second.y, 2));
	}

}
