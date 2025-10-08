package game;
import java.awt.*;

public interface Enemy {

    int hp = 10;

    public void paint(Graphics brush);

    int getHp();
    void setHp(int hp);

    
}
