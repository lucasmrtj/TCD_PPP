package freezemonsters.sprite;

import spriteframework.Direction;
import spriteframework.sprite.BadSprite;

import javax.swing.*;
import java.awt.*;

public class Gosma extends BadSprite {

    private boolean active;
    private final Direction direction;

    public Gosma(int x, int y, Direction direction) {
        this.direction = direction;
        initGosma(x, y);
    }

    private void initGosma(int x, int y) {
        setActive(true);
        this.x = x;
        this.y = y;

        String gosmaImg = "images/gosma.png";
        ImageIcon ii = new ImageIcon(gosmaImg);


        int width = 50;
        int height = 30;

        // Redimensiona a imagem
        Image scaledImage = ii.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        setImage(scaledIcon.getImage());
    }

        public void move() {
        switch (direction) {
            case UP:
                y -= 2;
                break;
            case DOWN:
                y += 2;
                break;
            case LEFT:
                x -= 2;
                break;
            case RIGHT:
                x += 2;
                break;
        }
    }

        public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
