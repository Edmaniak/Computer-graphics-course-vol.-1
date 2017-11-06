package app;

import gui.Canvas;
import gui.ColorPicker;
import gui.ToolButton;
import tools.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SimpleDraw extends JFrame {

    public static String title = "SimpleDraw";
    public static SimpleDraw gui;
    private final Canvas canvas;
    private final JToolBar toolBar;
    private final JLabel tooltip;
    private final JLabel instruction;
    private Tool selectedTool;
    private Color colorToUse = AppColor.DEFAULT_DRAW;

    private SimpleDraw(Dimension d) {

        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);

        // Singleton reference for quicker access
        if (gui == null)
            gui = this;

        // Canvas
        canvas = new Canvas(new Dimension(d.width, d.height), Color.black);

        // Toolbar
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(d.width, 30));
        generateButtons();

        // Tooltip downwards
        tooltip = new JLabel();
        instruction = new JLabel();
        JPanel tooltipPanel = new JPanel();
        tooltipPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
        tooltipPanel.setLayout(new BorderLayout());
        tooltipPanel.setPreferredSize(new Dimension(d.width, 20));
        tooltipPanel.add(tooltip, BorderLayout.WEST);
        tooltipPanel.add(instruction, BorderLayout.EAST);

        // Adding everything to the frame
        add(canvas, BorderLayout.CENTER);
        add(toolBar, BorderLayout.NORTH);
        add(tooltipPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void generateButtons() {

        // Button for new canvas
        ToolButton newCanvas = new ToolButton("New canvas", "res/new.png");
        newCanvas.addActionListener(e -> {
            if (colorToUse != AppColor.DEFAULT_DRAW)
                canvas.clear(colorToUse);
            else
                canvas.clear(AppColor.DEFAULT_BG);
            if (selectedTool != null)
                selectedTool.doAfterSwitch();
        });

        // Button for making brush line
        ToolButton brush = new ToolButton("Draw a point", "res/brush.png");
        brush.addActionListener(e -> changeTool(new BrushTool(canvas, colorToUse)));

        // Button for making dots
        ToolButton dots = new ToolButton("Draw a point", "res/point.png");
        dots.addActionListener(e -> changeTool(new DotTool(canvas, colorToUse)));

        // Button for making lines
        ToolButton line = new ToolButton("Draw a line", "res/line.png");
        line.addActionListener(e -> changeTool(new LineTool(canvas, colorToUse)));

        // Button for making n-gons
        ToolButton polygon = new ToolButton("Draw a polygon", "res/polygon.png");
        polygon.addActionListener(e -> changeTool(new PolygonTool(canvas, colorToUse)));

        // Button for making circles
        ToolButton circle = new ToolButton("Draw a circle", "res/circle.png");
        circle.addActionListener(e -> changeTool(new CircleTool(canvas, colorToUse)));

        // Button for making arcs
        ToolButton arc = new ToolButton("Draw an arc", "res/arc.png");
        arc.addActionListener(e -> changeTool(new ArcTool(canvas, colorToUse)));

        // Color picker
        ToolButton colorPicker = new ToolButton("Pick a color");
        colorPicker.setText("COLOR");
        colorPicker.addActionListener(e -> {
            ColorPicker colorPallete = new ColorPicker();
            colorPallete.openDialog();
            colorToUse = colorPallete.getChoosedColor();
            colorPicker.setBackground(colorToUse);
            if (selectedTool != null)
                selectedTool.setColor(colorToUse);
        });

        // Second part of the task
        toolBar.addSeparator();

        // Button for seedfiller
        ToolButton seedFill = new ToolButton("Fill desired area defined by raster color");
        seedFill.setText("SEED-FILL");
        seedFill.addActionListener(e -> changeTool(new SeedFillerTool(canvas, colorToUse)));

        // Button for scanline filling
        ToolButton scanLine = new ToolButton("Fill desired POLYGON defined by its geometrical structure");
        scanLine.setText("SCAN-LINE");
        scanLine.addActionListener(e -> changeTool(new ScanLineTool(canvas, colorToUse)));

    }

    private void changeTool(Tool tool) {
        if (selectedTool != null)
            selectedTool.doAfterSwitch();
        selectedTool = tool;
        selectedTool.setColor(colorToUse);
        canvas.setMouseMotionHandler(tool.getMotionHandler());
        canvas.setMouseClickHandler(tool.getClickHandler());
        canvas.setCursor(tool.cursor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleDraw(new Dimension(800, 600)));
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setTooltip(String tooltip) {
        this.tooltip.setText(tooltip);
    }

    public void setInstruction(String instruction) {
        this.instruction.setText(instruction);
    }

    public Tool getSelectedTool() {
        return selectedTool;
    }


}
