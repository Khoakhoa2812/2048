package Board;

import entities.EntitesStateManager;
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
    private boolean isAssigned = false;

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    private int top = -1;
    private int scoreTop = -1;
    private List<EntitesStateManager> entitesStateManagerList = new ArrayList<>();
    public void assignStateToStack(){
        top++;
        scoreTop++;
        EntitesStateManager entitesStateManager = new EntitesStateManager(playing);
        entitesStateManager.assignEntitiesState();
        entitesStateManagerList.add(top,entitesStateManager);
        playing.getScore().getScoreStack().add(scoreTop,playing.getScore().getScore());
    }
    public EntitesStateManager pop(){
        EntitesStateManager entitesStateManager = entitesStateManagerList.get(top);
        top--;
        return entitesStateManager;
    }
    public int scorePop(){
        int score = playing.getScore().getScoreStack().get(scoreTop);
        scoreTop--;
        return score;
    }
}
