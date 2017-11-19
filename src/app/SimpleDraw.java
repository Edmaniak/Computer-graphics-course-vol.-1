package app;

import file.FileHandler;
import gui.Canvas;
import gui.MainFrame;
import tools.Tool;

import javax.swing.*;
import java.awt.*;

public class SimpleDraw {

    public static final String title = "SimpleDraw";
    public static MainFrame gui;
    public static SimpleDraw app;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;

    private Tool selectedTool;
    public Color colorToUse = AppColor.DEFAULT_DRAW;
    private Canvas canvas;

    private SimpleDraw() {

        // Whole app singleton for more convinient access
        // They both are unique anyway
        if (app == null)
            app = this;
        if (gui == null)
            gui = new MainFrame(WIDTH, HEIGHT);

        gui.setVisible(true);
        canvas = gui.getCanvas();

    }

    /**
     * Handles all the logic needed for switching tools and calling switch events
     * @param tool to switch
     */
    public void changeTool(Tool tool) {
        if (selectedTool != null)
            selectedTool.doAfterSwitchOut();
        selectedTool = tool;
        selectedTool.setColor(colorToUse);
        selectedTool.doOnSwitchIn();
        canvas.setMouseMotionHandler(tool.getMotionHandler());
        canvas.setMouseClickHandler(tool.getClickHandler());
        canvas.setCursor(tool.cursor);
    }

    public Tool getSelectedTool() {
        return selectedTool;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new SimpleDraw());
    }


}
