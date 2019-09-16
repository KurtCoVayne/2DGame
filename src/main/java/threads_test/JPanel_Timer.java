package threads_test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * Hello world!
 *
 */
public class JPanel_Timer extends JPanel implements KeyListener {
    /**
     *
     */
    private JLabel test = new JLabel("*");
    private static final long serialVersionUID = 1L;
    double x = 500,y = 500, velx = 3, vely = 3;
    private final HashSet<Integer> pressed = new HashSet<Integer>();
    public static void main(String[] args) {
        JPanel a = new JPanel_Timer();
        JFrame f = new JFrame();
        f.add(a);
        f.setVisible(true);
        f.setSize(1000, 1000);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("test");
    }

    public JPanel_Timer() {
        addKeyListener(this);
        setFocusable(true);
        add(test);

        new Timer(1000/120, new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
                if(pressed.contains(KeyEvent.VK_D)) {
                    x += velx;
                } else if(pressed.contains(KeyEvent.VK_A)) {
                    x -= velx;
                } else if(pressed.contains(KeyEvent.VK_S)) {
                    y += vely;
                } else if(pressed.contains(KeyEvent.VK_W)) {
                    y -= vely;
                }
                repaint();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(x, y, 40, 40);
        g2.fill(circle);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }
}
