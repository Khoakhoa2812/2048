package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Board.Tile;
import gameStates.Playing;

public class EntitiesManager {
    private Playing playing;

    public EntitiesManager(Playing playing) {
        this.playing = playing;
    }

    private static EntitiesManager instance;

    public static EntitiesManager createInstance(Playing playing) {
        if (instance == null) {
            instance = new EntitiesManager(playing);
            return instance;
        }
        return null;
    }

    private List<Entities> entitiesList = new ArrayList<>();
    private Random random = new Random();
    private boolean limitPerCreation = true;

    public boolean isLimitPerCreation() {
        return limitPerCreation;
    }

    public void setLimitPerCreation(boolean limitPerCreation) {
        this.limitPerCreation = limitPerCreation;
    }

    public List<Entities> getEntitiesList() {
        return entitiesList;
    }


    public void createEntities() {
        int num1 = random.nextInt(16);
        int type1;
        int randomAccess = random.nextInt(10);
        if(randomAccess == 2 || randomAccess == 8){
            type1 = 1;
        } else {
            type1 = 0;
        }
        CreateRandomType(type1, checkOccupied(num1));
    }

    public void createEntitiesPerMove() {
        if (!limitPerCreation && checkAllPreEntitiesMove()) {
            createEntities();
            limitPerCreation = true;
        }
    }

    public void moveEntities() {
        if(!playing.getUndo().isUndoUsed()){
            switch (playing.getCalculation().calculateDirection()) {
                case "NORTH":
                    moveUp();
                    playing.getTempoValue().assignTempDirection("NORTH");
                    break;
                case "SOUTH":
                    moveDown();
                    playing.getTempoValue().assignTempDirection("SOUTH");
                    break;
                case "WEST":
                    moveLeft();
                    playing.getTempoValue().assignTempDirection("WEST");
                    break;
                case "EAST":
                    moveRight();
                    playing.getTempoValue().assignTempDirection("EAST");
                    break;
            }
        }
    }

    public boolean checkTileOccupied(int num) {
        for (Entities entities : entitiesList) {
            if (playing.getBoard4x4().getTile()[num / 4][num % 4].isOccupied(entities) && entities.isStatus()) {
                return true;
            }
        }
        return false;
    }

    public void moveUp() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int col = 0; col < boardTiles[0].length; col++) {
            for (int row = 0; row < boardTiles.length; row++) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetRow = row - 1;
                        if (targetRow >= 0) {
                            if (!checkTileOccupied(targetRow * boardTiles.length + col)) {
                                entity.move("UP", boardTiles[targetRow][col]);
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() - 4) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        } else {
                            entity.setMoveCompleted(true);
                        }
                    }
                }
            }
        }
    }

    public void moveDown() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int col = 0; col < boardTiles[0].length; col++) {
            for (int row = boardTiles.length - 1; row >= 0; row--) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetRow = row + 1;
                        if (targetRow < boardTiles.length) {
                            if (!checkTileOccupied(targetRow * boardTiles.length + col)) {
                                entity.move("DOWN", boardTiles[targetRow][col]);
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() + 4) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        } else {
                            entity.setMoveCompleted(true);
                        }
                    }
                }
            }
        }
    }

    public void moveLeft() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int row = 0; row < boardTiles.length; row++) {
            for (int col = 0; col < boardTiles[row].length; col++) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetColumn = col - 1;
                        if (targetColumn >= 0) {
                            if (!checkTileOccupied(row * boardTiles.length + targetColumn)) {
                                entity.move("LEFT", boardTiles[row][targetColumn]);
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() - 1) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        } else {
                            entity.setMoveCompleted(true);
                        }
                    }
                }
            }
        }
    }

    public void moveRight() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int row = 0; row < boardTiles.length; row++) {
            for (int col = boardTiles[row].length - 1; col >= 0; col--) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetColumn = col + 1;
                        if (targetColumn < boardTiles[row].length) {
                            if (!checkTileOccupied(row * boardTiles.length + targetColumn)) {
                                entity.move("RIGHT", boardTiles[row][targetColumn]);
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() + 1) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        } else {
                            entity.setMoveCompleted(true);
                        }
                    }
                }
            }
        }
    }

    public boolean checkAllPreEntitiesMove() {
        for (Entities entities : entitiesList) {
            if (!entities.isMoveCompleted() && entities.isStatus() && !entities.isEntitiesNewlyCreated()) {
                return false;
            }
        }
        return true;
    }

    public void releaseNewEntities() {
        if (checkAllPreEntitiesMove()) {
            for (Entities entities : entitiesList) {
                entities.setMoveCompleted(false);
            }
        }
    }

    public int checkOccupied(int num) {
        if(playing.getBoard4x4().isBoardFull()){
            return -1;
        }
        for (Entities entities : entitiesList) {
            if (playing.getBoard4x4().getTile()[num / 4][num % 4].isOccupied(entities) && entities.isStatus()) {
                num = random.nextInt(16);
                num = checkOccupied(num);
                break;
            }
        }
        return num;
    }

    public int countEntitiesAlived(){
        int count = 0;
        for(Entities entities:entitiesList){
            if(entities.isStatus()){
                count++;
            }
        }
        return count;
    }

    public void CreateRandomType(int type, int num) {
        if(num != -1){
            switch (type) {
                case 0:
                    for (Entities entities : entitiesList) {
                        if (entities.getValue() == 2 && entities.getNum() == 0 && !entities.isStatus()) {
                            entities.setStatus(true);
                            entities.setPosition(playing.getBoard4x4().getTile()[num / 4][num % 4]);
                            entities.setTileNum(num);
                            entities.setNum(1);
                            entities.setEntitiesNewlyCreated(true);
                            break;
                        }
                    }
                    break;
                case 1:
                    for (Entities entities : entitiesList) {
                        if (entities.getValue() == 4 && entities.getNum() == 0 && !entities.isStatus()) {
                            entities.setStatus(true);
                            entities.setPosition(playing.getBoard4x4().getTile()[num / 4][num % 4]);
                            entities.setTileNum(num);
                            entities.setNum(1);
                            entities.setEntitiesNewlyCreated(true);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public void ageTile() {
        for (Entities entities : entitiesList) {
            entities.setEntitiesNewlyCreated(false);
            entities.setEntitiesNewlyCombined(false);
            entities.setEntitiesNewlyDeleted(false);
        }
    }

    public void EntitiesRender(Graphics g) {
        for (Entities entities : entitiesList) {
            entities.EntityRender(g);
        }
    }

    public void update() {
        moveEntities();
        createEntitiesPerMove();
        releaseNewEntities();
    }
}
