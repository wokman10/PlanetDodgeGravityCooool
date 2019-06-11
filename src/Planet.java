import java.awt.*;

public class Planet extends Sprite {

    public Planet(double ixpos, double iypos, Vector initial, double mass) {
        super(ixpos, iypos, initial, mass);
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public Shape getBoundingShape() {
        return null;
    }
}
