package spaceinvaders;

public interface Commons extends spriteframework.Commons{

    int BOMB_HEIGHT = 5;

    int ALIEN_HEIGHT = 12;
    int ALIEN_WIDTH = 12;
    int ALIEN_INIT_X = 150;
    int ALIEN_INIT_Y = 5;

    int GO_DOWN = 15;
    int NUMBER_OF_ALIENS_ROW = 4;
    int NUMBER_OF_ALIENS_COL = 6;
    int NUMBER_OF_ALIENS_TO_DESTROY = NUMBER_OF_ALIENS_ROW * NUMBER_OF_ALIENS_COL;
    int CHANCE = 5;
    int PLAYER_WIDTH = 15;
    int PLAYER_HEIGHT = 10;

    String PLAYER_IMAGE = "/player.png";
}
