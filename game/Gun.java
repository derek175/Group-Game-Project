package game;

import java.awt.*;
import java.awt.event.*;

public class Gun extends Polygon {

    //initializes gun points to be painted and its given positon and rotation
    public Gun(Point position, double rotation) {
        super(new Point[] {new Point(-1,1), new Point(-1,1), new Point(1, -1), new Point(-1, -1)}, position, rotation);
    }

    //updates position and rotation of gun when moved
    public void updateElement(Point newPosition, double newRotation) {

        this.position = newPosition;
        this.rotation = newRotation;
    }

    //gets the point where bullets will come out from
    public Point getSpawnPoint() {

        Point[] current = getPoints();
        Point spawn = current[0];
        for (Point p : current) {
            if (p.x > spawn.x) {
                spawn = p;
            }
        }

        return spawn;
    }
    
}