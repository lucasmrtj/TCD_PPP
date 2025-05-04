package freezemonsters;

import spaceinvaders.SpaceInvadersConfig;
import spriteframework.AbstractBoard;
import spriteframework.MainFrame;

import java.awt.*;

public class FreezeMonstersGame extends MainFrame {
    public FreezeMonstersGame() {
        super("Freeze Monsters");
    }

    protected AbstractBoard createBoard() {
        return new FreezeMonstersBoard(getBoardHeight(), getBoardWidth());
    }

    @Override
    protected int getBoardWidth() {
        return FreezeMonstersConfig.getInstance().getBORDER_WIDTH();
    }

    @Override
    protected int getBoardHeight() {
        return FreezeMonstersConfig.getInstance().getBORDER_HEIGHT();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FreezeMonstersGame();
        });
    }
}
