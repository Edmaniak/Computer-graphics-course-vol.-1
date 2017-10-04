import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SimpleDraw {

	private String version = "0.01";
	private Canvas canvas;
	private JFrame frame;
	private JToolBar toolBar;
	private JPanel tooltipPanel;
	private JLabel tooltip;
	private List<JButton> buttons;
	private GraphicalObject selected;
	private MouseListener currentMouseListener;

	public SimpleDraw(Dimension d) {
		String title = "simpleDRAW " + version;

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
		JButton newCanvas = new JButton(new ImageIcon("res/new.png"));
		newCanvas.setToolTipText("New canvas");
		newCanvas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.clear();

			}
		});
		newCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tooltip.setText(newCanvas.getToolTipText());
			}
		});
		toolBar.add(newCanvas);

		// Button for making dots
		JButton brush = new JButton(new ImageIcon("res/point.png"));
		brush.setToolTipText("Draw a point");
		brush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Brush(canvas));
			}
		});
		brush.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tooltip.setText(brush.getToolTipText());
			}
		});
		toolBar.add(brush);

		// Button for making lines
		JButton line = new JButton(new ImageIcon("res/line.png"));
		line.setToolTipText("Draw a line");
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeTool(new Line(canvas));
			}
		});
		line.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				tooltip.setText(line.getToolTipText());
			}
		});
		toolBar.add(line);

	}

	private void changeTool(GraphicalObject go) {
		selected = go;
		freeListeners();
		canvas.addMouseListener(selected.getHandler());
	}

	private void freeListeners() {
		for (MouseListener listener : canvas.getMouseListeners()) {
			canvas.removeMouseListener(listener);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SimpleDraw(new Dimension(800, 600)));
	}

}
