package Calculation;

import entities.Entities;
import gameStates.Playing;

public class tileCombination {
    private static tileCombination instance;

    public static tileCombination createInstance(Playing playing) {
        if (instance == null) {
            instance = new tileCombination(playing);
            return instance;
        } else {
            return null;
        }
    }

    private Playing playing;
    private int[] availableValueCombination = { 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048 };

    public tileCombination(Playing playing) {
        this.playing = playing;
    }

    public void combination(Entities entity1, Entities entity2) {
        doCombination(entity1,entity2, availableValueCombination.length-1);
    }
    public int doCombination(Entities entities1, Entities entities2, int combinationIndex){
        if(!(playing.getCalculation().doCalculation(entities1,entities2) == availableValueCombination[combinationIndex]) && combinationIndex>0){
            return doCombination(entities1,entities2,combinationIndex-1);
        } else if(playing.getCalculation().doCalculation(entities1,entities2) == availableValueCombination[combinationIndex]){
            if(entities1.isStatus() && entities2.isStatus() && !entities1.isEntitiesNewlyCombined() && !entities2.isEntitiesNewlyCombined()){
                entities1.setStatus(false);
                entities2.setStatus(false);
                entities1.setEntitiesNewlyDeleted(true);
                entities2.setEntitiesNewlyDeleted(true);
                entities1.setNum(1);
                entities2.setNum(1);
                for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
                    if (entities.getValue() == availableValueCombination[combinationIndex] && entities.getNum() == 0 && !entities.isStatus()) {
                        entities.setStatus(true);
                        entities.setPosition(
                                playing.getBoard4x4().getTile()[entities2.getTileNum() / 4][entities2.getTileNum()
                                        % 4]);
                        entities.setTileNum(entities2.getTileNum());
                        entities.setNum(1);
                        entities.setEntitiesNewlyCombined(true);
                        playing.getScore().addScore(availableValueCombination[combinationIndex]);
                        return 0;
                    }
                }
            }
        } else {
            return 0;
        }
        return 0;
    }
    public boolean isAnyCombinationAvailable() {
        if(playing.getEntitiesManager().countEntitiesAlived() == 16){
            for(int i = 0;i<playing.getBoard4x4().getTile().length;i++){
                for(int j = 0;j<playing.getBoard4x4().getTile()[i].length;j++){
                    for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                        if(entities.isStatus() && entities.getTileNum() == playing.getBoard4x4().getTile()[i][j].getTileNum()){
                            for(Entities entities1:playing.getEntitiesManager().getEntitiesList()){
                                if(i+1<4 && j+1<4){
                                    if(entities1.getTileNum() == playing.getBoard4x4().getTile()[i+1][j].getTileNum() ||
                                            entities1.getTileNum() == playing.getBoard4x4().getTile()[i][j+1].getTileNum()){
                                        if(entities.getValue() == entities1.getValue()){
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
