package game;

import java.awt.*;
import java.awt.event.*;

public class Powerup extends Polygon{

    private boolean isAlive;

    public Powerup(Point[] points, Point position, double rotation){
        super(points, position, rotation);
        isAlive = true;
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

    public boolean collidesWith(Player player) {

        isAlive = false;
        return true;
    }
}