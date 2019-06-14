import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel implements ActionListener, KeyListener, MouseListener {

    public static JFrame frame = new JFrame("Life of Walker");
    private JPanel menu = new JPanel();

    //instance fields for the general environment
    public static final int FRAMEWIDTH = 1600, FRAMEHEIGHT = 800;
    private boolean isPaused = false;
    private Timer timer;
    private boolean[] keys;

    public ArrayList<Sprite> sprites;

    public Sprite usable;

    public Main(int w, int h) {
        setSize(w, h);
        keys = new boolean[512]; //should be enough to hold any key code.
        //TODO: initialize the instance fields.

        setup();

        timer = new Timer(1000 / 60, this);
        timer.start();
        timer.setActionCommand("timer");

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));

        SpinnerNumberModel xposmodel = new SpinnerNumberModel(
                new Integer(400), // value
                new Integer(0), // min
                new Integer(FRAMEWIDTH), // max
                new Integer(5) // step
        );
        JSpinner xpos = new JSpinner(xposmodel);
//        xpos.setSize(menu.getWidth(), 20);
        Dimension d = xpos.getPreferredSize();
        d.width = 30;
        d.height = 20;
        xpos.setPreferredSize(d);

        SpinnerNumberModel yposmodel = new SpinnerNumberModel(
                new Integer(400), // value
                new Integer(0), // min
                new Integer(FRAMEHEIGHT), // max
                new Integer(5) // step
        );
        JSpinner ypos = new JSpinner(yposmodel);
        Dimension f = ypos.getPreferredSize();
        f.width = 30;
        f.height = 20;
        ypos.setPreferredSize(d);

        SpinnerNumberModel xdeltamodel = new SpinnerNumberModel(
                new Integer(0), // value
                new Integer(-400), // min
                new Integer(400), // max
                new Integer(1) // step
        );
        JSpinner xdelta = new JSpinner(xdeltamodel);
        Dimension j = xpos.getPreferredSize();
        j.width = 30;
        j.height = 20;
        xdelta.setPreferredSize(j);

        SpinnerNumberModel ydeltamodel = new SpinnerNumberModel(
                new Integer(0), // value
                new Integer(-400), // min
                new Integer(400), // max
                new Integer(1) // step
        );
        JSpinner ydelta = new JSpinner(ydeltamodel);
        Dimension k = ypos.getPreferredSize();
        k.width = 30;
        k.height = 20;
        xdelta.setPreferredSize(k);

        SpinnerNumberModel massmodel = new SpinnerNumberModel(
                new Integer(400), // value
                new Integer(1), // min
                new Integer(10000), // max
                new Integer(10) // step
        );
        JSpinner mass = new JSpinner(massmodel);
        Dimension g = ypos.getPreferredSize();
        g.width = 30;
        g.height = 20;
        mass.setPreferredSize(d);


        JButton button1 = new JButton("Spawn");
        button1.setActionCommand("spawn");
        button1.addActionListener(this);

        JButton button2 = new JButton("Pause");
        button2.setActionCommand("pause");
        button2.addActionListener(this);

        JButton button3 = new JButton("Clear Board");
        button3.setActionCommand("clear");
        button3.addActionListener(this);


        menu.add(xpos);
        menu.add(ypos);
        menu.add(xdelta);
        menu.add(ydelta);
        menu.add(mass);
        menu.add(button1);
        menu.add(button2);
        menu.add(button3);
        frame.add(menu, BorderLayout.WEST);

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (!isPaused) {
            for (int i = 0; i < sprites.size(); i++) {
                sprites.get(i).update(sprites);
                if ((sprites.get(i).getXpos() > FRAMEWIDTH + 400 || sprites.get(i).getXpos() < -400) ||
                        (sprites.get(i).getYpos() > FRAMEHEIGHT + 400 || sprites.get(i).getYpos() < -400)) {
                    sprites.remove(i);
                }
            }
        }

//        g2.drawString("o", FRAMEWIDTH / 2, FRAMEHEIGHT / 2);
        for (Sprite spr : sprites) {
            spr.draw(g2);
//            System.out.println(spr.toString());
        }
        menu.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "pause":
                isPaused = !isPaused;
                break;
            case "spawn":
                sprites.add(new Planet((double)(int)(((JSpinner)menu.getComponent(0)).getValue()),
                        (double)(int)(((JSpinner)menu.getComponent(1)).getValue()),
                        new Vector((double)(int)(((JSpinner)menu.getComponent(2)).getValue()) * 100,
                        (double)(int)(((JSpinner)menu.getComponent(3)).getValue()) * 100),
                        (double)(int)(((JSpinner)menu.getComponent(4)).getValue())));
                break;
//            case "fill":
//                theWorld.fillHalf();
//                break;
            case "clear":
                sprites = new ArrayList<Sprite>();
                break;
//            case "changemode":
//                theWorld.rotateMode();
//                break;
//            case "changedrawmode":
//                theWorld.rotateDrawMode();
//                break;
//            case "plusspeed":
//                changeTimer(1);
//                break;
//            case "minusspeed":
//                changeTimer(-1);
//                break;
//            case "rotate":
//                theWorld.increaseDirection();
//                break;
        }

        grabFocus();
        menu.repaint();
        repaint();
    }

    public void setup() {
        sprites = new ArrayList<Sprite>();
        usable = new Sprite(0, 0, new Vector(0, 0), 1);
        sprites.add(new Planet(400, 400, new Vector(0, 0), 10000, Color.black));
        sprites.add(new Planet(400, 600, new Vector(-5000, 0), 100, Color.green));
        sprites.add(new Planet(600, 600, new Vector(-1000, 0), 5, Color.blue));
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            this.actionPerformed(new ActionEvent(this, 1, "pause"));
        }

        if (e.getKeyChar() == 'n') {
            this.actionPerformed(new ActionEvent(this, 1, "nextgen"));
        }

        if (e.getKeyChar() == 'f') {
            this.actionPerformed(new ActionEvent(this, 1, "fill"));
        }

        if (e.getKeyChar() == 'c') {
            this.actionPerformed(new ActionEvent(this, 1, "clear"));
        }


        if (e.getKeyChar() == 'd') {
            this.actionPerformed(new ActionEvent(this, 1, "rotate"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        grabFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //sets ups the panel and frame.  Probably not much to modify here.
    public static void main(String[] args) {
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHEIGHT + 24));

        JPanel panel = new Main(FRAMEWIDTH, FRAMEHEIGHT);
        panel.setFocusable(true);
        panel.grabFocus();


        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}