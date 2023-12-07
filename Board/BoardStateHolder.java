package Board;

import entities.Entities;
import gameStates.Playing;

import java.util.ArrayList;
import java.util.List;

public class BoardStateHolder {
    private static BoardStateHolder instance;
    private Playing playing;
    public BoardStateHolder(Playing playing){
        this.playing = playing;
    }
    public static BoardStateHolder createInstance(Playing playing){
        if(instance == null){
            instance = new BoardStateHolder(playing);
            return instance;
        }
        return null;
    }
  
    public void storeState(){
        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
            if(entities.isStatus()){

            }
        }
    }
    public void storeEntitiesActive(Entities entities){

    }
}
