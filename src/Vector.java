public class Vector {

    public double dx, dy;

    public Vector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Vector add(Vector a, Vector b) {
        return new Vector(a.dx + a.dy, b.dx + b.dy);
    }

    public Vector add(Vector other) {
        dx += other.dx;
        dy += other.dy;
        return this;
    }

    public Vector coeffecient(double d) {
        dx *= d;
        dy *= d;
        return this;
    }

    public Vector invert() {
        return new Vector(-this.dx, -this.dy);
    }

    public double magnitute() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "dx=" + dx +
                ", dy=" + dy +
                '}';
    }
}
