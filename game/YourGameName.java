package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;

class YourGameName extends Game {
	static int counter = 0;
	Player player;

  public YourGameName() {
    super("YourGameName!",800,600);
    this.setFocusable(true);
	this.requestFocus();

	Point[] elementPoints = new Point[4];

	elementPoints[0] = new Point(0,0);
	elementPoints[1] = new Point(20,0);
	elementPoints[2] = new Point(20,20);
	elementPoints[3] = new Point(0,20);

	player = new Player(elementPoints, new Point(400,300), 0);
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	// counter++;
    	// brush.setColor(Color.white);
    	// brush.drawString("Counter is " + counter,10,10);

		brush.setColor(Color.red);

		player.paint(brush);
  }
  
	public static void main (String[] args) {
   		YourGameName a = new YourGameName();
		a.repaint();
  }
}