package entities;

import Board.Tile;
import gameStates.Playing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitiesManager {
    private Playing playing;

    public EntitiesManager(Playing playing) {
        this.playing = playing;
    }

    public void initTile() {
        entitiesList.add(new Tile2(this.playing.getBoard4x4().getTile()[0][3]));
        entitiesList.add(new Tile4(this.playing.getBoard4x4().getTile()[1][3]));
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
        int type1 = random.nextInt(2);
        try {
            CreateRandomType(type1, checkOccupied(num1));
        } catch (StackOverflowError e) {

        }
    }

    public void createEntitiesPerMove() {
        if (!limitPerCreation && checkAllPreEntitiesMove()) {
            createEntities();
            limitPerCreation = true;
        }
    }

    public void moveEntities() {
        if (!playing.getUndo().isUndoUsed()) {
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

    public String checkOccupiedNum(int num) {
        for (Entities entities : entitiesList) {
            if (playing.getBoard4x4().getTile()[num / 4][num % 4].isOccupied(entities) && entities.isStatus()) {
                return "occupied";
            }
        }
        return "non-occupied";
    }

    public void OccupiedTile() {
        for (int i = 0; i < 16; i++) {
            System.out.println("Current tile: " + i + " : " + checkOccupiedNum(i));
        }
    }

    public void moveUp() {
        for (int i = 0; i < 16; i++) {
            for (Entities entities : entitiesList) {
                if (entities.isStatus() && entities.getTileNum() == i) {
                    if (!entities.isEntitiesNewlyCreated()) {
                        if (entities.getTileNum() - 4 >= 0) {
                            if (!checkTileOccupied(entities.getTileNum() - 4)) {
                                entities.move("UP", playing.getBoard4x4().getTile()[(entities.getTileNum() - 4)
                                        / 4][(entities.getTileNum() - 4) % 4]);
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entities.getTileNum() - 4) {
                                        playing.getTileCombination().combination(entities, entities1);
                                    }
                                }
                                entities.setMoveCompleted(true);
                            }
                        } else {
                            entities.setMoveCompleted(true);
                        }
                    }
                }
            }
        }
    }

    public void moveDown() {
        // for(int i = entitiesList.size()-1; i >= 0;i--){
        // if(entitiesList.get(i).isStatus() &&
        // !entitiesList.get(i).isEntitiesNewlyCreated()){
        // if(entitiesList.get(i).getTileNum() + 4 < 16){
        // if(!checkTileOccupied(entitiesList.get(i).getTileNum()+4)){
        // entitiesList.get(i).updatePosition(playing.getBoard4x4().getTile()[entitiesList.get(i).getTileNum()+4]);
        // entitiesList.get(i).setTileNum(entitiesList.get(i).getTileNum()+4);
        // } else {
        // for(Entities entities: entitiesList){
        // if(entitiesList.get(i).getTileNum()+4 == entities.getTileNum()){
        // playing.getTileCombination().combination(entitiesList.get(i),entities);
        // }
        // }
        // entitiesList.get(i).setMoveCompleted(true);
        // }
        // } else {
        // entitiesList.get(i).setMoveCompleted(true);
        // }
        // }
        // }
        for (int i = 15; i >= 0; i--) {
            for (Entities entities : entitiesList) {
                if (entities.isStatus()) {
                    if (entities.getTileNum() == i) {
                        if (!entities.isEntitiesNewlyCreated()) {
                            if (entities.getTileNum() + 4 < 16) {
                                if (!checkTileOccupied(entities.getTileNum() + 4)) {
                                    entities.move("DOWN", playing.getBoard4x4().getTile()[(entities.getTileNum() + 4)
                                            / 4][(entities.getTileNum() + 4) % 4]);
                                } else {
                                    for (Entities entities1 : entitiesList) {
                                        if (entities1.getTileNum() == entities.getTileNum() + 4) {
                                            playing.getTileCombination().combination(entities, entities1);
                                        }
                                    }
                                    entities.setMoveCompleted(true);
                                }
                            } else {
                                entities.setMoveCompleted(true);
                            }
                        }
                    }

                }
            }
        }
    }

    public void moveLeft() {
        for (int i = 0; i < 16; i++) {
            for (Entities entities : entitiesList) {
                if (entities.isStatus()) {
                    if (entities.getTileNum() == i) {
                        if (!entities.isEntitiesNewlyCreated()) {
                            if ((entities.getTileNum() - 1 >= 0 && entities.getTileNum() < 4) ||
                                    (entities.getTileNum() - 1 >= 4 && entities.getTileNum() < 8) ||
                                    (entities.getTileNum() - 1 >= 8 && entities.getTileNum() < 12) ||
                                    (entities.getTileNum() - 1 >= 12 && entities.getTileNum() < 16)) {
                                if (!checkTileOccupied(entities.getTileNum() - 1)) {
                                    entities.move("LEFT", playing.getBoard4x4().getTile()[(entities.getTileNum() - 1)
                                            / 4][(entities.getTileNum() - 1) % 4]);
                                } else {
                                    for (Entities entities1 : entitiesList) {
                                        if (entities1.getTileNum() == entities.getTileNum() - 1) {
                                            playing.getTileCombination().combination(entities, entities1);
                                        }
                                    }
                                    entities.setMoveCompleted(true);
                                }
                            } else {
                                entities.setMoveCompleted(true);
                            }
                        }
                    }
                }
            }
        }
    }

    public void moveRight() {
        // for(int i = entitiesList.size()-1; i >= 0;i--){
        // if(entitiesList.get(i).isStatus() &&
        // !entitiesList.get(i).isEntitiesNewlyCreated()){
        // if((entitiesList.get(i).getTileNum()+1 <=3 &&
        // entitiesList.get(i).getTileNum() >=0) ||
        // (entitiesList.get(i).getTileNum() +1 <= 7 && entitiesList.get(i).getTileNum()
        // > 3) ||
        // (entitiesList.get(i).getTileNum() +1 <= 11 &&
        // entitiesList.get(i).getTileNum() > 7) ||
        // (entitiesList.get(i).getTileNum() +1 <= 15 &&
        // entitiesList.get(i).getTileNum() > 11)){
        // if(!checkTileOccupied(entitiesList.get(i).getTileNum()+1)){
        // entitiesList.get(i).updatePosition(playing.getBoard4x4().getTile()[entitiesList.get(i).getTileNum()+1]);
        // entitiesList.get(i).setTileNum(entitiesList.get(i).getTileNum()+1);
        // } else {
        // for(Entities entities: entitiesList){
        // if(entitiesList.get(i).getTileNum()+1 == entities.getTileNum()){
        // playing.getTileCombination().combination(entitiesList.get(i),entities);
        // }
        // }
        // entitiesList.get(i).setMoveCompleted(true);
        // }
        // } else {
        // entitiesList.get(i).setMoveCompleted(true);
        // }
        // }
        // }
        for (int i = 15; i >= 0; i--) {
            for (Entities entities : entitiesList) {
                if (entities.isStatus()) {
                    if (entities.getTileNum() == i) {
                        if (!entities.isEntitiesNewlyCreated()) {
                            if ((entities.getTileNum() + 1 <= 3 && entities.getTileNum() >= 0) ||
                                    (entities.getTileNum() + 1 <= 7 && entities.getTileNum() > 3) ||
                                    (entities.getTileNum() + 1 <= 11 && entities.getTileNum() > 7) ||
                                    (entities.getTileNum() + 1 <= 15 && entities.getTileNum() > 11)) {
                                if (!checkTileOccupied(entities.getTileNum() + 1)) {
                                    entities.move("RIGHT", playing.getBoard4x4().getTile()[(entities.getTileNum() + 1)
                                            / 4][(entities.getTileNum() + 1) % 4]);
                                } else {
                                    for (Entities entities1 : entitiesList) {
                                        if (entities1.getTileNum() == entities.getTileNum() + 1) {
                                            playing.getTileCombination().combination(entities, entities1);
                                        }
                                    }
                                    entities.setMoveCompleted(true);
                                }
                            } else {
                                entities.setMoveCompleted(true);
                            }
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

    private int timeCast = 0;

    public int checkOccupied(int num) {
        for (Entities entities : entitiesList) {
            if (playing.getBoard4x4().getTile()[num / 4][num % 4].isOccupied(entities) && entities.isStatus()) {
                num = random.nextInt(16);
                timeCast++;
                num = checkOccupied(num);
                break;
            }
        }
        timeCast = 0;
        return num;
    }

    public boolean checkStream() {
        int countAlive = 0;
        Tile[][] tiles = playing.getBoard4x4().getTile(); // Giả sử getTile() trả về mảng 2 chiều Tile[][]

        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                for (Entities entity : entitiesList) {
                    if (entity.isStatus() && tile.isOccupied(entity)) {
                        countAlive++;
                        break;
                    }
                }
            }
        }

        return countAlive == (tiles.length * tiles[0].length);
    }

    public void CreateRandomType(int type, int num) {
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

    public void ageTile() {
        for (Entities entities : entitiesList) {
            entities.setEntitiesNewlyCreated(false);
            entities.setEntitiesNewlyCombined(false);
            entities.setEntitiesNewlyDeleted(false);
        }
    }

    public void testEntities() {
        int i = 0;
        for (Entities entities : entitiesList) {
            if (entities.isStatus() && entities.isMoveCompleted() && !entities.isEntitiesNewlyCreated()) {
                System.out.println(
                        "entity " + i + " position: " + entities.getTileNum() + " value: " + entities.getValue());
                i++;
            } else if (entities.isStatus() && entities.isEntitiesNewlyCreated()) {
                System.out.println("entity " + i + " position: " + entities.getTileNum() + " value: "
                        + entities.getValue() + " is newly created");
                i++;
            }
        }
    }

    private int reRandomized(int num1, int num2) {
        if (isSameNum(num1, num2)) {
            num2 = random.nextInt(16);
            reRandomized(num1, num2);
        }
        return num2;
    }

    private boolean isSameNum(int num1, int num2) {
        if (num1 == num2) {
            return true;
        }
        return false;
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
        // testEntities();
        // OccupiedTile();
        for (Entities entities : entitiesList) {
            entities.update();
        }
    }
}
