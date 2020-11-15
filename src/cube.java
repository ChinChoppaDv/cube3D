import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class cube extends Canvas implements Runnable {

/*    public static JFrame frame = new JFrame("cube");*/
    public static Canvas canvas = new Canvas();
    public static int a = 500;
    public static int b = 500;
    private boolean running = false;
    public static BufferStrategy bufferStrategy;
    public static Graphics graphics;
    public static int rectW = 70;
    public static double t = 0.0;

    private JFrame frame;
    private Thread thread;

    public cube () {
        Dimension size = new Dimension(a, b);
        frame = new JFrame("cube");

    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "КУБ");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run () {
        while (running) {
            t += 0.1;
            bufferStrategy = canvas.getBufferStrategy();
            graphics = bufferStrategy.getDrawGraphics();
            graphics.clearRect(0, 0, b, a);
            graphics.setColor(Color.black);
            graphics.drawRect((int) (Math.sin(t) * 100), 140, rectW, rectW);
            bufferStrategy.show();
            graphics.dispose();
        }
    }

    public static void main(String[] args) {
        cube cube3d = new cube();
        cube3d.frame.add(cube3d);
        cube3d.frame.add(canvas);
        canvas.setSize(a, b);
        cube3d.frame.pack();
        cube3d.frame.setVisible(true);
        cube3d.frame.setResizable(false);
        cube3d.frame.setLocationRelativeTo(null);
        cube3d.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas.createBufferStrategy(3);
        cube3d.start();

    }

}
