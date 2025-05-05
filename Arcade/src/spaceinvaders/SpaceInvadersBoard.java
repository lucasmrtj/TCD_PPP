package spaceinvaders;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;

import spriteframework.AbstractBoard;
import spriteframework.gamedirection.GameDirection;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import spaceinvaders.sprite.*;

public class SpaceInvadersBoard extends AbstractBoard {
    //define sprites
    //private List<BadSprite> aliens;
    private Shot shot;    
    
    // define global control vars   
    private int direction = -1;
    private int deaths = 0;
    private String explImg = "images/explosion.png";

    public SpaceInvadersBoard( int BOARD_HEIGHT, int BOARD_WIDTH){
        super(SpaceInvadersConfig.getInstance().getPLAYER_IMAGE(), BOARD_HEIGHT, BOARD_WIDTH);
    }

    protected void createBadSprites() {  // create sprites
        int NUMBER_OF_ALIENS_ROW = SpaceInvadersConfig.getInstance().getNUMBER_OF_ALIENS_ROW();
        int NUMBER_OF_ALIENS_COL = SpaceInvadersConfig.getInstance().getNUMBER_OF_ALIENS_COL();
        int ALIEN_INIT_X = SpaceInvadersConfig.getInstance().getALIEN_INIT_X();
        int ALIEN_INIT_Y = SpaceInvadersConfig.getInstance().getALIEN_INIT_Y();

        for (int i = 0; i < NUMBER_OF_ALIENS_ROW; i++) {
            for (int j = 0; j < NUMBER_OF_ALIENS_COL; j++) {
                BomberSprite alien = new BomberSprite(ALIEN_INIT_X + 18 * j,
                        ALIEN_INIT_Y + 18 * i);
                badSprites.add(alien);
            }
        }
    }
    
    protected void createOtherSprites() {
        shot = new Shot();
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    // Override
    protected void drawOtherSprites(Graphics g) {
            drawShot(g);
    }
    
    protected void processOtherSprites(Player player, KeyEvent e) {
		int x = player.getX();
		int y = player.getY();

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE) {
			if (inGame) {
				if (!shot.isVisible()) {
					shot = new Shot(x, y);
				}
			}
		}
	}

//    private void gameOver(Graphics g) {
//
//        g.setColor(Color.black);
//        g.fillRect(0, 0, Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
//
//        g.setColor(new Color(0, 32, 48));
//        g.fillRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
//        g.setColor(Color.white);
//        g.drawRect(50, Commons.BOARD_WIDTH / 2 - 30, Commons.BOARD_WIDTH - 100, 50);
//
//        Font small = new Font("Helvetica", Font.BOLD, 14);
//        FontMetrics fontMetrics = this.getFontMetrics(small);
//
//        g.setColor(Color.white);
//        g.setFont(small);
//        g.drawString(message, (Commons.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
//                Commons.BOARD_WIDTH / 2);
//    }

    protected void update() {

        if (deaths == SpaceInvadersConfig.getInstance().getNUMBER_OF_ALIENS_TO_DESTROY()) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        for (Player player: players) 
        	player.act();

        // shot
        if (shot.isVisible()) {

            int shotX = shot.getX();
            int shotY = shot.getY();

            for (BadSprite alien : badSprites) {

                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && shot.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + SpaceInvadersConfig.getInstance().getALIEN_WIDTH())
                            && shotY >= (alienY)
                            && shotY <= (alienY +SpaceInvadersConfig.getInstance().getALIEN_HEIGHT())) {

                        ImageIcon ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens
        for (BadSprite alien : badSprites) {
            int x = alien.getX();

            if (x >= BOARD_WIDTH - SpaceInvadersConfig.getInstance().getBORDER_RIGHT() && direction != -1) {

                direction = -1;

                Iterator<BadSprite> i1 = badSprites.iterator();

                while (i1.hasNext()) {
                    BadSprite a2 = i1.next();
                    a2.setY(a2.getY() + SpaceInvadersConfig.getInstance().getGO_DOWN());
                }
            }

            if (x <= SpaceInvadersConfig.getInstance().getBORDER_LEFT() && direction != 1) {

                direction = 1;

                Iterator<BadSprite> i2 = badSprites.iterator();

                while (i2.hasNext()) {

                    BadSprite a = i2.next();
                    a.setY(a.getY() + SpaceInvadersConfig.getInstance().getGO_DOWN());
                }
            }
        }

        Iterator<BadSprite> it = badSprites.iterator();

        while (it.hasNext()) {
            BadSprite alien = it.next();
            if (alien.isVisible()) {
                int y = alien.getY();
                if (y > SpaceInvadersConfig.getInstance().getGROUND() - SpaceInvadersConfig.getInstance().getALIEN_HEIGHT()) {
                    inGame = false;
                    message = "Invasion!";
                }
                alien.moveX(direction);
            }
        }
        // bombs
        updateOtherSprites();
    }

    protected void updateOtherSprites() {
		Random generator = new Random();

        for (BadSprite alien : badSprites) {

            int shot = generator.nextInt(10);
            Bomb bomb = ((BomberSprite)alien).getBomb();

            if (shot == SpaceInvadersConfig.getInstance().getCHANCE() && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = players.get(0).getX();
            int playerY = players.get(0).getY();

            if (players.get(0).isVisible() && !bomb.isDestroyed()) {
                if (bombX >= (playerX)
                        && bombX <= (playerX + SpaceInvadersConfig.getInstance().getPLAYER_WIDTH())
                        && bombY >= (playerY)
                        && bombY <= (playerY + SpaceInvadersConfig.getInstance().getPLAYER_HEIGHT()))
                {
                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);
                if (bomb.getY() >= SpaceInvadersConfig.getInstance().getGROUND() - SpaceInvadersConfig.getInstance().getBOMB_HEIGHT()) {
                    bomb.setDestroyed(true);
                }
            }
        }
	}

    protected int getDelay(){
        return SpaceInvadersConfig.getInstance().getDELAY();
    }

    protected int getINIT_PLAYER_X(){
        return SpaceInvadersConfig.getInstance().getINIT_PLAYER_X();
    }

    protected int getINIT_PLAYER_Y(){
        return SpaceInvadersConfig.getInstance().getINIT_PLAYER_Y();
    }

    protected GameDirection getGameDirection(){
        return SpaceInvadersConfig.getInstance().getGameDirection();
    }
}

