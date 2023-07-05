package Calculation;

import entities.Entities;
import gameStates.Playing;

public class tileCombination {
    private static tileCombination instance;
    public static tileCombination createInstance(Playing playing){
        if(instance == null){
            instance = new tileCombination(playing);
            return instance;
        } else {
            return null;
        }
    }
    private Playing playing;
    private int[] availableValueCombination = {4,8};
    public tileCombination(Playing playing){
        this.playing = playing;
    }
    public void combination(Entities entity1, Entities entity2){
        for(int i = 0;i<availableValueCombination.length;i++){
            if(playing.getCalculation().doCalculation(entity1,entity2) == availableValueCombination[i] && entity1.isStatus() && entity2.isStatus()){
                entity1.setStatus(false);
                entity2.setStatus(false);
                entity1.setNum(1);
                entity2.setNum(1);
                switch (i){
                    case 0:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 4 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity1.getTileNum()]);
                                entities.setTileNum(entity1.getTileNum());
                                entities.setNum(1);
                                break;
                            }
                        }
                        break;
                    case 1:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 8 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity1.getTileNum()]);
                                entities.setTileNum(entity1.getTileNum());
                                entities.setNum(1);
                                break;
                            }
                        }
                        break;
                }
                break;
            }
        }
    }

}