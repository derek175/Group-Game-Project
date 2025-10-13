package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Player extends Polygon implements KeyListener{

    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public Player(Point[] points, Point position, double rotation){
        super(points, position, rotation);
    }

    public void paint(Graphics brush){
        Point[] points = super.getPoints();

        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for(int i = 0; i < points.length; i ++){
            xPoints[i] = (int) points[i].getX();
            yPoints[i] = (int) points[i].getY();
        }

        brush.drawPolygon(xPoints, yPoints, points.length);
        brush.fillPolygon(xPoints, yPoints, points.length);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
        if (pressed == KeyEvent.VK_UP) {
            movingUp = true;
        }
        if (pressed == KeyEvent.VK_DOWN) {
            movingDown = true;
        }
        if (pressed == KeyEvent.VK_LEFT) {
            movingLeft = true;
        }
        if (pressed == KeyEvent.VK_RIGHT) {
            movingRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int pressed = e.getKeyCode();
        if (pressed == KeyEvent.VK_UP) {
            movingUp = false;
        }
        if (pressed == KeyEvent.VK_DOWN) {
            movingDown = false;
        }
        if (pressed == KeyEvent.VK_LEFT) {
            movingLeft = false;
        }
        if (pressed == KeyEvent.VK_RIGHT) {
            movingRight = false;
        }
    }

    public void movement() {
        Point[] points = super.getPoints();
        if (movingUp = true) {
            for (Point p : points) {
                p.y += 2;
            }
        }
        if (movingDown = true) {
            for (Point p : points) {
                p.y -= 2;
            }
        }
        if (movingLeft = true) {
            for (Point p : points) {
                p.x += 2;
            }
        }
        if (movingRight = true) {
            for (Point p : points) {
                p.x -= 2;
            }
        }
    }
}
