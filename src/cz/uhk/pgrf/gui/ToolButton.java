package cz.uhk.pgrf.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import cz.uhk.pgrf.app.SimpleDraw;

public class ToolButton extends JButton {

	public ToolButton() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				SimpleDraw.tooltip.setText(getToolTipText());
			}
			public void mouseExited(MouseEvent e) {
				SimpleDraw.tooltip.setText("");
			}

		});
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
