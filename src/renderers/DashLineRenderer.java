package renderers;

import gui.Canvas;
import objects.Vertex2D;

import java.awt.*;

public class DashLineRenderer extends LineRenderer {

    private int space;
    private int state;

    public DashLineRenderer(Canvas canvas, int space) {
        super(canvas);
        this.space = space;
    }

    @Override
    public void render(Vertex2D o, Vertex2D e, Color color) {
        float dx = e.x - o.x;
        float dy = e.y - o.y;

        if (Math.abs(dy) <= Math.abs(dx)) {
            // one point line
            if ((o.x == e.x) && (o.y == e.y))
                myCanvas.putPixel(o.x, o.y, color);
            else if (e.x < o.x)
                Vertex2D.reverse(o, e);

            float k = dy / dx;
            float fy = (float) o.y;

            for (int x = o.x; x <= e.x; x++) {
                int y = Math.round(fy);
                decide(x, y, color);
                fy += k;
            }

        } else {
            if (e.y < o.y)
                Vertex2D.reverse(o, e);

            float k = dx / dy;
            float fx = (float) o.x;

            for (int y = o.y; y <= e.y; y++) {
                int x = Math.round(fx);
                decide(x, y, color);
                fx += k;
            }
        }
    }

    // Primitivní kritérium pro rozhodnutí o tom zda-li pixel umístit či ne na základně vzoru
    private void decide(int x, int y, Color color) {
        if (state <= space) {
            myCanvas.putPixel(x, y, color);
        }
        state++;
        if (state == space * 2)
            state = 0;
    }
}
