package game;
import java.awt.*;

// alien extends Polygon and implements Enemy interface

public class Alien extends Polygon implements Enemy{
    // has hp that player lowers by using lasers

    private int hp;

    public Alien(Point[] points, Point position, int rotation, int hp){
        super(points, position, rotation);
        this.hp = hp;
    }
    //method that draws the alien on the screen
    public void paint(Graphics brush){
        Point[] points = super.getPoints();

        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for(int i = 0; i < points.length; i++){
            xPoints[i] = (int) points[i].getX();
            yPoints[i] = (int) points[i].getY();
        }

        brush.drawPolygon(xPoints, yPoints, points.length);
        brush.fillPolygon(xPoints, yPoints, points.length);

    }

    // getters and setters for hp, color

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public Color getColor() {
        return Color.green;
    }

    // alien follows player to attack

    public void movement(Player player){

        // allows alien to follow player at a slower frame rate

        double rate = 1.0;

        // finds the distance from alien to player using distance/pythagoereom formula

        double dx = player.position.x - this.position.x;
        double dy = player.position.y - this.position.y;

        double distance = Math.sqrt((dx * dx) + (dy * dy));

        if(distance != 0){
            // vector is the hypotenuse 
            // normalization of a vector = unit vector
            // unit vector allows us to get a unit of a vector (length = 10) -> (length = 1)

            double x = dx/distance * rate;
            double y = dy/distance * rate;

            // we can use the unit vector to create an animation effect

            this.position.x += x;
            this.position.y += y;
        }
    }

    // inner class that houses StrongerAlien which has different color
    public static class StrongerAlien extends Alien{
    
        public StrongerAlien(Point[] points, Point position, int rotation, int hp){
            super(points, position, rotation, hp);
        }

        @Override
        public void paint(Graphics brush) {
            brush.setColor(Color.orange);
            super.paint(brush);
        }

        // returns color of stronger alien
        public Color getColor() {
            return Color.orange;
        }
    }
}
