package freezemonsters;

import spriteframework.GameConfig;

public class FreezeMonstersConfig extends GameConfig {
    private static FreezeMonstersConfig instance;
    private String PLAYER_IMAGE = "/woody.png";

    private FreezeMonstersConfig(){
        setBORDER_HEIGHT(700);
        setBORDER_WIDTH(1000);
    }

    public static FreezeMonstersConfig getInstance() {
        if(instance == null) {
            instance = new FreezeMonstersConfig();
        }
        return instance;
    }

    public String getPLAYER_IMAGE() {
        return PLAYER_IMAGE;
    }
}
