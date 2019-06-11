public class ForceHelper {

    private static ForceHelper instance = new ForceHelper();

    public static ForceHelper getInstance() {
        if (instance == null) {
            instance = new ForceHelper();
        }
        return instance;
    }

    private static int framewidth = 800, frameheight = 800;
    private static double speedOfLight = 1000; // m/s
    private static double gravitationalconstant = 0.000667;

    public ForceHelper() {
    }

    //returns newtons kg * m/s/s?
    public static double doGravity(Sprite a, Sprite b) {
        double distance = a.distanceTo(b);
        return gravitationalconstant * a.getMass() * b.getMass() / distance / distance;
    }

    public static Vector acceleration(Sprite a, Sprite b) {
        double magnitute = doGravity(a, b);
        double angle = a.angleTo(b);
        return new Vector(a.getMass() * magnitute * Math.cos(angle), a.getMass() * magnitute * Math.sin(angle));
    }
}
