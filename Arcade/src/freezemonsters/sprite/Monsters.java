package freezemonsters.sprite;

import freezemonsters.FreezeMonstersConfig;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;
import spriteframework.Direction; // Importando a enumeração
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Monsters extends BadnessBoxSprite {

    // Frozen
    private String frozenImg;
    private boolean frozen;

    // Movimento
    private Direction currentDirection; // Usando a enum
    private int directionChangeCounter;
    private final int DIRECTION_CHANGE_DELAY = 100;

    // Gosma
    private Gosma gosma;
    private int gosmaCooldown;
    private final int GOSMA_COOLDOWN_MAX = 250; // Frames ent

    public Monsters(int x, int y) {
        initMonster(x, y);
        this.frozen = false;
        this.currentDirection = getRandomDirection();
        this.directionChangeCounter = 0;
        this.gosmaCooldown = 0;
    }

    // Método auxiliar para obter direção aleatória
    private Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        return directions[new Random().nextInt(directions.length)];
    }

    public void moveRandomly(int boardWidth, int boardHeight) {
        if (!frozen) {
            directionChangeCounter++;

            // Troca de direção após um certo tempo
            if (directionChangeCounter >= DIRECTION_CHANGE_DELAY) {
                currentDirection = getRandomDirection();
                directionChangeCounter = 0;
            }

            int newX = getX();
            int newY = getY();
            int width = getImage().getWidth(null);
            int height = getImage().getHeight(null);

            // Movimento baseado na direção atual
            switch (currentDirection) {
                case UP: newY--; break;
                case DOWN: newY++; break;
                case LEFT: newX--; break;
                case RIGHT: newX++; break;
            }

            // Verifica colisão com bordas e inverte direção
            if (newX <= 0) {
                currentDirection = Direction.RIGHT;
            } else if (newX >= boardWidth - width) {
                currentDirection = Direction.LEFT;
            } else if (newY <= 0) {
                currentDirection = Direction.DOWN;
            } else if (newY >= boardHeight - height) {
                currentDirection = Direction.UP;
            } else {
                setX(newX);
                setY(newY);
            }
        }
    }

    private void initMonster(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        int randomNum = rand.nextInt(9) + 1;

        String monsterImg = String.format("images/monster%d.png", randomNum);
        frozenImg = String.format("images/monster%dbg.png", randomNum);

        ImageIcon ii = new ImageIcon(monsterImg);
        Image scaledImage = ii.getImage().getScaledInstance(
                FreezeMonstersConfig.getInstance().getRAY_WIDTH(),
                FreezeMonstersConfig.getInstance().getRAY_HEIGHT(),
                Image.SCALE_SMOOTH
        );
        setImage(scaledImage);
    }

    public void freeze() {
        this.frozen = true;
        ImageIcon ii = new ImageIcon(frozenImg);
        Image scaledImage = ii.getImage().getScaledInstance(FreezeMonstersConfig.getInstance().getRAY_WIDTH(), FreezeMonstersConfig.getInstance().getRAY_HEIGHT(), Image.SCALE_SMOOTH);
        setImage(scaledImage);
    }

    public boolean isFrozen() {
        return frozen;
    }

    public Gosma getGosma() {
        return gosma;
    }


    public LinkedList<Gosma> releaseGosma() {
        LinkedList<Gosma> newGosmas = new LinkedList<>();

        if (!frozen && gosmaCooldown <= 0) {
            // Centraliza a gosma no monstro
            int gosmaX = this.x + this.getImage().getWidth(null)/2 - 15;
            int gosmaY = this.y + this.getImage().getHeight(null)/2 - 15;

            newGosmas.add(new Gosma(gosmaX, gosmaY, currentDirection));
            gosmaCooldown = GOSMA_COOLDOWN_MAX;
        } else {
            gosmaCooldown--;
        }

        return newGosmas;
    }
}