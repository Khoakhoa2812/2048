package Calculation;

import entities.Entities;
import gameStates.Playing;

public class calculation {
    private Playing playing;
    public calculation(Playing playing){
        this.playing = playing;
    }
    private static calculation instance;
    public static calculation createInstance(Playing playing){
        if(instance == null){
            instance = new calculation(playing);
            return instance;
        } else {
            return null;
        }
    }
    public String calculateDirection(){
        if(playing.isDragCompleted()){
            double gapX = playing.getFinalX() - playing.getInitX();
            double gapY = playing.getFinalY() - playing.getInitY();
            if(gapX < 0 && gapY >0){
                if(gapX*-1 > gapY || gapX*-1 == gapY){
                    return "WEST";
                } else if(gapX*-1 <gapY){
                    return "SOUTH";
                }
            } else if (gapX < 0 && gapY < 0) {
                if(gapX*-1 > gapY*-1 || gapX*-1 == gapY*-1){
                    return "WEST";
                } else {
                    return "NORTH";
                }
            } else if (gapX > 0 && gapY < 0) {
                if(gapX > gapY*-1 || gapX == gapY*-1){
                    return "EAST";
                } else {
                    return "NORTH";
                }
            } else if (gapX >0 && gapY > 0) {
                if(gapX > gapY || gapX == gapY){
                    return "EAST";
                } else {
                    return "SOUTH";
                }
            }
        }
        return "";
    }
    public int doCalculation(Entities entity1, Entities entity2){
        return entity1.getValue()+ entity2.getValue();
    }
}
