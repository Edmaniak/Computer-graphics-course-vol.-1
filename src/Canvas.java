import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseMotionListener {
	
	private BufferedImage buffer;
	private Color bgColor;
	private Dimension dimensions;
	private MouseListener mouseEventHandler;
	private MouseMotionListener mouseMotionHandler;

	public Canvas(Dimension d) {
		setDimensions(d);
		buffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(d);
		clear();
		addMouseMotionListener(this);
	}

	public Canvas(Dimension d, Color bgColor) {
		this(d);
		setBgColor(bgColor);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(buffer, 0, 0, null);
	}
	
	public void clear() {
		setBgColor(AppColor.DEFAULT_BG);
		repaint();
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
		Graphics newBgCol = buffer.getGraphics();
		newBgCol.setColor(bgColor);
		newBgCol.fillRect(0, 0, dimensions.width, dimensions.height);
		repaint();
	}
	
	public void setMouseClickHandler(MouseAdapter mouseHandler) {
		removeMouseListener(mouseEventHandler);
		addMouseListener(mouseHandler);
		mouseEventHandler = mouseHandler;
	}
	
	public void setMouseMotionHandler(MouseAdapter mouseHandler) {
		removeMouseMotionListener(mouseMotionHandler);
		addMouseMotionListener(mouseHandler);
		mouseMotionHandler = mouseHandler;
	}
	
	public void drawPixel(Vector2D position,Color color) {
		buffer.setRGB(position.x, position.y, color.hashCode());
	}
	
	public void drawPixel(int x, int y,Color color) {
		buffer.setRGB(x, y, color.hashCode());
	}

	public Dimension getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimension dimensions) {
		this.dimensions = dimensions;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		SimpleDraw.tooltip.setText("X:" + e.getX() + " Y: " + e.getY() );
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		SimpleDraw.tooltip.setText("X:" + e.getX() + " Y: " + e.getY() );
	}
}
