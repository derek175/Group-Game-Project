package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Player extends Polygon implements KeyListener{


    private boolean movingUp;
    private boolean movingDown; 
    private boolean movingLeft;
    private boolean movingRight; 
    PlayerRadius circle;

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

        circle.paint(brush);
    }



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

        circle.update();
    }
}
