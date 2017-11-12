package gui;

import app.AppColor;
import app.SimpleDraw;
import patterns.Pattern;
import tools.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    private final Canvas canvas;
    private final JToolBar toolBar;
    private final JLabel tooltip;
    private final JLabel instruction;
    private final Dimension dimension;
    private final SimpleDraw app;

    public MainFrame(int width, int height) {

        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(SimpleDraw.title);
        dimension = new Dimension(width,height);

        // app reference
        app = SimpleDraw.app;

        // Canvas
        canvas = new Canvas(dimension, AppColor.DEFAULT_BG);

        // Toolbar
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new Dimension(dimension.width, 45));
        generateButtons();

        // Tooltip downwards
        tooltip = new JLabel();
        instruction = new JLabel();
        JPanel tooltipPanel = new JPanel();
        tooltipPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
        tooltipPanel.setLayout(new BorderLayout());
        tooltipPanel.setMinimumSize(new Dimension(width,50));
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

        // List of toolGroups
        ToolGroup task1 = new ToolGroup(toolBar);
        ToolGroup seedGroup = new ToolGroup(toolBar);
        ToolGroup task2 = new ToolGroup(toolBar);

        // Button for new canvas
        ToolButton newCanvas = new ToolButton("New canvas", "res/new.png", task1);
        newCanvas.addActionListener(e -> {
            if (app.colorToUse != AppColor.DEFAULT_DRAW)
                canvas.clear(app.colorToUse);
            else
                canvas.clear(AppColor.DEFAULT_BG);
            if (app.getSelectedTool() != null)
                app.getSelectedTool().doAfterSwitchOut();
        });

        // Button for making brush line
        ToolButton brush = new ToolButton("Draw a point", "res/brush.png", task1);
        brush.addActionListener(e -> app.changeTool(new BrushTool(canvas, app.colorToUse)));

        // Button for making dots
        ToolButton dots = new ToolButton("Draw a point", "res/point.png", task1);
        dots.addActionListener(e -> app.changeTool(new DotTool(canvas, app.colorToUse)));

        // Button for making lines
        ToolButton line = new ToolButton("Draw a line", "res/line.png", task1);
        line.addActionListener(e -> app.changeTool(new LineTool(canvas, app.colorToUse)));

        // Button for making dashed lines
        ToolButton dashLine = new ToolButton("Draw a dashed line", "res/dash.png", task1);
        dashLine.addActionListener(e -> app.changeTool(new DashLineTool(canvas, app.colorToUse)));

        // Button for making n-gons
        ToolButton polygon = new ToolButton("Draw a polygon", "res/polygon.png", task1);
        polygon.addActionListener(e -> app.changeTool(new PolygonTool(canvas, app.colorToUse)));

        // Button for making circles
        ToolButton circle = new ToolButton("Draw a circle", "res/circle.png", task1);
        circle.addActionListener(e -> app.changeTool(new CircleTool(canvas, app.colorToUse)));

        // Button for making arcs
        ToolButton arc = new ToolButton("Draw an arc", "res/arc.png", task1);
        arc.addActionListener(e -> app.changeTool(new ArcTool(canvas, app.colorToUse)));

        // Color picker
        ToolButton colorPicker = new ToolButton("Pick a color", task1);
        colorPicker.setText("COLOR");
        colorPicker.addActionListener(e -> {
            ColorPicker colorPallete = new ColorPicker();
            colorPallete.openDialog();
            app.colorToUse = colorPallete.getChoosedColor();
            colorPicker.setBackground(app.colorToUse);
            if (app.getSelectedTool() != null)
                app.getSelectedTool().setColor(app.colorToUse);
        });

        // Second part of the task
        String[] options = {"Solid","Random pattern"};
        JComboBox fillType = new JComboBox(options);


        // Button for seedfiller
        ToolButton seedFill = new ToolButton("SEED-FILL desired polygon defined by raster color", "res/fill.png", seedGroup);
        seedFill.setText("SF");
        seedFill.addActionListener(e -> {
            FillPicker fillOptions = new FillPicker();
            switch (fillOptions.getChoice()) {
                case 0:
                    app.changeTool(new SeedFillerTool(canvas, app.colorToUse));
                    break;
                case 1:
                    app.changeTool(new SeedFillerTool(canvas, app.colorToUse, Color.ORANGE, Color.BLUE, new Pattern(6, 6)));
                    break;
            }
        });

        seedGroup.add(fillType);

        // Button for scanline filling
        ToolButton scanLine = new ToolButton("SCAN-LINE-FILL desired polygon defined by its geometrical structure", "res/fill.png", task2);
        scanLine.setText("SL");
        scanLine.addActionListener(e -> app.changeTool(new ScanLineTool(canvas, app.colorToUse)));

        // Button for clipper
        ToolButton clipper = new ToolButton("Drag an clipping area around the polygon you want to clipp", "res/cut.png", task2);
        clipper.addActionListener(e -> app.changeTool(new ClipperTool(canvas, app.colorToUse)));

        // Button for editable Polygon
        ToolButton editablePolygon = new ToolButton("Draw an editable polygon","res/polygon.png",task2);
        editablePolygon.setText("EDITABLE POLYGON");
        editablePolygon.addActionListener(e -> app.changeTool(new EditPolygonTool(canvas, app.colorToUse)));
    }

    public Canvas getCanvas() {
        return canvas;
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
}
