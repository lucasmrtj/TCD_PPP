package spaceinvaders;

public class SpaceInvadersConfig extends spriteframework.GameConfig{
    private static SpaceInvadersConfig instance;

    private int GROUND = 290;
    private int BOMB_HEIGHT = 5;

    private int ALIEN_HEIGHT = 12;
    private int ALIEN_WIDTH = 12;
    private int ALIEN_INIT_X = 150;
    private int ALIEN_INIT_Y = 5;

    private int GO_DOWN = 15;
    private int NUMBER_OF_ALIENS_ROW = 4;
    private int NUMBER_OF_ALIENS_COL = 6;
    private int NUMBER_OF_ALIENS_TO_DESTROY = NUMBER_OF_ALIENS_ROW * NUMBER_OF_ALIENS_COL;
    private int CHANCE = 5;
    private int PLAYER_WIDTH = 15;
    private int PLAYER_HEIGHT = 10;

    private String PLAYER_IMAGE = "/player.png";

    private SpaceInvadersConfig(){
        setBORDER_HEIGHT(500);
        setBORDER_WIDTH(500);
    }

    public static SpaceInvadersConfig getInstance() {
        if(instance == null) {
            instance = new SpaceInvadersConfig();
        }
        return instance;
    }

    public String getPLAYER_IMAGE() {
        return PLAYER_IMAGE;
    }

    public int getPLAYER_HEIGHT() {
        return PLAYER_HEIGHT;
    }

    public int getPLAYER_WIDTH() {
        return PLAYER_WIDTH;
    }

    public int getCHANCE() {
        return CHANCE;
    }

    public int getNUMBER_OF_ALIENS_TO_DESTROY() {
        return NUMBER_OF_ALIENS_TO_DESTROY;
    }

    public int getNUMBER_OF_ALIENS_COL() {
        return NUMBER_OF_ALIENS_COL;
    }

    public int getNUMBER_OF_ALIENS_ROW() {
        return NUMBER_OF_ALIENS_ROW;
    }

    public int getGO_DOWN() {
        return GO_DOWN;
    }

    public int getALIEN_INIT_Y() {
        return ALIEN_INIT_Y;
    }

    public int getALIEN_INIT_X() {
        return ALIEN_INIT_X;
    }

    public int getALIEN_WIDTH() {
        return ALIEN_WIDTH;
    }

    public int getALIEN_HEIGHT() {
        return ALIEN_HEIGHT;
    }

    public int getBOMB_HEIGHT() {
        return BOMB_HEIGHT;
    }

    public int getGROUND() {
        return GROUND;
    }
}
