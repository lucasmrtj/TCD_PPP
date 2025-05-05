package freezemonsters;

import spriteframework.GameConfig;
import spriteframework.gamedirection.*;

import javax.swing.*;

public class FreezeMonstersConfig extends GameConfig {
    private static FreezeMonstersConfig instance;

    private int RAY_WIDTH = 50;
    private int RAY_HEIGHT = 50;

    private int MONSTER_HEIGHT = 80;
    private int MONSTER_WIDTH = 80;
    private int NUMBER_OF_MONSTERS = 9;

    private FreezeMonstersConfig() {
        int boardWidth = 1000;
        int boardHeight = 700;
        String playerImage = "/woody.png";

        setBORDER_HEIGHT(boardHeight);
        setBORDER_WIDTH(boardWidth);
        setPlayerImage(playerImage);

        // Carrega a imagem original para obter suas dimensões
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(playerImage));
        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        // Calcula a largura proporcional à altura desejada
        int targetHeight = 80;
        int targetWidth = (int) ((double) originalWidth / originalHeight * targetHeight);

        setPLAYER_WIDTH(targetWidth);  // Largura ajustada proporcionalmente
        setPLAYER_HEIGHT(targetHeight); // Altura fixa desejada

        // Posiciona o Woody no centro:
        setINIT_PLAYER_X((boardWidth - targetWidth) / 2);
        setINIT_PLAYER_Y((boardHeight - targetHeight) / 2);

        setGameDirection(new Quadimensional());
    }

    public static FreezeMonstersConfig getInstance() {
        if(instance == null) {
            instance = new FreezeMonstersConfig();
        }
        return instance;
    }

    public int getNUMBER_OF_MONSTERS(){
        return NUMBER_OF_MONSTERS;
    }

    public int getRAY_HEIGHT() {
        return RAY_HEIGHT;
    }

    public int getRAY_WIDTH() {
        return RAY_WIDTH;
    }

    public int getMONSTER_HEIGHT() {
        return MONSTER_HEIGHT;
    }

    public int getMONSTER_WIDTH() {
        return MONSTER_WIDTH;
    }
}
