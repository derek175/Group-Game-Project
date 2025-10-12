package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;

class SpaceSta extends Game {
	Player player;
	Alien alien;

  public SpaceSta() {
    super("SpaceStationSurvival",800,600);
    this.setFocusable(true);
	this.requestFocus();

	Point[] elementPoints = new Point[4];

	elementPoints[0] = new Point(0,0);
	elementPoints[1] = new Point(20,0);
	elementPoints[2] = new Point(20,20);
	elementPoints[3] = new Point(0,20);

	player = new Player(elementPoints, new Point(400,300), 0);

	Point[] alienPoints = new Point[3];

	alienPoints[0] = new Point(0,0);
	alienPoints[1] = new Point(10,30);
	alienPoints[2] = new Point(30,0);

	alien = new Alien(alienPoints, new Point(100, 300), 0, 10);

	this.addKeyListener(player);

	
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);

		// call the move method here before paint()
    
		brush.setColor(Color.red);
		player.paint(brush);

		brush.setColor(Color.green);
		alien.paint(brush);
  }
  
	public static void main (String[] args) {
   		SpaceSta a = new SpaceSta();
		a.repaint();
  	}
}