import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class cube extends Canvas implements Runnable { //extends Canvas - т.е нам сразу становятся доступны методы канваса без обращения к самому канвасу
                                                        //implements Runnable - означает то, что  код теперь будет выполнятся не один раз, а пока метод run() активен

    public static Canvas canvas = new Canvas(); //создаем новый канвас ака холст на котором будем все рисовать и рендерить (далее - канвас)
    private static int width = 500; //размерчики
    private static int height = 500;
    private boolean running = false; //ну и булик отвечающий за работу проги

    private Screen screen;
    private JFrame frame;
    private Thread thread;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //изображение из текущего буффера, остальное это тип создаваемого изображения, здесь ргб
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();    //getRaster() - возвращает считай массив пикселей который мы уже можем менять
                                                                                            //getDataBuffer() - берем из растера буффер данных
                                                                                            //getData() - ну вот тут уже просто записываем в массив пикселей
    public cube () {
        Dimension size = new Dimension(width, height); //конструктор куба, здесь класс Dimension - считай просто размеры
        setPreferredSize(size); //устанавливаем размер
        screen = new Screen(width, height);
        frame = new JFrame("cube"); //ну это просто наше окно
    }

    public synchronized void start() { //метод старта, делает running = true
        running = true;
        thread = new Thread(this, "cubeThread"); //новый поток, считай когда мы комплириуем в процесс добавляется новый подпроцесс - как раз наш поток (можно в дистпетчере задач чекнуть)
        thread.start(); //запускаем поток
    }

    public synchronized void stop() { //метод остановки
        running = false;
        try {
            thread.join(); //останавливаем поток
        } catch (InterruptedException e) { //смотрмим че там за ошибки выбросить может
            e.printStackTrace();
        }
    }

    public void render () { //метод рендера изображения - он и будет кучу раз вызываться когда работает run()
        BufferStrategy bufferStrategy = getBufferStrategy(); //берем буферную стратению
        if (bufferStrategy == null) { //нет буферной стратегии? не беда - делаем свою и возвращаемся обратно
            createBufferStrategy(3);
            return;
        }
            screen.render();
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
            Graphics graphics = bufferStrategy.getDrawGraphics();
            graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            graphics.dispose();
            bufferStrategy.show();
    }


    public void run () { //тот самый run() - его не надо вызывать, потому что как раз таки наш основной класс implements Runnable, а следовательно чему то же в таком случае надо делать run
        while (running) { //рендерим делаем дела
            render();

        }
    }

    public static void main(String[] args) {
        cube cube3d = new cube(); //создаем новый куб3д из нашего конструктора
        cube3d.frame.setResizable(false);
        cube3d.frame.add(cube3d); //добавляем в наше окно куб3д
        cube3d.frame.pack(); //просто делаем окно таким, чтобы все вместилось
        canvas.setSize(cube3d.getSize()); //устанавливаем канвасу размер куба указанный в конструкторе
        cube3d.frame.setVisible(true); //делаем видимым
        cube3d.frame.setLocationRelativeTo(null); //центрируем
        cube3d.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //окно при нажатии кноки закрыть, т.к. если этого не сделать эта штука будет по-прежнему работать
        cube3d.start(); //ну и запускаем рендер изображения и всего-всего
    }

}
