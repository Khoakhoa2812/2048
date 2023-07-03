package gameStates;

public enum gameScenes {
    MENU,
    PLAYING,
    WIN,
    LOSE,
    SETTING;
    public static gameScenes GameScenes = gameScenes.MENU;
    public static void setGameScenes(gameScenes scene) {
        GameScenes = scene;
    }
}
