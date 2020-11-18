import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class cube extends Canvas implements Runnable {

    public static Canvas canvas = new Canvas();
    public static int width = 500;
    public static int height = 500;
    private boolean running = false;


    private JFrame frame;
    private Thread thread;

    public cube () {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        frame = new JFrame("cube");
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "cubeThread");
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

    public void render () {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
            Graphics graphics = bufferStrategy.getDrawGraphics();
            graphics.setColor(Color.black);
            graphics.fillRect(0,0, getWidth(),getHeight());
            graphics.dispose();
            bufferStrategy.show();
    }


    public void run () {
        while (running) {
            render();

        }
    }

    public static void main(String[] args) {
        cube cube3d = new cube();
        cube3d.frame.setResizable(false);
        cube3d.frame.add(cube3d);
        cube3d.frame.pack();
        canvas.setSize(cube3d.getSize());
        cube3d.frame.setVisible(true);
        cube3d.frame.setLocationRelativeTo(null);
        cube3d.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cube3d.start();
    }

}
