package spriteframework;

import spriteframework.gamedirection.GameDirection;

// Essa classe é uma classe singleton
public class GameConfig {
    // Valores modificaveis para cada jogo
    private GameDirection gameDirection;
    private String playerImage;
    private int BORDER_WIDTH;
    private int BORDER_HEIGHT;
    private int INIT_PLAYER_X;
    private int INIT_PLAYER_Y;
    private int PLAYER_WIDTH ;
    private int PLAYER_HEIGHT;

    // Padrão para cada um dos jogos
    private int BORDER_RIGHT = 30;
    private int BORDER_LEFT = 5;
    private int DELAY = 17;

    protected void setINIT_PLAYER_X(int x) {
        INIT_PLAYER_X = x;
    }

    protected void setINIT_PLAYER_Y(int y) {
        INIT_PLAYER_Y = y;
    }

    protected void setBORDER_WIDTH(int BORDER_WIDTH) {
        this.BORDER_WIDTH = BORDER_WIDTH;
    }

    protected void setBORDER_HEIGHT(int BORDER_HEIGHT) {
        this.BORDER_HEIGHT = BORDER_HEIGHT;
    }

    protected void setGameDirection(GameDirection gameDirection) {
        this.gameDirection = gameDirection;
    }

    protected void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    protected void setPLAYER_WIDTH(int PLAYER_WIDTH) {
        this.PLAYER_WIDTH = PLAYER_WIDTH;
    }

    protected void setPLAYER_HEIGHT(int PLAYER_HEIGHT) {
        this.PLAYER_HEIGHT = PLAYER_HEIGHT;
    }

    public int getPLAYER_WIDTH() {
        return PLAYER_WIDTH;
    }

    public int getPLAYER_HEIGHT() {
        return PLAYER_HEIGHT;
    }

    public String getPLAYER_IMAGE() {
        return playerImage;
    }

    public GameDirection getGameDirection(){
        return gameDirection;
    }

    public int getDELAY() {
        return DELAY;
    }

    public int getINIT_PLAYER_Y() {
        return INIT_PLAYER_Y;
    }

    public int getINIT_PLAYER_X() {
        return INIT_PLAYER_X;
    }

    public int getBORDER_LEFT() {
        return BORDER_LEFT;
    }

    public int getBORDER_RIGHT() {
        return BORDER_RIGHT;
    }

    public int getBORDER_HEIGHT() {
        return BORDER_HEIGHT;
    }

    public int getBORDER_WIDTH() {
        return BORDER_WIDTH;
    }

    public int getDelay(){
        return DELAY;
    }
}
