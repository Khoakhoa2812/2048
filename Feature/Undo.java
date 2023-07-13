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
        g.drawImage(UndoButton,50,30,50,50,null);
    }
    public void storeCurrentEntities(){
        for(Tile tile:playing.getBoard4x4().getTile()){
            tile.setValueHold(0);
        }
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
    public void UndoMove(int x, int y){
        if(UndoButtonBound.contains(x,y)){
            for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                UndoStatus(entities);
            }
            for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                UndoPosition(entities);
            }
            isUndoUsed = true;
        }
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
                }
            }
        }
    }
}
