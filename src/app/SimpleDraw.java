package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import gui.Canvas;
import gui.ColorPicker;
import gui.ToolButton;
import tools.*;

public class SimpleDraw extends JFrame {

    public static String title = "simpleDraw";
    private Canvas canvas;
    private JToolBar toolBar;
    private JLabel tooltip;
    private JLabel instruction;



    private Tool selectedTool;
    private Color colorToUse = AppColor.DEFAULT_DRAW;

    private SimpleDraw(Dimension d) {

        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);

        // Canvas
        canvas = new Canvas(new Dimension(d.width, d.height), Color.black, this);

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
                selectedTool.clear();
        });
        toolBar.add(newCanvas);

        // Button for making brush line
        ToolButton brush = new ToolButton("Draw a point", "res/brush.png", this);
        brush.addActionListener(e -> changeTool(new Brush(canvas, colorToUse)));
        toolBar.add(brush);

        // Button for making dots
        ToolButton dots = new ToolButton("Draw a point", "res/point.png", this);
        dots.addActionListener(e -> changeTool(new Dot(canvas, colorToUse)));
        toolBar.add(dots);

        // Button for making lines
        ToolButton line = new ToolButton("Draw a line", "res/line.png", this);
        line.addActionListener(e -> changeTool(new Line(canvas, colorToUse)));
        toolBar.add(line);

        // Button for making n-gons
        ToolButton polygon = new ToolButton("Draw a polygon", "res/polygon.png", this);
        polygon.addActionListener(e -> changeTool(new Polygon(canvas, colorToUse)));
        toolBar.add(polygon);

        // Button for making circles
        ToolButton circle = new ToolButton("Draw a circle", "res/circle.png", this);
        circle.addActionListener(e -> changeTool(new Circle(canvas, colorToUse)));
        toolBar.add(circle);

        // Button for making arcs
        ToolButton arc = new ToolButton("Draw an arc", "res/arc.png", this);
        arc.addActionListener(e -> changeTool(new Arc(canvas, colorToUse)));
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
        ToolButton seedFill = new ToolButton("Fill desired area", "",this);
        seedFill.addActionListener(e -> changeTool(new SeedFiller(canvas, colorToUse)));
        toolBar.add(seedFill);

    }

    private void changeTool(Tool go) {
        selectedTool = go;
        selectedTool.setColor(colorToUse);
        selectedTool.clear();
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
