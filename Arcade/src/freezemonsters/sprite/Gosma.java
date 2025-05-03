package freezemonsters.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;

public class Gosma extends BadSprite {

    private boolean destroyed;

    public Gosma(int x, int y) {

        initGosma(x, y);
    }

    private void initGosma(int x, int y) {
        setDestroyed(true);

        this.x = x;
        this.y = y;

        String gosmaImg = "images/gosma.png";
        ImageIcon ii = new ImageIcon(gosmaImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
