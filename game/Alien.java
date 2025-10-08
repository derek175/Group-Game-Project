package game;
import java.awt.*;

public class Alien extends Polygon implements Enemy{
    private int hp;

    public Alien(Point[] points, Point position, int rotation, int hp){
        super(points, position, rotation);
        this.hp = hp;
    }

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

    public int getHp(){
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }
}
