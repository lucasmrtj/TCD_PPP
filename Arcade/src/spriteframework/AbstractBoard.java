package spriteframework;


import javax.swing.JPanel;
import javax.swing.Timer;

import spriteframework.gamedirection.GameDirection;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;


public abstract class AbstractBoard extends JPanel {

    protected Dimension d;

    protected LinkedList<Player> players;
    protected LinkedList<BadSprite> badSprites;

    private int numberPlayers;  // to do - future use
    protected boolean inGame = true;
    protected String message = "Game Over";

    protected Timer timer;
    protected GameConfig gameConfig;

    // Frozen Spots
    //  void initBoard()
    // 
    // HotSpots
    protected abstract void createBadSprites();
    protected abstract void createOtherSprites();
    protected abstract void drawOtherSprites(Graphics g);
    protected abstract void update();
    protected abstract void processOtherSprites(Player player, KeyEvent e);
    protected abstract void drawSpecificForEach(Graphics g);

    public AbstractBoard(GameConfig gameConfig) {
        // Para facilitar a vida na hora de montar o board
        this.gameConfig = gameConfig;

        initBoard();

        numberPlayers = 1;
        badSprites = new LinkedList<BadSprite>();
        createBadSprites();
        createOtherSprites();
		//shot = new Shot();
    }

    private void initBoard() {

    	addKeyListener(new TAdapter());
    	setFocusable(true);
    	d = new Dimension(gameConfig.getBORDER_WIDTH(), gameConfig.getBORDER_HEIGHT());
    	setBackground(Color.black);

    	timer = new Timer(gameConfig.getDelay(), new GameCycle());
    	timer.start();

    	createPlayers();
    	numberPlayers = 1;
    	badSprites = new LinkedList<BadSprite>();
    	createBadSprites();
    	createOtherSprites();
    }


    protected void createPlayers() {
		players = new LinkedList<Player>();
        players.add(createPlayer());
	}
	
	protected Player createPlayer() {
		return new Player(gameConfig);
	}

    public Player getPlayer(int i) {
	   if (i >=0 && i<players.size())
		   return players.get(i);
	   return null;
    }
   
    private void drawBadSprites(Graphics g) {

        for (BadSprite bad : badSprites) {
            if (bad.isVisible()) {
                g.drawImage(bad.getImage(), bad.getX(), bad.getY(), this);
            }

            if (bad.isDying()) {
                bad.die();
            }

            if (bad.getBadnesses()!= null) {
            	for (BadSprite badness: bad.getBadnesses()) {
            		if (!badness.isDestroyed()) {
            			g.drawImage(badness.getImage(), badness.getX(), badness.getY(), this);
            		}
            	}
            }
        }
    }

    private void drawPlayers(Graphics g) {
    	for (Player player: players) {
    		if (player.isVisible()) {
    			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
    		}

    		if (player.isDying()) {
    			player.die();
    			inGame = false;
    		}
    	}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g1) { // Template Method
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            drawSpecificForEach(g);
            drawBadSprites(g);
            drawPlayers(g);
            drawOtherSprites(g);

        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            gameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, gameConfig.getBORDER_WIDTH(), gameConfig.getBORDER_HEIGHT());

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, gameConfig.getBORDER_WIDTH() / 2 - 30, gameConfig.getBORDER_HEIGHT() - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, gameConfig.getBORDER_WIDTH() / 2 - 30, gameConfig.getBORDER_HEIGHT() - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (gameConfig.getBORDER_WIDTH() - fontMetrics.stringWidth(message)) / 2,
                gameConfig.getBORDER_WIDTH() / 2);
    }

    private void doGameCycle() {
        update();
        repaint();
    }

	private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            for (Player player: players)
                 player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	for (Player player: players) {
                player.keyPressed(e);
                processOtherSprites(player, e); // hotspot
        	}
        }
    }
}
