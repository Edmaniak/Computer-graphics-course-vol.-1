package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import app.SimpleDraw;

public class ToolButton extends JButton {

    private ToolButton() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SimpleDraw.gui.setTooltip(getToolTipText());
            }

            public void mouseExited(MouseEvent e) {
                SimpleDraw.gui.setTooltip("");
            }
        });

        addActionListener(e -> {
            if (SimpleDraw.gui.getSelectedTool() != null)
                SimpleDraw.gui.setInstruction(SimpleDraw.gui.getSelectedTool().getInstruction());
        });

        // Tool button is exclusively for SimpleDraw toolbar, so we can add it to the toolbar here
        SimpleDraw.gui.getToolBar().add(this);
    }

    public ToolButton(String toolTip) {
        this();
        setToolTipText(toolTip);
    }

    public ToolButton(String toolTip, String icon) {
        this();
        setToolTipText(toolTip);
        setIcon(new ImageIcon(icon));

    }

}
