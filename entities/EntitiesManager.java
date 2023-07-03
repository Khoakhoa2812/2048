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
        for(Entities entities:entitiesList){
            System.out.println("x: "+entities.getBound().getWidth());
            System.out.println("y: "+entities.getBound().getHeight());
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
}
