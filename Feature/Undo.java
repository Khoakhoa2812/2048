package Feature;

import Board.Tile;
import entities.Entities;
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

    public Undo(Playing playing){
        this.playing = playing;
        UndoButtonBound = new Rectangle(50,30,50,50);
    }
    private static Undo instance;
    public static Undo createInstance(Playing playing){
        if(instance == null){
            instance = new Undo(playing);
            return instance;
        }
        return null;
    }
    public void render(Graphics g){
        UndoButton = t.getImage(getClass().getResource("/resources/Undo.png"));
        UndoButtonUnavailable = t.getImage(getClass().getResource("/resources/undo_unavailable.png"));
        g.drawImage(UndoButton,50,30,50,50,null);
        undoBlockedRender(g);
    }
    public void storeCurrentEntities(){
        resetTile();
        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
            if(entities.isStatus()){
                for(Tile tile1:playing.getBoard4x4().getTile()){
                    if(tile1.isOccupied(entities)){
                        tile1.setValueHold(entities.getValue());
                    }
                }
            }
        }
    }
    public void resetTile(){
        for(Tile tile:playing.getBoard4x4().getTile()){
            tile.setValueHold(0);
        }
    }
    public void UndoMove(int x, int y){
        if(UndoButtonBound.contains(x,y)){
            for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                UndoStatus(entities);
            }
//            checkQuantity();
            for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                UndoPosition(entities);
            }
            isUndoUsed = true;
        }
//        checkQuantity();
    }
    public void UndoStatus(Entities entities){
        if(entities.isEntitiesNewlyCreated()){
            entities.setStatus(false);
            entities.setNum(0);
            entities.setEntitiesNewlyCreated(false);
        }
        if(entities.isEntitiesNewlyDeleted()){
            entities.setStatus(true);
            entities.setEntitiesNewlyDeleted(false);
            entities.setNum(0);
        }
        if(entities.isEntitiesNewlyCombined()){
            entities.setStatus(false);
            entities.setEntitiesNewlyCombined(false);
            entities.setNum(0);
        }
    }
    public void UndoPosition(Entities entities){
        if(entities.isStatus()){
            for(Tile tile:playing.getBoard4x4().getTile()){
                if(tile.getValueHold() == entities.getValue()){
                    entities.setPosition(tile);
                    entities.setTileNum(tile.getTileNum());
                    tile.setValueHold(0);
                    break;
                }
            }
        }
    }
    public void checkQuantity(){
        int entities = 0;
        for(Entities entities1:playing.getEntitiesManager().getEntitiesList()){
            if(entities1.isStatus()){
                entities++;
            }
        }
        System.out.println(entities);
    }
    public void undoBlockedRender(Graphics g){
        if(isUndoUsed){
            g.drawImage(UndoButtonUnavailable,50,30,50,50,null);
        }
    }
    public void update(){
//        checkQuantity();
    }
}
