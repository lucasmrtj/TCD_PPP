package freezemonsters.sprite;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class Monsters extends BadnessBoxSprite {
    private Gosma gosma;

    public Monsters(int x, int y) {
        initMonster(x, y);
    }

    private void initMonster(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        int randomNum = rand.nextInt(9) + 1;

        gosma = new Gosma(x, y);

        String alienImg = String.format("images/monster%d.png", randomNum);
        ImageIcon ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }

    public Gosma getGosma() {
        return gosma;
    }


    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> aGosma = new LinkedList<BadSprite>();
        aGosma.add(gosma);
        return aGosma;
    }
}
