import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;

public class ColorPicker extends JColorChooser {

	private Color choosedColor = Color.white;

	class OkListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			choosedColor = getColor();
		}
	}

	public void openDialog() {
		JDialog colorDialog = JColorChooser.createDialog(null, "Pick a color", true, this, new OkListener(), null);
		colorDialog.setVisible(true);
	}
	
	public Color getChoosedColor() {
		return choosedColor;
	}
}
