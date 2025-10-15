package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.ArrayList;

class SpaceSta extends Game {

	private Player player;
	private Alien alien;
	private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Laser> lasers;
	private int score;
	Runnable handler;
	private long lastCollisionTime = 0;
	private final int COOLDOWN = 2000;

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
	this.addKeyListener(player);
	

	Point[] alienPoints = new Point[3];

	alienPoints[0] = new Point(0,0);
	alienPoints[1] = new Point(15,-30);
	alienPoints[2] = new Point(30,0);

	enemies = new ArrayList<>();
	lasers = new ArrayList<>();

	enemies.add(new Alien(alienPoints, new Point(100, 300), 0, 3));
	

	handler = new Runnable() {

		@Override
		public void run() {

            ArrayList<Enemy> enemiesToRemove = new ArrayList<>();

			//checks all lasers on screen
            for (Laser l : player.getLasers()) {

				//checks for all enemies on screen
                for (Enemy e : enemies) {

					//checks if laser collides with enemy to be removed
                    if (l.isAlive() && l.collides((Polygon) e)) {
                        e.setHp(e.getHp() - 1);
                        l.kill();

                        if (e.getHp() == 0) {
                            enemiesToRemove.add(e);
                        }
                    }
                }
            }

			long currentTime = System.currentTimeMillis();

			//checks for all enemies on screen
			for (Enemy e : enemies) {

				//checks if any enemy has touched the player and prevents player from constantly losing hp
				if (((Polygon) e).collides(player) && currentTime - lastCollisionTime >= COOLDOWN) {
					player.loseHp();
					lastCollisionTime = currentTime;
				}
			}
 
			//removes all enemies when they are killed
			enemies.removeAll(enemiesToRemove);
		}
	};

	if (player.getHp() == 0) {
		
	}
	alien = new Alien(alienPoints, new Point(100, 300), 0, 10);

  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);

		brush.setColor(Color.white);
        brush.drawString("HP: " + player.getHp(), 10, 20);
        brush.drawString("Score: " + score, 10, 40);

		// call the move method here before paint()

		player.movement();
		player.rotation();
		// call the move method here before paint()

		player.movement();
		alien.movement(player);
    
		brush.setColor(Color.red);
		player.paint(brush);

        player.updateLasers();
		brush.setColor(Color.green);
        for (Enemy e : enemies) {
            if (e instanceof Alien) {
                ((Alien) e).paint(brush);
            }
        }

		handler.run();
    }
		brush.setColor(Color.green);
		alien.paint(brush);
  }
  
	public static void main (String[] args) {
   		SpaceSta a = new SpaceSta();
		a.repaint();
  	}

}