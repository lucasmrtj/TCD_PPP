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
		return new SpaceInvadersBoard(getGameConfig());
	}

	protected GameConfig getGameConfig() {
		return SpaceInvadersConfig.getInstance();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			new SpaceInvadersGame();
		});
	}

}
