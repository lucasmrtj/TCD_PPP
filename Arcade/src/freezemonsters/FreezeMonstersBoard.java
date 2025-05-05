package freezemonsters;

import freezemonsters.sprite.*;
import spaceinvaders.SpaceInvadersConfig;
import spaceinvaders.sprite.Shot;
import spriteframework.AbstractBoard;
import spriteframework.Direction;
import spriteframework.GameConfig;
import spriteframework.gamedirection.GameDirection;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class FreezeMonstersBoard extends AbstractBoard {

    private Ray ray;
    private LinkedList<Gosma> activeGosmas = new LinkedList<>();

    public FreezeMonstersBoard(GameConfig config){
        super(config);
    }

    protected void drawSpecificForEach(Graphics g) {
        g.setColor(new Color(127,255,0)); // Azul claro
        g.fillRect(0, 0, gameConfig.getBORDER_WIDTH(), gameConfig.getBORDER_HEIGHT());
    }

    protected void createBadSprites() {
        badSprites = new LinkedList<>();
        int boardWidth = gameConfig.getBORDER_WIDTH();
        int boardHeight = gameConfig.getBORDER_HEIGHT();
        int NUMBER_OF_MONSTERS = ((FreezeMonstersConfig) gameConfig).getNUMBER_OF_MONSTERS();

        Random rand = new Random();
        int monsterWidth = ((FreezeMonstersConfig) gameConfig).getMONSTER_WIDTH();
        int monsterHeight = ((FreezeMonstersConfig) gameConfig).getMONSTER_HEIGHT();

        for (int i = 0; i < NUMBER_OF_MONSTERS; i++) {
            int x, y;

            // Decide em qual borda o monstro vai aparecer (0: topo, 1: direita, 2: baixo, 3: esquerda)
            int border = rand.nextInt(4);

            switch (border) {
                case 0: // Topo
                    x = rand.nextInt(boardWidth - monsterWidth);
                    y = 0;
                    break;
                case 1: // Direita
                    x = boardWidth - monsterWidth;
                    y = rand.nextInt(boardHeight - monsterHeight);
                    break;
                case 2: // Baixo
                    x = rand.nextInt(boardWidth - monsterWidth);
                    y = boardHeight - monsterHeight;
                    break;
                case 3: // Esquerda
                    x = 0;
                    y = rand.nextInt(boardHeight - monsterHeight);
                    break;
                default:
                    x = 0;
                    y = 0;
            }

            Monsters monster = new Monsters(x, y);
            badSprites.add(monster);
        }
    }

    protected void createOtherSprites(){
        ray = new Ray();
    }

    private void drawRay(Graphics g) {
        if(ray.isVisible()) {
            g.drawImage(ray.getImage(), ray.getX(), ray.getY(), this);
        }
    }

    private void drawGosmas(Graphics g) {
        for (Gosma gosma : activeGosmas) {
            g.drawImage(gosma.getImage(), gosma.getX(), gosma.getY(), this);
        }
    }

    protected void drawOtherSprites(Graphics g){
        drawRay(g);
        drawGosmas(g);
    }

    protected void update(){
        // COndição de vitoria
        if (allMonstersFrozen()) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }
        // Configura o woody para andar
        // Player
        for(Player player : players){
            player.act();
            checkPlayerCollisions(player);
            //System.out.println(String.format("Player: (X, Y) | (%d, %d)", player.getX(), player.getY()));
        }

        // Atualiza monstros
        for (BadSprite monster : badSprites) {
            if (monster.isVisible() && !((Monsters)monster).isFrozen()) {
                ((Monsters) monster).moveRandomly(gameConfig.getBORDER_WIDTH(), gameConfig.getBORDER_HEIGHT());
            }

            LinkedList<Gosma> newGosmas = ((Monsters) monster).releaseGosma();
            activeGosmas.addAll(newGosmas);
        }

        updateOtherSprites();
    }

    private void updateOtherSprites() {
        updateRay();
        updateGosmas();
    }

    // Métodos auxiliares:
    private void updateRay() {
        if (ray.isVisible()) {
            ray.move();

            // Verifica colisão com monstros
            for (BadSprite badSprite : badSprites) {
                if (badSprite.isVisible() &&
                        ray.isCollidingWith((Monsters) badSprite) &&
                        !((Monsters) badSprite).isFrozen()) {

                    ((Monsters)badSprite).freeze();
                    ray.setVisible(false);
                    break;
                }
            }

            // Verifica se saiu da tela
            if (ray.isOutOfBounds(gameConfig.getBORDER_WIDTH(), gameConfig.getBORDER_HEIGHT())) {
                ray.setVisible(false);
            }
        }
    }

    private void updateGosmas() {
        Iterator<Gosma> iterator = activeGosmas.iterator();
        while (iterator.hasNext()) {
            Gosma gosma = iterator.next();
            gosma.move();

            // Remove gosmas que saíram da tela
            if (gosma.isOutOfBounds(gameConfig.getBORDER_WIDTH(), gameConfig.getBORDER_HEIGHT())) {
                iterator.remove();
            }
        }
    }

    private void checkPlayerCollisions(Player player) {
        // Cria retângulo de colisão do jogador
        Rectangle playerRect = new Rectangle(
                player.getX(),
                player.getY(),
                player.getImage().getWidth(null),
                player.getImage().getHeight(null)
        );

        // Verifica colisão com monstros
        checkMonsterCollisions(player, playerRect);

        // Verifica colisão com gosmas
        checkGosmaCollisions(player, playerRect);
    }

    private void checkMonsterCollisions(Player player, Rectangle playerRect) {
        for (BadSprite monster : badSprites) {
            if (monster.isVisible() && !((Monsters)monster).isFrozen()) {
                Rectangle monsterRect = new Rectangle(
                        monster.getX(),
                        monster.getY(),
                        monster.getImage().getWidth(null),
                        monster.getImage().getHeight(null)
                );

                if (playerRect.intersects(monsterRect)) {
                    playerHit(player);
                    return; // Sai após a primeira colisão
                }
            }
        }
    }

    private void checkGosmaCollisions(Player player, Rectangle playerRect) {
        Iterator<Gosma> iterator = activeGosmas.iterator();
        while (iterator.hasNext()) {
            Gosma gosma = iterator.next();
            Rectangle gosmaRect = new Rectangle(
                    gosma.getX(),
                    gosma.getY(),
                    gosma.getImage().getWidth(null),
                    gosma.getImage().getHeight(null)
            );

            if (playerRect.intersects(gosmaRect)) {
                iterator.remove();
                playerHit(player);
                return;
            }
        }
    }

    private void playerHit(Player player) {
        // Configura o estado de "dying" como no SpaceInvaders
        ImageIcon explosionIcon = new ImageIcon("images/explosion.png");
        Image scale = explosionIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        player.setImage(scale);
        player.setDying(true);
    }

    protected void processOtherSprites(Player player, KeyEvent e){
        int x = player.getX();
        int y = player.getY();
        Direction direction = player.getFacingDirection();

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (inGame) {
                if (!ray.isVisible()) {
                    ray = new Ray(x, y, direction);
                }
            }
        }
    }

    private boolean allMonstersFrozen() {
        for (BadSprite badSprite : badSprites) {
            if (badSprite.isVisible() && !((Monsters)badSprite).isFrozen()) {
                return false;
            }
        }
        return true;
    }

}
