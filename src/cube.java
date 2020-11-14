import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

class cube {

public static int a = 500;
public static int b = 500;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Canvas canvas = new Canvas();
        frame.add(canvas);
        canvas.setSize(a, b);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
    