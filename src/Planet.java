import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Planet extends Sprite {

    public Planet(double ixpos, double iypos, Vector initial, double mass) {
        this(ixpos, iypos, initial, mass, new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)));
    }

    public Planet(double ixpos, double iypos, Vector initial, double mass, Color color) {
        super(ixpos, iypos, initial, mass, Math.pow(mass, 1./4) * 2, color);
    }

    @Override
    public Shape getBoundingShape() {
        return new Ellipse2D.Double(super.getXpos(), super.getYpos(), super.getRadius() * 2, super.getRadius() * 2);
    }
}
