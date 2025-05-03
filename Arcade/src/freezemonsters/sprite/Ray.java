package freezemonsters.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;

public class Ray extends BadSprite {

    public Ray() {
    }

    public Ray(int x, int y) {
        initShot(x, y);
    }

    private void initRay(int x, int y) {

        String shotImg = "images/ray.png";
        ImageIcon ii = new ImageIcon(shotImg);
        setImage(ii.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
