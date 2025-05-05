package spriteframework.sprite;

import javax.swing.ImageIcon;

import spriteframework.Direction;
import spriteframework.GameConfig;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
    private int width;
    private int height;
    protected GameConfig gameConfig;
    private Direction facingDirection = Direction.RIGHT;

    public Player(GameConfig gameConfig) {
        super();
        this.gameConfig = gameConfig;

        loadImage(gameConfig.getPLAYER_IMAGE(), gameConfig.getPLAYER_WIDTH(), gameConfig.getPLAYER_HEIGHT());
		getImageDimensions();
		resetState();
    }

    private void loadImage(String imagePath, int targetWidth, int targetHeight) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image originalImage = originalIcon.getImage();

        // Redimensione a imagem
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

        // Atualize a imagem e dimensões do sprite
        setImage(scaledImage);
        this.width = targetWidth;
        this.height = targetHeight;
    }
    
    public void act() {
        int BOARD_WIDTH = gameConfig.getBORDER_WIDTH();
        int BOARD_HEIGHT = gameConfig.getBORDER_HEIGHT();

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
        gameConfig.getGameDirection().move(e, this);
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                facingDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                facingDirection = Direction.RIGHT;
                break;
            case KeyEvent.VK_UP:
                facingDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                facingDirection = Direction.DOWN;
                break;
        }
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

        setX(gameConfig.getINIT_PLAYER_X());
        setY(gameConfig.getINIT_PLAYER_Y());
    }

    public Direction getFacingDirection(){
        return facingDirection;
    }
}
