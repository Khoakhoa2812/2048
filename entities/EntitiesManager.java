package entities;

import gameStates.Playing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntitiesManager {
    private Playing playing;
    public EntitiesManager(Playing playing){
        this.playing = playing;
    }
    public void initTile(){
        entitiesList.add(new Tile2(this.playing.getBoard4x4().getTile()[3]));
        entitiesList.add(new Tile4(this.playing.getBoard4x4().getTile()[7]));
    }
    private static EntitiesManager instance;
    public static EntitiesManager createInstance(Playing playing){
        if(instance == null){
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

    public void createEntities(){
        int num1 = random.nextInt(16);
        int num2 = random.nextInt(16);
        if(isSameNum(num1,num2)){
            num2 = reRandomized(num1, num2);
        }
        int type1 = random.nextInt(2);
        int type2 = random.nextInt(2);
        try {
            entitiesList.add(CreateRandomType(type1,checkOccupied(num1)));
            entitiesList.add(CreateRandomType(type2,checkOccupied(num2)));
        } catch (StackOverflowError e){
            System.out.println("You Lose");
        }
    }
    public void createEntitiesPerMove(){
        if(!limitPerCreation && checkAllPreEntitiesMove()){
            createEntities();
            limitPerCreation = true;
        }
    }
    public void moveEntities(){
        switch (playing.getCalculation().calculateDirection()){
            case "NORTH":
                moveUp();
                break;
            case "SOUTH":
                moveDown();
                break;
            case "WEST":
                moveLeft();
                break;
            case "EAST":
                moveRight();
                break;
        }
    }
    public boolean checkTileOccupied(int num){
        for(Entities entities:entitiesList){
            if(playing.getBoard4x4().getTile()[num].isOccupied(entities) && entities.isStatus()){
                return true;
            }
        }
        return false;
    }
    public int checkOccupiedNum(int num){
        for(Entities entities:entitiesList){
            if(playing.getBoard4x4().getTile()[num].isOccupied(entities) && entities.isStatus()){
                return num;
            }
        }
        return -1;
    }
    public void OccupiedTile(){
        for(int i = 0;i<16;i++){
            System.out.println("Current tile: "+i+" : "+checkOccupiedNum(i));
        }
    }
    public void moveUp(){
        for (Entities entities: entitiesList){
            if(entities.isStatus() && !entities.isEntitiesNewlyCreated()){
                if (entities.getTileNum()-4 >=0){
                    if(!checkTileOccupied(entities.getTileNum()-4)){
                        entities.updatePosition(playing.getBoard4x4().getTile()[entities.getTileNum()-4]);
                        entities.setTileNum(entities.getTileNum()-4);
                    } else {
                        for(Entities entities1:entitiesList){
                            if(entities1.getTileNum() == entities.getTileNum()-4){
                                playing.getTileCombination().combination(entities,entities1);
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
    public void moveDown(){
        for (Entities entities: entitiesList){
            if(entities.isStatus() && !entities.isEntitiesNewlyCreated()){
                if (entities.getTileNum()+4 <16){
                    if(!checkTileOccupied(entities.getTileNum()+4)){
                        entities.updatePosition(playing.getBoard4x4().getTile()[entities.getTileNum()+4]);
                        entities.setTileNum(entities.getTileNum()+4);
                    } else {
                        for(Entities entities1:entitiesList){
                            if(entities1.getTileNum() == entities.getTileNum()+4){
                                playing.getTileCombination().combination(entities,entities1);
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
    public void moveLeft(){
        for (Entities entities: entitiesList){
            if(entities.isStatus() && !entities.isEntitiesNewlyCreated()){
                if((entities.getTileNum()-1 >=0 && entities.getTileNum() <4) ||
                        (entities.getTileNum() -1 >= 4 && entities.getTileNum() < 8) ||
                        (entities.getTileNum() -1 >= 8 && entities.getTileNum() < 12) ||
                        (entities.getTileNum() -1 >= 12 && entities.getTileNum() < 16)){
                    if(!checkTileOccupied(entities.getTileNum()-1)){
                        entities.updatePosition(playing.getBoard4x4().getTile()[entities.getTileNum()-1]);
                        entities.setTileNum(entities.getTileNum()-1);
                    } else {
                        for(Entities entities1:entitiesList){
                            if(entities1.getTileNum() == entities.getTileNum()-1){
                                playing.getTileCombination().combination(entities,entities1);
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
    public void moveRight(){
        for (Entities entities: entitiesList){
            if(entities.isStatus() && !entities.isEntitiesNewlyCreated()){
                if((entities.getTileNum()+1 <=3 && entities.getTileNum() >=0) ||
                        (entities.getTileNum() +1 <= 7 && entities.getTileNum() > 3) ||
                        (entities.getTileNum() +1 <= 11 && entities.getTileNum() > 7) ||
                        (entities.getTileNum() +1 <= 15 && entities.getTileNum() > 11)){
                    if(!checkTileOccupied(entities.getTileNum()+1)){
                        entities.updatePosition(playing.getBoard4x4().getTile()[entities.getTileNum()+1]);
                        entities.setTileNum(entities.getTileNum()+1);
                    } else {
                        for(Entities entities1:entitiesList) {
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
    public boolean checkAllPreEntitiesMove(){
        for(Entities entities:entitiesList){
            if(!entities.isMoveCompleted() && entities.isStatus() && !entities.isEntitiesNewlyCreated()){
                return false;
            }
        }
        System.out.println("1");
        return true;
    }
    public void releaseMove(){
        if(checkAllPreEntitiesMove()){
            for(Entities entities:entitiesList){
                entities.setMoveCompleted(false);
            }
        }
    }
    public int checkOccupied(int num){
        for(Entities entities:entitiesList){
            if(playing.getBoard4x4().getTile()[num].isOccupied(entities) && entities.isStatus()){
                num = random.nextInt(16);
                checkOccupied(num);
            }
        }
        return num;
    }
    public Entities CreateRandomType(int type, int num){
        if(type == 0){
            Tile2 tile2 = new Tile2(playing.getBoard4x4().getTile()[num]);
            tile2.setEntitiesNewlyCreated(true);
            return tile2;
        } else if(type == 1){
            Tile4 tile4 = new Tile4(playing.getBoard4x4().getTile()[num]);
            tile4.setEntitiesNewlyCreated(true);
            return tile4;
        }
        return null;
    }
    public void ageTile(){
        for(Entities entities: entitiesList){
            entities.setEntitiesNewlyCreated(false);
        }
    }
    public void testEntities(){
        int i = 0;
        for(Entities entities:entitiesList){
            if(entities.isStatus() && entities.isMoveCompleted()){
                System.out.println("entity "+i+" position: "+entities.getTileNum());
                i++;
            }
        }
    }
    private int reRandomized(int num1, int num2){
        if(isSameNum(num1, num2)){
            num2 = random.nextInt(16);
            reRandomized(num1,num2);
        }
        return num2;
    }
    private boolean isSameNum(int num1, int num2){
        if(num1 == num2){
            return true;
        }
        return false;
    }
    public void EntitiesRender(Graphics g){
        for(Entities entities:entitiesList){
            entities.EntityRender(g);
        }
    }
    public void update(){
        moveEntities();
        createEntitiesPerMove();
//        testEntities();
//        OccupiedTile();
        for (Entities entities:entitiesList){
            entities.update();
        }
    }
}
