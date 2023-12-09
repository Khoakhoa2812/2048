package Feature;

import Board.Tile;
import entities.EntitesStateManager;
import entities.Entities;
import entities.EntitiesStorage;
import gameStates.Playing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Undo {
    private Playing playing;
    private Image UndoButton;
    private Image UndoButtonUnavailable;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Rectangle UndoButtonBound;

    private boolean isUndoUsed = false;

    public boolean isUndoUsed() {
        return isUndoUsed;
    }

    public void setUndoUsed(boolean undoUsed) {
        isUndoUsed = undoUsed;
    }

    public Undo(Playing playing) {
        this.playing = playing;
        UndoButtonBound = new Rectangle(50, 30, 50, 50);
    }

    private static Undo instance;

    public static Undo createInstance(Playing playing) {
        if (instance == null) {
            instance = new Undo(playing);
            return instance;
        }
        return null;
    }

    public void render(Graphics g) {
        UndoButton = t.getImage(getClass().getResource("/resources/Undo.png"));
        UndoButtonUnavailable = t.getImage(getClass().getResource("/resources/undo_unavailable.png"));
        g.drawImage(UndoButton, 50, 30, 50, 50, null);
        drawBlockedUndoButton(g);
    }
    public void drawBlockedUndoButton(Graphics g){
        if(playing.getBoardStateHolder().isStackEmpty()){
            g.drawImage(UndoButtonUnavailable,50,30,50,50,null);
        }
    }

    public void UndoMove(int x, int y) {
        if(!playing.getBoardStateHolder().isStackEmpty()){
            if(UndoButtonBound.contains(x,y)){
                EntitesStateManager entitesStateManager = playing.getBoardStateHolder().pop();
                int count = 0;
                for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                    if(entities.getNo() == count){
                        entities.setNum(entitesStateManager.getNumList().get(count));
                        entities.setMoveCompleted(entitesStateManager.getIsMoveCompletedList().get(count));
                        entities.setEntitiesNewlyCombined(entitesStateManager.getIsNewlyCombinedList().get(count));
                        entities.setEntitiesNewlyDeleted(entitesStateManager.getIsNewlyDeletedList().get(count));
                        entities.setEntitiesNewlyCreated(entitesStateManager.getIsNewlyCreatedList().get(count));
                        entities.setStatus(entitesStateManager.getStatusList().get(count));
                        entities.setTileNum(entitesStateManager.getTileNumList().get(count));
                        entities.setValue(entitesStateManager.getValueList().get(count));
                        for(int i = 0;i<playing.getBoard4x4().getTile().length;i++){
                            for(int j = 0;j<playing.getBoard4x4().getTile()[i].length;j++){
                                if(playing.getBoard4x4().getTile()[i][j].getTileNum() == entitesStateManager.getTileNumList().get(count)){
                                    entities.setPosition(playing.getBoard4x4().getTile()[i][j]);
                                }
                            }
                        }
                        if(entities.isStatus()){
                            System.out.println(entities.getTileNum());
                        }
                    }
                    count++;
                }
                playing.getScore().setCurrentScore(playing.getBoardStateHolder().scorePop());
                isUndoUsed = true;
            }
        }
    }
}
