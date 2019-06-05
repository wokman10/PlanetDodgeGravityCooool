import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * This class models a rectangular movable object on the screen.  It is drawn using an image.
 */
public abstract class Sprite {

    public static final int NORTH = 90, SOUTH = 270, WEST = 180, EAST = 0, NE = 45, NW = 135, SW = 225, SE = 315;
    private static int nextID = 1; //static instance fields- there is ONE shared variable for all objects of this class.
    private double xpos;
    private double ypos;
    private double xdelta;
    private double ydelta;
    private double mass;
    private int id;


    public Sprite() {

        id = nextID;
        nextID++;
    }

    /**
     * This draws the image pic at the Point loc, rotated to face dir.
     */

    public void draw(Graphics2D g2) {
        draw(g2, 1);
    }

    public void draw(Graphics2D g2, float opacity) {
    }

    /**
     * Moves the pic in the direction the Sprite is facing (dir).
     */
    public void update() {
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
    public abstract Shape getBoundingShape();

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
}