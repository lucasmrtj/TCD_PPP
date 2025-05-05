package spriteframework.gamedirection;
import spriteframework.sprite.Player;

import java.awt.event.KeyEvent;

public interface GameDirection {

    void move(KeyEvent e, Player player);
}
