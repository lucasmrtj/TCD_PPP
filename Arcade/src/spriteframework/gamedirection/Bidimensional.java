package spriteframework.gamedirection;

import spriteframework.sprite.Player;

import java.awt.event.KeyEvent;

// Move apenas para a esquerda e para a direita
public class Bidimensional implements GameDirection {

    public void move(KeyEvent e, Player player) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setDx(-2);
        }

        if (key == KeyEvent.VK_RIGHT) {
            player.setDx(2);
        }
    }

}
