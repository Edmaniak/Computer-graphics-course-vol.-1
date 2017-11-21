package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import app.SimpleDraw;

public class ToolButton extends JButton {

    private ToolButton(ToolGroup toolGroup) {

        //Style
        setFocusPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SimpleDraw.gui.setTooltip(getToolTipText());
            }

            public void mouseExited(MouseEvent e) {
                SimpleDraw.gui.setTooltip(" ");
            }
        });

        addActionListener(e -> {
            if (SimpleDraw.app.getSelectedTool() != null)
                SimpleDraw.gui.setInstruction(SimpleDraw.app.getSelectedTool().getInstruction());
        });

        toolGroup.add(this);

    }

    public ToolButton(String toolTip, ToolGroup toolGroup) {
        this(toolGroup);
        setToolTipText(toolTip);
    }

    public ToolButton(String toolTip, String icon, ToolGroup toolGroup) {
        this(toolGroup);
        setToolTipText(toolTip);
        setIcon(new ImageIcon(icon));
    }

}
