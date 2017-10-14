import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

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
		generateButtons();

		toolBar.setPreferredSize(new Dimension(d.width, 30));

		// Tooltip downwards
		tooltipPanel = new JPanel();
		tooltipPanel.setLayout(new BorderLayout());
		tooltipPanel.setPreferredSize(new Dimension(d.width, 15));
		tooltip = new JLabel();
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
					canvas.setBgColor(colorToUse);
				else
					canvas.clear();
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

		// Button for making circles
		ToolButton circle = new ToolButton("Draw a circle or an arc", "res/circle.png");
		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Circle(canvas, colorToUse));
			}
		});
		toolBar.add(circle);

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
				selectedTool.setColor(colorToUse);
			}
		});
		toolBar.add(colorPicker);

	}

	private void changeTool(GraphicalObject go) {
		selectedTool = go;
		canvas.setMouseClickHandler(go.clickHandler);
		canvas.setMouseMotionHandler(go.motionHandler);
		canvas.setCursor(go.cursor);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SimpleDraw(new Dimension(800, 600)));
	}

}
