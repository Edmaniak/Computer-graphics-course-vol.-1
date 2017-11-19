package file;

import app.SimpleDraw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileHandler {

    private BufferedImage img;
    public static final String OUTPUT_PATH = "file/saved-image.jpg";

    public FileHandler(BufferedImage img) {
        this.img = img;
    }

    public void load() {
        try {
            img = ImageIO.read(new File(OUTPUT_PATH));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    SimpleDraw.gui,
                    "Loading failed " + e.getMessage(),
                    "Loading failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void save() {
        try {
            File file = new File(OUTPUT_PATH);
            if (ImageIO.write(img, "jpg", file))
                JOptionPane.showMessageDialog(
                        SimpleDraw.gui,
                        "Image saved",
                        "File",
                        JOptionPane.INFORMATION_MESSAGE
                );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    SimpleDraw.gui,
                    "Saving failed " + e.getMessage(),
                    "File",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
