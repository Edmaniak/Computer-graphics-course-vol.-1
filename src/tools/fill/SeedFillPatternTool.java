package tools.fill;

import gui.Canvas;
import renderers.fill.SeedFillPatternRenderer;

import java.awt.*;

public class SeedFillPatternTool extends SeedFillerTool {

    public SeedFillPatternTool(Canvas canvas, Color c) {
        super(canvas, c);
        fillRenderer =  new SeedFillPatternRenderer(canvas, color, pattern);
    }
}
