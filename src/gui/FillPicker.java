package gui;

import app.SimpleDraw;

import javax.swing.*;
import java.awt.*;

public class FillPicker extends JDialog {

    private int choice;

    public FillPicker() {
        super(SimpleDraw.gui, "Choose seed-fill type", true);
        setLocationRelativeTo(SimpleDraw.gui.getToolBar());

        JPanel buttons = new JPanel(new FlowLayout());

        setLayout(new BorderLayout());
        add(buttons,BorderLayout.SOUTH);
        add(new JLabel("Please choose the way you want to fill the area"), BorderLayout.CENTER);

        JButton solidFill = new JButton("Solid");
        solidFill.addActionListener(e -> {
            choice = 0;
            dispose();
        });
        buttons.add(solidFill);

        JButton randFill = new JButton("Random pattern");
        randFill.addActionListener(e -> {
            choice = 1;
            dispose();
        });
        buttons.add(randFill);

        pack();
        setVisible(true);


    }

    public int getChoice() {
        return choice;
    }
}
