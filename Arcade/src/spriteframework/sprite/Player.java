package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.GameConfig;
import spriteframework.gamedirection.GameDirection;

import java.awt.event.KeyEvent;

public class Player extends Sprite {
    private int width;
    private int height;
    protected int BOARD_HEIGHT, BOARD_WIDTH;
    protected int INIT_PLAYER_X, INIT_PLAYER_Y;
    protected GameDirection gameDirection;

    public Player(String playerImage, int BOARD_HEIGHT, int BOARD_WIDTH, int INIT_PLAYER_X, int INIT_PLAYER_Y, GameDirection gameDirection) {
        super();

        // ISSOS AQUI SAI para colocar em Game Direction
        this.BOARD_HEIGHT = BOARD_HEIGHT;
        this.BOARD_WIDTH = BOARD_WIDTH;
        this.INIT_PLAYER_X = INIT_PLAYER_X;
        this.INIT_PLAYER_Y = INIT_PLAYER_Y;
        this.gameDirection = gameDirection;

        loadImage(playerImage);
		getImageDimensions();
		resetState();
    }

    protected void loadImage (String playerImage) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(playerImage));
        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());
    }
    
    public void act() {
        x += dx;
        y += dy;

        if (x <= 2) {
            x = 2;
        }
        if (y <= 2) {
            y = 2;
        }

        if (x >= BOARD_WIDTH - 2 * width) {
            x = BOARD_WIDTH - 2 * width;
        }
        if (y >= BOARD_HEIGHT - 2 * height){
            y = BOARD_HEIGHT - 2 * height;
        }
    }

    public void keyPressed(KeyEvent e) {
        gameDirection.move(e, this);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // Bidirecional (horizontal)
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0; // Para o movimento horizontal
        }

        // Quadridirecional (vertical)
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0; // Para o movimento vertical
        }
    }

    private void resetState() {

        setX(INIT_PLAYER_X);
        setY(INIT_PLAYER_Y);
    }
}
