package spriteframework;

// Essa classe é uma classe singleton
public class GameConfig {

    private int BORDER_WIDTH;
    private int BORDER_HEIGHT;
    private int BORDER_RIGHT = 30;
    private int BORDER_LEFT = 5;
    private int INIT_PLAYER_X = 270;
    private int INIT_PLAYER_Y = 280;
    private int DELAY = 17;

    protected void setBORDER_WIDTH(int BORDER_WIDTH) {
        this.BORDER_WIDTH = BORDER_WIDTH;
    }

    protected void setBORDER_HEIGHT(int BORDER_HEIGHT) {
        this.BORDER_HEIGHT = BORDER_HEIGHT;
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
}
