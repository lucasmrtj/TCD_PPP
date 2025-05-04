package spaceinvaders;

import java.awt.EventQueue;

import spriteframework.AbstractBoard;
import spriteframework.GameConfig;
import spriteframework.MainFrame;

public class SpaceInvadersGame extends MainFrame {


	public SpaceInvadersGame () {
		super("Space Invaders");
	}
	
	protected AbstractBoard createBoard() {
		return new SpaceInvadersBoard(getBoardHeight(), getBoardWidth());
	}

	@Override
	protected int getBoardWidth() {
		return SpaceInvadersConfig.getInstance().getBORDER_WIDTH();
	}

	@Override
	protected int getBoardHeight() {
		return SpaceInvadersConfig.getInstance().getBORDER_HEIGHT();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new SpaceInvadersGame();
		});
	}

}
