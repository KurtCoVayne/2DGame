package threads_test;

import javax.swing.JFrame;

/**
 * Launcher
 */
public class Launcher {

    private static final int width = 250,height=250,scale=2;

    public static void main(String[] args) {
        Canvas_Thread game  = new Canvas_Thread("test", width, height, scale);
        JFrame f = new JFrame("test");
        
        f.setSize(width * scale, height * scale);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.add(game);
        f.setVisible(true);
        
        game.start();
    }
}