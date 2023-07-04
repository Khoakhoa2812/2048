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
        if(playing.isDragCompleted()){
            createEntities();
            playing.setDragCompleted(false);
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
            if(playing.getBoard4x4().getTile()[num].isOccupied(entities)){
                return true;
            }
        }
        return false;
    }
    public void moveUp(){
        for (Entities entities: entitiesList){
            if(entities.getOldTileNum()-4 >=0){
                if(!checkTileOccupied(entities.getOldTileNum()-4)){
                    entities.updatePosition(playing.getBoard4x4().getTile()[entities.getOldTileNum()-4]);
                    entities.setNewTileNum(entities.getOldTileNum()-4);
                }
            }
        }
    }
    public void moveDown(){
        for (Entities entities: entitiesList){
            if(entities.getOldTileNum()+4 <16){
                if(!checkTileOccupied(entities.getOldTileNum()+4)){
                    entities.updatePosition(playing.getBoard4x4().getTile()[entities.getOldTileNum()+4]);
                    entities.setNewTileNum(entities.getOldTileNum()+4);
                }
            }
        }
    }
    public void moveLeft(){
        for (Entities entities: entitiesList){
            if((entities.getOldTileNum()-1 >=0 && entities.getOldTileNum() <4) ||
                    (entities.getOldTileNum() -1 >= 4 && entities.getOldTileNum() < 8) ||
                    (entities.getOldTileNum() -1 >= 8 && entities.getOldTileNum() < 12) ||
                    (entities.getOldTileNum() -1 >= 12 && entities.getOldTileNum() < 16)){
                if(!checkTileOccupied(entities.getOldTileNum()-1)){
                    entities.updatePosition(playing.getBoard4x4().getTile()[entities.getOldTileNum()-1]);
                    entities.setNewTileNum(entities.getOldTileNum()-1);
                }
            }
        }
    }
    public void moveRight(){
        for (Entities entities: entitiesList){
            if((entities.getOldTileNum()+1 <=3 && entities.getOldTileNum() >=0) ||
                    (entities.getOldTileNum() +1 <= 7 && entities.getOldTileNum() > 3) ||
                    (entities.getOldTileNum() +1 <= 11 && entities.getOldTileNum() > 7) ||
                    (entities.getOldTileNum() +1 <= 15 && entities.getOldTileNum() > 11)){
                if(!checkTileOccupied(entities.getOldTileNum()+1)){
                    entities.updatePosition(playing.getBoard4x4().getTile()[entities.getOldTileNum()+1]);
                    entities.setNewTileNum(entities.getOldTileNum()+1);
                }
            }
        }
    }
    public int checkOccupied(int num){
        for(Entities entities:entitiesList){
            if(playing.getBoard4x4().getTile()[num].isOccupied(entities)){
                num = random.nextInt(16);
                checkOccupied(num);
            }
        }
        return num;
    }
    public Entities CreateRandomType(int type, int num){
        if(type == 0){
            return new Tile2(playing.getBoard4x4().getTile()[num]);
        } else if(type == 1){
            return new Tile4(playing.getBoard4x4().getTile()[num]);
        }
        return null;
    }
    public void testEntities(){
        int i = 0;
        for(Entities entities:entitiesList){
            System.out.println("entity "+i+" position: "+entities.getOldTileNum());
            i++;
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
        createEntitiesPerMove();
        moveEntities();
        testEntities();
        for (Entities entities:entitiesList){
            entities.update();
        }
    }
}
