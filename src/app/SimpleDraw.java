package app;

import gui.Canvas;
import gui.ColorPicker;
import gui.ToolButton;
import tools.*;
import tools.PolygonTool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SimpleDraw extends JFrame {

    public static String title = "simpleDraw";
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

        // Singleton reference for quicker access;
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
        ToolButton newCanvas = new ToolButton("New canvas", "res/new.png", this);
        newCanvas.addActionListener(e -> {
            if (colorToUse != AppColor.DEFAULT_DRAW)
                canvas.clear(colorToUse);
            else
                canvas.clear(AppColor.DEFAULT_BG);
            if (selectedTool != null)
                selectedTool.doAfterSwitch();
        });
        toolBar.add(newCanvas);

        // Button for making brush line
        ToolButton brush = new ToolButton("Draw a point", "res/brush.png", this);
        brush.addActionListener(e -> changeTool(new BrushTool(canvas, colorToUse)));
        toolBar.add(brush);

        // Button for making dots
        ToolButton dots = new ToolButton("Draw a point", "res/point.png", this);
        dots.addActionListener(e -> changeTool(new DotTool(canvas, colorToUse)));
        toolBar.add(dots);

        // Button for making lines
        ToolButton line = new ToolButton("Draw a line", "res/line.png", this);
        line.addActionListener(e -> changeTool(new LineTool(canvas, colorToUse)));
        toolBar.add(line);

        // Button for making n-gons
        ToolButton polygon = new ToolButton("Draw a polygon", "res/polygon.png", this);
        polygon.addActionListener(e -> changeTool(new PolygonTool(canvas, colorToUse)));
        toolBar.add(polygon);

        // Button for making circles
        ToolButton circle = new ToolButton("Draw a circle", "res/circle.png", this);
        circle.addActionListener(e -> changeTool(new CircleTool(canvas, colorToUse)));
        toolBar.add(circle);

        // Button for making arcs
        ToolButton arc = new ToolButton("Draw an arc", "res/arc.png", this);
        arc.addActionListener(e -> changeTool(new ArcTool(canvas, colorToUse)));
        toolBar.add(arc);

        // Color picker
        ToolButton colorPicker = new ToolButton("Pick a color", this);
        colorPicker.setText("COLOR");
        colorPicker.addActionListener(e -> {
            ColorPicker colorPallete = new ColorPicker();
            colorPallete.openDialog();
            colorToUse = colorPallete.getChoosedColor();
            colorPicker.setBackground(colorToUse);
            if (selectedTool != null)
                selectedTool.setColor(colorToUse);
        });
        toolBar.add(colorPicker);

        // Second part of the task
        toolBar.addSeparator();

        // Button for seedfiller
        ToolButton seedFill = new ToolButton("Fill desired area", this);
        seedFill.setText("SEEDFILL");
        seedFill.addActionListener(e -> changeTool(new SeedFillerTool(canvas, colorToUse)));
        toolBar.add(seedFill);

        // Second part of the task
        toolBar.addSeparator();



    }

    private void changeTool(Tool go) {
        selectedTool = go;
        selectedTool.setColor(colorToUse);
        selectedTool.doAfterSwitch();
        canvas.setMouseMotionHandler(go.getMotionHandler());
        canvas.setMouseClickHandler(go.getClickHandler());
        canvas.setCursor(go.cursor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleDraw(new Dimension(800, 600)));
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
