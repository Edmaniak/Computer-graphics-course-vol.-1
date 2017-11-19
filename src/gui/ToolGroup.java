package gui;

import app.SimpleDraw;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ToolGroup extends JPanel {


    public ToolGroup(JToolBar toolBar){
        super(new FlowLayout());
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        toolBar.add(this);
    }

}
