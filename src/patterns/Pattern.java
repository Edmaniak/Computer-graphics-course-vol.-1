package patterns;

import java.awt.*;
import java.util.Random;

public class Pattern {
    private int[][] structure;
    private Random rand;
    public final Color color1 = Color.ORANGE;;
    public final Color color2 = Color.BLUE;;

    public Pattern(int width, int height) {
        rand = new Random();
        genRandom(width, height);
    }

    public int getHeight() {
        return structure.length;
    }

    public int getWidth() {
        return structure[0].length;
    }

    public void genRandom(int height, int width) {
        structure = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int n = rand.nextBoolean() ? 1 : 0;
                structure[i][j] = n;
            }
        }
    }

    public int[][] getStructure() {
        return structure;
    }
}
