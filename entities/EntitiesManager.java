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
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int col = 0; col < boardTiles[0].length; col++) {
            for (int row = 1; row < boardTiles.length; row++) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetRow = row - 1;
                        if (targetRow >= 0) {
                            if (!checkTileOccupied(targetRow * boardTiles.length + col)) {
                                entity.move("UP", boardTiles[targetRow][col]);
                                System.out.println(entity.getTileNum());
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() - 4) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        }
                        entity.setMoveCompleted(true);
                    }
                }
            }
        }
    }

    public void moveDown() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int col = 0; col < boardTiles[0].length; col++) {
            for (int row = boardTiles.length - 2; row >= 0; row--) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetRow = row + 1;
                        if (targetRow < boardTiles.length) {
                            if (!checkTileOccupied(targetRow * boardTiles.length + col)) {
                                entity.move("DOWN", boardTiles[targetRow][col]);
                                System.out.println(entity.getTileNum());
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() + 4) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        }
                        entity.setMoveCompleted(true);
                    }
                }
            }
        }
    }

    public void moveLeft() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int row = 0; row < boardTiles.length; row++) {
            for (int col = 1; col < boardTiles[row].length; col++) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetColumn = col - 1;
                        if (targetColumn >= 0) {
                            if (!checkTileOccupied(row * boardTiles.length + targetColumn)) {
                                entity.move("LEFT", boardTiles[row][targetColumn]);
                                System.out.println(entity.getTileNum());
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() - 1) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        }
                        entity.setMoveCompleted(true);
                    }
                }
            }
        }
    }

    public void moveRight() {
        Tile[][] boardTiles = playing.getBoard4x4().getTile();
        for (int row = 0; row < boardTiles.length; row++) {
            for (int col = boardTiles[row].length - 2; col >= 0; col--) {
                int entityTileNum = row * boardTiles.length + col;
                for (Entities entity : entitiesList) {
                    if (entity.getTileNum() == entityTileNum && entity.isStatus() && !entity.isEntitiesNewlyCreated()) {
                        int targetColumn = col + 1;
                        if (targetColumn < boardTiles[row].length) {
                            if (!checkTileOccupied(row * boardTiles.length + targetColumn)) {
                                entity.move("RIGHT", boardTiles[row][targetColumn]);
                                System.out.println(entity.getTileNum());
                            } else {
                                for (Entities entities1 : entitiesList) {
                                    if (entities1.getTileNum() == entity.getTileNum() + 1) {
                                        playing.getTileCombination().combination(entity, entities1);
                                    }
                                }
                                entity.setMoveCompleted(true);
                            }
                        }
                        entity.setMoveCompleted(true);
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
