import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	private BufferedImage fill;

	private Color bgColor;
	private Dimension dimensions;
	private Color defColor = Color.BLACK;

	public Canvas(Dimension d) {
		setDimensions(d);
		fill = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(d);
		clear();
		
	}

	public Canvas(Dimension d, Color bgColor) {
		this(d);
		setBgColor(bgColor);
	}

	public void present(Graphics graphics) {
		graphics.drawImage(fill, 0, 0, null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		present(g);
	}

	public void clear() {
		setBgColor(defColor);
		repaint();
	}
	
	public BufferedImage getFill() {
		return fill;
	}
	
	public void draw() {
		repaint();
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
		Graphics newBgCol = fill.getGraphics();
		newBgCol.setColor(bgColor);
		newBgCol.fillRect(0, 0, dimensions.width, dimensions.height);
		repaint();
	}

	public Dimension getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimension dimensions) {
		this.dimensions = dimensions;
	}
}
