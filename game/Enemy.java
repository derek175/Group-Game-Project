package game;
import java.awt.*;

public interface Enemy {
    // made for enemies to implement
    // all enemies must have hp, a paint method, and a getter + setter for hp

    int hp = 10;

    public void paint(Graphics brush);

    int getHp();
    void setHp(int hp);

    
}
