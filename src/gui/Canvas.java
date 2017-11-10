package gui;

import app.SimpleDraw;
import objects.Polygon;
import objects.Vertex2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel implements MouseMotionListener {

	private BufferedImage activeBuffer;
	private BufferedImage mainBuffer;
	private Color bgColor;
	private Dimension dimensions;
	private MouseListener mouseEventHandler;
	private MouseMotionListener mouseMotionHandler;
	private List<Polygon> polygons;

	// constr with default color
	public Canvas(Dimension d) {
		setDimensions(d);
		polygons = new ArrayList<>();
		activeBuffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
		mainBuffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(d);
		addMouseMotionListener(this);
	}

	// constr with custom color - not used really
	public Canvas(Dimension d, Color bgColor) {
		this(d);
		setBgColor(bgColor);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(activeBuffer, 0, 0, null);
	}

	public void clear(Color c) {
		activeBuffer = new BufferedImage(dimensions.width, dimensions.height, BufferedImage.TYPE_INT_RGB);
		mainBuffer = new BufferedImage(dimensions.width, dimensions.height, BufferedImage.TYPE_INT_RGB);
		polygons.clear();
		setBgColor(c);
		repaint();
	}

	// Metoda pro mix originalniho bufferu a dynamicky kreslenych grafickych objektu
	public void mix() {
		Graphics g = activeBuffer.getGraphics();
		g.drawImage(mainBuffer, 0, 0, null);
		repaint();
	}

	// Zapise hotovy graficky objekt tvoreny v ramci mouse I/O do hlavniho bufferu
	public void drawInto() {
		mainBuffer = deepCopy(activeBuffer);
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
		Graphics newBgCol = activeBuffer.getGraphics();
		newBgCol.setColor(bgColor);
		newBgCol.fillRect(0, 0, dimensions.width, dimensions.height);
		drawInto();
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

	// Prevzata metoda pro kopirovani bufferu do jineho bufferu
	private static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	/////// Main methods for drawing pixels into the canvas
	public void putPixel(Vertex2D position, Color color) {
		putPixel(position.x, position.y, color);
	}

	public void putPixel(int x, int y, Color color) {
		if (canDrawAt(x,y))
			activeBuffer.setRGB(x, y, color.hashCode());
	}

	public void putText(String text, int x, int y) {
		activeBuffer.getGraphics().drawString(text, x, y);
		repaint();
	}

	public void putText(Object text, int x, int y) {
		putText(text.toString(), x, y);
	}

	////// --------------------------------------------
	// Super method for every case of escaping this canvas
	private boolean canDrawAt(int x, int y) {
		if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight())
			return true;
		return false;
	}

	public int getCanvasColorAt(int x, int y) {
		System.out.println(mainBuffer.getRGB(x,y));
		return mainBuffer.getRGB(x, y);
	}

	public int getActiveColorAt(int x, int y) {
		return activeBuffer.getRGB(x,y);
	}

	public Dimension getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimension dimensions) {
		this.dimensions = dimensions;
	}

	// Shows mouse coordinates in the bottom panel
	@Override
	public void mouseDragged(MouseEvent e) {
		SimpleDraw.gui.setTooltip("X:" + e.getX() + " Y: " + e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		SimpleDraw.gui.setTooltip("X:" + e.getX() + " Y: " + e.getY());
	}

	public BufferedImage getActiveBuffer() {
		return activeBuffer;
	}

	public BufferedImage getMainBuffer() {
		return mainBuffer;
	}

	public void addToPolygons(Polygon p) {
		polygons.add(p);
	}

	public List<Polygon> getPolygons() {
		return polygons;
	}

}
