package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class Player extends Polygon implements KeyListener{

    private boolean movingUp;
    private boolean movingDown; 
    private boolean movingLeft;
    private boolean movingRight; 
    private boolean shooting;

    private int hp = 3;
    private ArrayList<Laser> lasers = new ArrayList<>();
    private long lastShotTime = 0;
    private final int COOLDOWN = 150;

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
        brush.fillPolygon(xPoints, yPoints, points.length);

        for (Laser l : lasers) l.paint(brush);
    }

    public ArrayList<Laser> getLasers() {
        return lasers;
    }

    public int getHp() {
        return hp;
    }

    public void loseHp() {
        hp--;
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
        if (pressed == KeyEvent.VK_SPACE) {
            shooting = true;
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
        if (pressed == KeyEvent.VK_SPACE) {
            shooting = false;
        }
    }

    public void movement() {
        // 
        int spaces = 2;
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

    // set rotation based on current movement
    public void rotation() {

        if (movingUp && movingLeft) {
            rotation = 315; 
        } 
        else if (movingUp && movingRight) {
            rotation = 45;
        } 
        else if (movingDown && movingLeft) {
            rotation = 225;
        } 
        else if (movingDown && movingRight) {
            rotation = 135;
        } 
        else if (movingUp) {
            rotation = 0;
        } 
        else if (movingDown) {
            rotation = 180;
        } 
        else if (movingLeft) {
            rotation = 270;
        } 
        else if (movingRight) {
            rotation = 90;
        }
    }

    public void updateLasers() {

        long currentTime = System.currentTimeMillis();

        //delay in bewteen shots so there no constant beam
        if (shooting && currentTime - lastShotTime > COOLDOWN) {
            lastShotTime = currentTime;

            //fires in direction based on player rotation
            double angle = Math.toRadians(rotation - 90);
            double vx = Math.cos(angle) * 5;
            double vy = Math.sin(angle) * 5;
            lasers.add(new Laser(position.x + 3, position.y + 3, vx, vy));
        }

        //updates laser position
        for (int i = 0; i < lasers.size(); i++) {
            Laser l = lasers.get(i);
            l.update();

            //deletes laser if collided with enemy or at end of screen
            if (!l.isAlive()) {
                lasers.remove(i);
                i--;
            }
        }
    }

}
