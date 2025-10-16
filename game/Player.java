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

    private int score = 0;
    private int hp = 3;
    private ArrayList<Laser> lasers = new ArrayList<>();
    private long lastShotTime = 0;
    private int COOLDOWN = 150;
    private PlayerRadius circle;
    private boolean gameOver;

    public Player(Point[] points, Point position, double rotation){
        super(points, position, rotation);
        this.movingUp = false;
        this.movingDown = false;
        this.movingLeft = false;
        this.movingRight = false;

        circle = new PlayerRadius(100);
    }

    // if enemy steps into player radius, shoot
    private class PlayerRadius{

        Point[] circlePoints = new Point[16];
        private int radius;

        public PlayerRadius(int radius){
            this.radius = radius;
            createCircle();
        }

        public void createCircle(){
            // use polar coordinates to get (x,y) points
            // center offset so that player radius moves with player
            double centerX = position.x;
            double centerY = position.y;

            // theta/angle = 2pi / 16 
            // gets 16 points around a circle

            double angle = (2 * Math.PI)/ 16;

            for(int i = 0; i < circlePoints.length; i++){

                // r * cos0 = x coordinate of circle
                // r * sin0 = y coordinate of circle

                double x = centerX + radius * Math.cos(angle * i);
                double y = centerY + radius * Math.sin(angle * i);

                circlePoints[i] = new Point(x, y);
            }

        }

        // test circle, take out later

        public void paint(Graphics brush) {
            int[] xPoints = new int[circlePoints.length];
            int[] yPoints = new int[circlePoints.length];

            for (int i = 0; i < circlePoints.length; i++) {
                xPoints[i] = (int) circlePoints[i].x;
                yPoints[i] = (int) circlePoints[i].y;
            }

            brush.setColor(Color.BLUE);
            brush.drawPolygon(xPoints, yPoints, circlePoints.length);
        }

        public void update() {
            createCircle(); // Recalculate based on new Player position
        }

    //if alien steps into radius, trigger laser to hit alien; collision
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

        circle.paint(brush);

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameOver(boolean value){

        gameOver = value;
    }

    //movement and keyboard responsiveness
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
        if(gameOver){
            return;
        }
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

        circle.update();
    }

}
