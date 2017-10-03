import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SimpleDraw {

	private String version = "0.01";
	private Canvas canvas;
	private JFrame frame;
	private JPanel tools;
	private JPanel tooltip;

	public SimpleDraw(Dimension d) {
		String title = "simpleDRAW " + version;

		frame = new JFrame(title);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		canvas = new Canvas(new Dimension(d.width, d.height), Color.black);

		tools = new JPanel();
		tools.setPreferredSize(new Dimension(d.width, 30));

		tooltip = new JPanel();
		tooltip.setPreferredSize(new Dimension(d.width, 15));

		frame.add(canvas, BorderLayout.CENTER);
		frame.add(tools, BorderLayout.NORTH);
		frame.add(tooltip, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
		
		Line line = new Line(100,100,200,200);
		canvas.draw(line);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SimpleDraw(new Dimension(800, 600)));
	}

}
