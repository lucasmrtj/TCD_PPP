package spriteframework.gamedirection;
import spriteframework.sprite.Player;

import java.awt.event.KeyEvent;

public abstract interface GameDirection {

    public abstract void move(KeyEvent e, Player player);
}
