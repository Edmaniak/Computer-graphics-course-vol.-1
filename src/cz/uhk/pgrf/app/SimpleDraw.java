package cz.uhk.pgrf.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import cz.uhk.pgrf.gui.Canvas;
import cz.uhk.pgrf.gui.ColorPicker;
import cz.uhk.pgrf.gui.ToolButton;
import cz.uhk.pgrf.tools.Arc;
import cz.uhk.pgrf.tools.Brush;
import cz.uhk.pgrf.tools.Circle;
import cz.uhk.pgrf.tools.Dot;
import cz.uhk.pgrf.tools.GraphicalObject;
import cz.uhk.pgrf.tools.Line;
import cz.uhk.pgrf.tools.Polygon;

public class SimpleDraw {

	public String title = "simpleDraw";
	private Canvas canvas;
	private JFrame frame;
	public static JToolBar toolBar;
	private JPanel tooltipPanel;
	public static JLabel tooltip;

	private GraphicalObject selectedTool;
	private Color colorToUse = AppColor.DEFAULT_DRAW;

	public SimpleDraw(Dimension d) {

		frame = new JFrame(title);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Canvas
		canvas = new Canvas(new Dimension(d.width, d.height), Color.black);

		// Toolbar
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setPreferredSize(new Dimension(d.width, 30));
		generateButtons();

		// Tooltip downwards
		tooltip = new JLabel();
		tooltipPanel = new JPanel();
		tooltipPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
		tooltipPanel.setLayout(new BorderLayout());
		tooltipPanel.setPreferredSize(new Dimension(d.width, 20));
		tooltipPanel.add(tooltip, BorderLayout.WEST);
		
		

		// Adding everything to the frame
		frame.add(canvas, BorderLayout.CENTER);
		frame.add(toolBar, BorderLayout.NORTH);
		frame.add(tooltipPanel, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

	private void generateButtons() {
		// Button for new canvas
		ToolButton newCanvas = new ToolButton("New canvas", "res/new.png");
		newCanvas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (colorToUse != AppColor.DEFAULT_DRAW)
					canvas.clear(colorToUse);
				else
					canvas.clear(AppColor.DEFAULT_BG);
				if (selectedTool != null)
					selectedTool.clear();
			}
		});
		toolBar.add(newCanvas);

		// Button for making brush line
		ToolButton brush = new ToolButton("Draw a point", "res/brush.png");
		brush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Brush(canvas, colorToUse));
			}
		});
		toolBar.add(brush);

		// Button for making dots
		ToolButton dots = new ToolButton("Draw a point", "res/point.png");
		dots.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Dot(canvas, colorToUse));
			}
		});
		toolBar.add(dots);

		// Button for making lines
		ToolButton line = new ToolButton("Draw a line", "res/line.png");
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Line(canvas, colorToUse));
			}
		});
		toolBar.add(line);

		// Button for making n-gons
		ToolButton polygon = new ToolButton("Draw a polygon", "res/polygon.png");
		polygon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Polygon(canvas, colorToUse));
			}
		});
		toolBar.add(polygon);

		// Button for making circles
		ToolButton circle = new ToolButton("Draw a circle", "res/circle.png");
		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Circle(canvas, colorToUse));
			}
		});
		toolBar.add(circle);

		// Button for making arcs
		ToolButton arc = new ToolButton("Draw an arc", "res/arc.png");
		arc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Arc(canvas, colorToUse));
			}
		});
		toolBar.add(arc);

		// Color picker
		ToolButton colorPicker = new ToolButton("Pick a color");
		colorPicker.setText("COLOR");
		colorPicker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ColorPicker colorPallete = new ColorPicker();
				colorPallete.openDialog();
				colorToUse = colorPallete.getChoosedColor();
				colorPicker.setBackground(colorToUse);
				if (selectedTool != null)
					selectedTool.setColor(colorToUse);
			}
		});
		toolBar.add(colorPicker);

	}

	private void changeTool(GraphicalObject go) {
		selectedTool = go;
		selectedTool.setColor(colorToUse);
		canvas.setMouseMotionHandler(go.getMotionHandler());
		canvas.setMouseClickHandler(go.getClickHandler());
		canvas.setCursor(go.cursor);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SimpleDraw(new Dimension(800, 600)));
	}

}
