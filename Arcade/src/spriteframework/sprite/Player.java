package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.GameConfig;

import java.awt.event.KeyEvent;

public class Player extends Sprite {
    private int width;
    protected int BOARD_HEIGHT, BOARD_WIDTH;
    protected int INIT_PLAYER_X, INIT_PLAYER_Y;

    public Player(String playerImage, int BOARD_HEIGHT, int BOARD_WIDTH, int INIT_PLAYER_X, int INIT_PLAYER_Y) {
        super();
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.INIT_PLAYER_X = INIT_PLAYER_X;
        this.INIT_PLAYER_Y = INIT_PLAYER_Y;

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

        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
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

        setX(INIT_PLAYER_X);
        setY(INIT_PLAYER_Y);
    }
}
