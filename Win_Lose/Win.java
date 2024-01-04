package Win_Lose;

import entities.Entities;
import entities.Tile2048;
import gameStates.Playing;
import gameStates.gameScenes;

import java.awt.*;

public class Win {
    private Playing playing;
    public Win(Playing playing){
        this.playing = playing;
//        testWin();
    }
    private static Win instance;
    public static Win createInstance(Playing playing){
        if(instance == null){
            instance = new Win(playing);
            return instance;
        }
        return null;
    }
    public void WinMatch(){
        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
            if(entities.getValue() == 2048 && entities.isStatus()){
                gameScenes.setGameScenes(gameScenes.WIN);
            }
        }
    }
    public void update(){
        WinMatch();
    }
}
