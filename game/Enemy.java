package game;
import java.awt.*;

public interface Enemy {
    // made for enemies 

    int hp = 10;

    public void paint(Graphics brush);

    int getHp();
    void setHp(int hp);

    
}
