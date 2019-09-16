package threads_test;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Canvas_Thread
 */
public class Canvas_Thread extends Canvas implements Runnable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public int width, height, scale;
    public String title;
    public static boolean running = false;
    public Thread gameThread;

    private BufferStrategy bs;
    private Graphics g;

    int x = 0;

    public Canvas_Thread(String title, int width, int height, int scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;
    }

    private void init() {
        setSize(width * scale, height * scale);
        Assets.init();
    }

    @Override
    public void run() {
        init();
        int fps = 120;
        long oneSecond = 1000000000;
        double timePerTick = oneSecond / fps; // Nano seconds divided fps to get the time a tick should take
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // Time in nano seconds
        long timer = 0;
        int ticks = 0;
        // GAME LOOP
        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; // Time passed now was called divided by the maximum time allowed
                                                     // to do operations
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) { // If delta is greater than all the processing time passed then do the game
                              // looping and rest the 1 it gets
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= oneSecond){
                System.out.println("Ticks called: "+ ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    private void tick() {
        x += 1;
    }

    private void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // CLEANING SCREEN
        g.clearRect(0, 0, width * scale, height * scale);
        // Created drawing tools,then draw

        g.drawImage(Assets.player1, x, 10, null);

        // End Drawing
        bs.show();
        g.dispose();
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}