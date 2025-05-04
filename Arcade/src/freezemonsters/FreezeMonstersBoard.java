package freezemonsters;

import freezemonsters.sprite.*;
import spriteframework.AbstractBoard;
import spriteframework.sprite.Player;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FreezeMonstersBoard extends AbstractBoard {

    private Ray ray;

    public FreezeMonstersBoard(int BOARD_HEIGHT, int BOARD_WIDTH){
        super(FreezeMonstersConfig.getInstance().getPLAYER_IMAGE(), BOARD_HEIGHT, BOARD_WIDTH);
    }

    protected void createBadSprites(){
        ;
    }

    protected void createOtherSprites(){
        ;
    }

    protected void drawOtherSprites(Graphics g){
        ;
    }

    protected void update(){
        ;
    }

    protected void processOtherSprites(Player player, KeyEvent e){
        ;
    }

    @Override
    public int getDelay(){
        return 1;
    }

    @Override
    public int getINIT_PLAYER_X(){
        return 1;
    }

    @Override
    public int getINIT_PLAYER_Y(){
        return 1;
    }
}
