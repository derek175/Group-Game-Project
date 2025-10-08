package game;

public class Laser {

    private Point position;
    private double vx;
    private double vy;
    private final int SIZE = 1;
    private boolean isAlive = true;

    public Laser(double x, double y, double vx, double vy) {

        position = new Point(x, y);
        this.vx = vx;
        this.vy = vy;
    }

    public void update() {

        position.x += vx;
        position.y += vy;
        
        if (position.x < -50 || position.x > 800 || position.y < -50 || position.y > 600) {
            isAlive = false;
        }
    }

    /*
    public boolean collidesWith(Alien alien) {

    }
    */
}
