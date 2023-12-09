package entities;

import gameStates.Playing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntitesStateManager {
    private static EntitesStateManager instance;
    private Playing playing;
    public EntitesStateManager(Playing playing){
        this.playing = playing;
    }
    public static EntitesStateManager createInstance(Playing playing){
        if(instance == null){
            instance = new EntitesStateManager(playing);
            return instance;
        }
        return null;
    }
    private List<Integer> NumList = new ArrayList<>();
    private List<Integer> NoList = new ArrayList<>();

    public List<Integer> getNoList() {
        return NoList;
    }

    public List<Integer> getNumList() {
        return NumList;
    }

    public List<Integer> getTileNumList() {
        return TileNumList;
    }

    public List<Integer> getValueList() {
        return valueList;
    }

    public List<Rectangle> getBoundList() {
        return boundList;
    }

    public List<Boolean> getStatusList() {
        return StatusList;
    }

    public List<Boolean> getIsNewlyCreatedList() {
        return isNewlyCreatedList;
    }

    public List<Boolean> getIsNewlyCombinedList() {
        return isNewlyCombinedList;
    }

    public List<Boolean> getIsNewlyDeletedList() {
        return isNewlyDeletedList;
    }

    public List<Boolean> getIsMoveCompletedList() {
        return isMoveCompletedList;
    }

    private List<Integer> TileNumList = new ArrayList<>();
    private List<Integer> valueList = new ArrayList<>();
    private List<Rectangle> boundList = new ArrayList<>();
    private List<Boolean> StatusList = new ArrayList<>();
    private List<Boolean> isNewlyCreatedList = new ArrayList<>();
    private List<Boolean> isNewlyCombinedList = new ArrayList<>();
    private List<Boolean> isNewlyDeletedList = new ArrayList<>();
    private List<Boolean> isMoveCompletedList = new ArrayList<>();
    private int count = 0;
    public void assignEntitiesState(){
        for(Entities entities: playing.getEntitiesManager().getEntitiesList()){
            NumList.add(count,entities.getNum());
            TileNumList.add(count,entities.getTileNum());
            valueList.add(count,entities.getValue());
            boundList.add(count,entities.getBound());
            StatusList.add(count,entities.isStatus());
            isNewlyCreatedList.add(count,entities.isEntitiesNewlyCreated());
            isNewlyCombinedList.add(count,entities.isEntitiesNewlyCombined());
            isNewlyDeletedList.add(count,entities.isEntitiesNewlyDeleted());
            isMoveCompletedList.add(count,entities.isMoveCompleted());
            count++;
//            System.out.println(entities.getTileNum());
        }
    }
}
