package Win_Lose;

import gameStates.Playing;
import gameStates.gameScenes;

public class Lose {
    private Playing playing;
    public Lose(Playing playing){
        this.playing = playing;
    }
    private static Lose instance;
    public static Lose createInstance(Playing playing){
        if(instance == null){
            instance = new Lose(playing);
            return instance;
        }
        return null;
    }

    public void gameLose(){
        if(playing.getTileCombination().isAnyCombinationAvailable()){
            gameScenes.setGameScenes(gameScenes.LOSE);
        }
    }


    public void update(){
        gameLose();
    }
}
