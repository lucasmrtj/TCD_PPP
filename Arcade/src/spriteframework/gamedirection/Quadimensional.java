package spriteframework.gamedirection;

import spriteframework.sprite.Player;
import java.awt.event.KeyEvent;

// Todas as quatro direções
public class Quadimensional implements GameDirection{
    public void move(KeyEvent e, Player player) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setDx(-2);
        }

        if (key == KeyEvent.VK_RIGHT) {
            player.setDx(2);
        }

        if (key == KeyEvent.VK_UP){
            player.setDy(-2);
        }

        if (key == KeyEvent.VK_DOWN){
            player.setDy(2);
        }
    }
}
