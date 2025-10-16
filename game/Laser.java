package game;
import java.awt.*;

public class Laser extends Polygon {

    private double vx;
    private double vy;
    private boolean isAlive = true;

    // intialize laser and it's overrall shape
    public Laser(double x, double y, double vx, double vy) {
        super(new Point[] {new Point(-2, -2), new Point(2, -2), new Point(2, 2), new Point(-2, 2)}, new Point(x, y), 0);
        position = new Point(x, y);
        this.vx = vx;
        this.vy = vy;
    }

    // paints the laser inot the game 
    public void paint(Graphics brush) {
        brush.setColor(Color.yellow);

        Point[] points = super.getPoints();
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for (int i = 0; i < points.length; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }

        brush.fillPolygon(xPoints, yPoints, points.length);
    }

    // updates the position of laser based off x and y velocity
    public void update() {

        position.x += vx;
        position.y += vy;
        
        // removes laser when off screen
        if (position.x < -50 || position.x > 800 || position.y < -50 || position.y > 600) {
            kill();
        }
    }

    // returns if laser value is alive
    public boolean isAlive() {
        return isAlive;
    }

    // removes laser off screen
    public void kill() {
        isAlive = false;
    }
}
