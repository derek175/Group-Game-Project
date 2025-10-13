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
        this.movingUp = false;
        this.movingDown = false;
        this.movingLeft = false;
        this.movingRight = false;
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
    }

    // if enemy steps into player radius
   // public class playerRadius(){}
    



    //movement and keyboard responsiveness

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
        // position offsets points 
        int spaces = 5;
        if (movingUp == true) {
           position.y -= spaces;
        }
        if (movingDown == true) {
            position.y += spaces;
        }
        if (movingLeft == true) {
            position.x -= spaces;
        }
        if (movingRight == true) {
            position.x += spaces;
        }
    }
}
