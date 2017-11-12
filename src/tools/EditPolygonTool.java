package tools;

import gui.Canvas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditPolygonTool extends Tool {

    private Polygon polygon;

    public EditPolygonTool(Canvas canvas, Color color) {
        super(canvas, color);


    }

    @Override
    public void doAfterSwitchOut() {

    }

    @Override
    public void doOnSwitchIn() {

    }

    @Override
    public void clear() {

    }
}
