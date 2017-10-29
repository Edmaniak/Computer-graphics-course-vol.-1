package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import app.SimpleDraw;

public class ToolButton extends JButton {

    final SimpleDraw parent;

    private ToolButton(SimpleDraw parent) {
        this.parent = parent;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                parent.setTooltip(getToolTipText());
            }

            public void mouseExited(MouseEvent e) {
                parent.setTooltip("");
            }
        });

        addActionListener(e -> {
            if (parent.getSelectedTool() != null)
                parent.setInstruction(parent.getSelectedTool().getInstruction());
        });
    }

    public ToolButton(String toolTip, SimpleDraw parent) {
        this(parent);
        setToolTipText(toolTip);
    }

    public ToolButton(String toolTip, String icon, SimpleDraw parent) {
        this(parent);
        setToolTipText(toolTip);
        setIcon(new ImageIcon(icon));
    }

}
