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
    private boolean isStackEmpty = false;

    public boolean isStackEmpty() {
        return isStackEmpty;
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
    public void checkStackEmpty(){
        if(top == -1){
            isStackEmpty = true;
        } else {
            isStackEmpty = false;
        }
    }
    public int scorePop(){
        int score = playing.getScore().getScoreStack().get(scoreTop);
        scoreTop--;
        return score;
    }
    public void test(){
        System.out.println(top);
    }
    public void update(){
        checkStackEmpty();
//        test();
    }
}
