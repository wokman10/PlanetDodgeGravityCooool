import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Main extends JPanel {

    //instance fields for the general environment
    public static final int FRAMEWIDTH = 800, FRAMEHEIGHT = 800;
    private Timer timer;
    private boolean[] keys;

    public ArrayList<Sprite> sprites;

    public Main() {
        keys = new boolean[512]; //should be enough to hold any key code.
        //TODO: initialize the instance fields.

        setup();

        timer = new Timer(1000/60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                for (Sprite spr : sprites) {
                    spr.update(sprites);
                }

                if (keys[KeyEvent.VK_R]) {
                }

                if (keys[KeyEvent.VK_H]) {
                }

                if (keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP]) {
                    keys[KeyEvent.VK_W] = false;
                    keys[KeyEvent.VK_UP] = false;
                }
                if (keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT]) {
                    keys[KeyEvent.VK_A] = false;
                    keys[KeyEvent.VK_LEFT] = false;
                }
                if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN]) {
                    keys[KeyEvent.VK_S] = false;
                    keys[KeyEvent.VK_DOWN] = false;
                }
                if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT]) {
                    keys[KeyEvent.VK_D] = false;
                    keys[KeyEvent.VK_RIGHT] = false;
                }

                repaint();
            }
        });

        timer.start();

        setKeyListener();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawString("o", FRAMEWIDTH / 2, FRAMEHEIGHT / 2);
        for (Sprite spr : sprites) {
            spr.draw(g2);
//            System.out.println(spr.toString());
        }
    }

    public void setup() {
        sprites = new ArrayList();
        sprites.add(new Sprite(400, 400, new Vector(0, 0), 1000));
        sprites.add(new Sprite(400, 600, new Vector(-20, 0), 10));
        sprites.add(new Sprite(600, 600, new Vector(-40, 0), 10));
    }

    public void setKeyListener() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) { /*intentionally left blank*/ }

            //when a key is pressed, its boolean is switch to true.
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = true;
            }

            //when a key is released, its boolean is switched to false.
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                keys[keyEvent.getKeyCode()] = false;
            }
        });
    }
    //sets ups the panel and frame.  Probably not much to modify here.
    public static void main(String[] args) {
        JFrame window = new JFrame("pOLaR frOgGEr");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, FRAMEWIDTH, FRAMEHEIGHT + 22); //(x, y, w, h) 22 due to title bar.

        Main panel = new Main();
        panel.setSize(FRAMEWIDTH, FRAMEHEIGHT);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}