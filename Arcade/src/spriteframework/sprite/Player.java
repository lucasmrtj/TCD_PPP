package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.Commons;

import java.awt.Image;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
    private int width;

    public Player(String playerImage) {
        loadImage(playerImage);
		getImageDimensions();
		resetState();
    }

    protected void loadImage (String playerImage) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImage));
        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());
    }
    
    public void act() {
        x += dx;

        if (x <= 2) {
            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {
            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
    private void resetState() {

        setX(Commons.INIT_PLAYER_X);
        setY(Commons.INIT_PLAYER_Y);
    }
}
