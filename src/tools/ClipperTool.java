package tools;

import gui.Canvas;
import renderers.DashLineRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClipperTool extends PolygonTool {

    private JButton clip;

    public ClipperTool(Canvas canvas, Color color) {
        super(canvas, color);
        lr = new DashLineRenderer(canvas, 5);

    }

    @Override
    public void doAfterOut() {
        myCanvas.remove(clip);
        myCanvas.repaint();
    }

    @Override
    public void doOnSwitchIn() {
        clip = new JButton("-CLIP-");
        clip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        myCanvas.add(clip);
    }

    @Override
    public void clear() {

    }
}
