import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


class cube extends Canvas {

    public static JFrame frame = new JFrame("cube");
    public static Canvas canvas = new Canvas();
    public static int a = 500;
    public static int b = 500;
    public static boolean running = true;
    public static BufferStrategy bufferStrategy;
    public static Graphics graphics;

    private static void draw () {
        while (running) {
            bufferStrategy = canvas.getBufferStrategy();
            graphics = bufferStrategy.getDrawGraphics();
            graphics.clearRect(0, 0, b, a);

            graphics.setColor(Color.black);
            graphics.drawString("чё блять как это работает?", 5, 15);

            bufferStrategy.show();
            graphics.dispose();
        }
    }

    public static void main(String[] args) {
        frame.add(canvas);
        canvas.setSize(a, b);
//        canvas.setBackground(color);
//        Graphics graphics = frame.getGraphics();
//        graphics.drawLine(3, 3, 8, 8);
//        canvas.print(graphics);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.createBufferStrategy(3);
        draw();
    }

}
