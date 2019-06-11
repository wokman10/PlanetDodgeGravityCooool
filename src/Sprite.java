import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

/**
 * This class models a rectangular movable object on the screen.  It is drawn using an image.
 * Sprite is a moving object
 */
public class Sprite {

    private static int nextID = 1; //static instance fields- there is ONE shared variable for all objects of this class.
    private static double standardRadius = 10;

    private double xpos;
    private double ypos;
    private Vector delta; //m/s
    private double mass; // kg
    private double radius;
    private Color color;
    private int id;

    private double small = 1. / 60;

    public Sprite(double xpos, double ypos, Vector initial, double mass) {
        this(xpos, ypos, initial, mass, standardRadius, new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
    }

    public Sprite(double ixpos, double iypos, Vector initial, double mass, double radius, Color color) {
        xpos = ixpos;
        ypos = iypos;
        delta = initial;
        this.mass = mass;
        this.radius = radius;
        this.color = color;

        id = nextID;
        nextID++;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval((int)xpos, (int)ypos, (int)(2 * radius), (int)(2 * radius));
        g2.setColor(Color.BLACK);
    }

    public void updatedelta(ArrayList<Sprite> otherplanets) {
        Vector gravitationalAcceleration = new Vector(0,0);
        for (Sprite p : otherplanets) {
            if (p != this) {
                gravitationalAcceleration.add(ForceHelper.acceleration(p, this));
//                System.out.println(gravitationalAcceleration);
            }
        }
        delta.add(gravitationalAcceleration);
    }

    public void update(ArrayList<Sprite> otherplanets) {
        updatedelta(otherplanets);
        xpos += delta.dx * small;
        ypos += delta.dy * small;
    }

    /**
     * Returns true if this Sprite intersects the other Sprite
     */
    public boolean intersects(Sprite other) {
        Area areaA = new Area(other.getBoundingShape());
        areaA.intersect(new Area(this.getBoundingShape()));
        return !areaA.isEmpty();
    }

    /**
     * Returns a Rectangle object that surrounds this Sprite's pic.
     * Useful for hit detection.  Really useful.
     *
     * @return the bounding Rectangle.
     */
    public Shape getBoundingShape() {
        return null;
    }

    public int getID() {
        return id;
    }

    /**
     * Overrides the equals method.
     *
     * @param o should be a Sprite
     * @return true if the sprites share the same ID
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sprite)) //if not a Sprite...false
            return false;
        Sprite other = (Sprite) o;  //cast Object to Sprite variable
        if (other.getID() == getID())    //if ID's match...
            return true;
        return false;
    }

    public Vector getDelta() {
        return delta;
    }

    public void setDelta(Vector delta) {
        this.delta = delta;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getXpos() {
        return xpos;
    }

    public double getYpos() {
        return ypos;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double distanceTo(Sprite other) {
        return Math.sqrt((xpos - other.getXpos()) * (xpos - other.getXpos()) + (ypos - other.getYpos()) * (ypos - other.getYpos()));
    }

    public double angleTo(Sprite other) {
//        return Math.atan2((other.getYpos() - ypos), (other.getXpos() - xpos));
        return Math.atan2((ypos - other.getYpos()), (xpos - other.getXpos()));
    }
}