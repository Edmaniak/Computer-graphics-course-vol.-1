package app;

import gui.Canvas;
import gui.ColorPicker;
import gui.FillPicker;
import gui.ToolButton;
import patterns.Pattern;
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
                selectedTool.doAfterSwitchOut();
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

        // Button for making dashed lines
        ToolButton dashLine = new ToolButton("Draw a dashed line", "res/dash.png");
        dashLine.addActionListener(e -> changeTool(new DashLineTool(canvas, colorToUse)));

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
        // SEPARATION
        JSeparator separ = new JSeparator(JSeparator.VERTICAL);
        toolBar.addSeparator();
        separ.setMaximumSize(new Dimension(10,toolBar.getPreferredSize().height));
        toolBar.add(separ);



        /*
        ToolButton editablePolyogon = new ToolButton("Draw an editable polygon");
        editablePolyogon.setText("EDITABLE-POLYGON");
        */


        // Button for seedfiller
        ToolButton seedFill = new ToolButton("SEED-FILL desired polygon defined by raster color", "res/fill.png");
        seedFill.setText("SF");
        seedFill.addActionListener(e -> {
            FillPicker fillOptions = new FillPicker();
            switch (fillOptions.getChoice()) {
                case 0:
                    changeTool(new SeedFillerTool(canvas, colorToUse));
                    break;
                case 1:
                    changeTool(new SeedFillerTool(canvas, colorToUse, Color.ORANGE, Color.BLUE, new Pattern(6, 6)));
                    break;
            }
        });

        // Button for scanline filling
        ToolButton scanLine = new ToolButton("SCAN-LINE-FILL desired polygon defined by its geometrical structure","res/fill.png");
        scanLine.setText("SL");
        scanLine.addActionListener(e -> changeTool(new ScanLineTool(canvas, colorToUse)));

        // Button for clipper
        ToolButton clipper = new ToolButton("Drag an clipping area around the polygon you want to clipp", "res/cut.png");
        clipper.addActionListener(e -> changeTool(new ClipperTool(canvas, colorToUse)));
    }

    private void changeTool(Tool tool) {
        if (selectedTool != null)
            selectedTool.doAfterSwitchOut();
        selectedTool = tool;
        selectedTool.setColor(colorToUse);
        selectedTool.doOnSwitchIn();
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
