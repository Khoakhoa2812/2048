package entities;

import gameStates.Playing;

import java.util.ArrayList;
import java.util.List;

public class EntitiesStorage {
    private static EntitiesStorage instance;

    public static EntitiesStorage createInstance(Playing playing){
        if(instance == null){
            instance = new EntitiesStorage(playing);
            return instance;
        } else {
            return null;
        }
    }
    private Playing playing;
    public EntitiesStorage(Playing playing){
        this.playing = playing;
        tile4Store();
        tile8Store();
        rejectStatus();
    }
    public void tile4Store(){
        for(int i = 0;i<50;i++){
            playing.getEntitiesManager().getEntitiesList().add(new Tile4(playing.getBoard4x4().getTile()[0]));
        }
    }
    public void tile8Store(){
        for(int i = 0;i<50;i++){
            playing.getEntitiesManager().getEntitiesList().add(new Tile8(playing.getBoard4x4().getTile()[0]));
        }
    }
    public void rejectStatus(){
        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
            entities.setStatus(false);
        }
    }
}
