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
    private int[] availableValueCombination = {4,8,16,32,64,128,256,512,1024};
    public tileCombination(Playing playing){
        this.playing = playing;
    }
    public void combination(Entities entity1, Entities entity2){
        for(int i = 0;i<availableValueCombination.length;i++){
            if(playing.getCalculation().doCalculation(entity1,entity2) == availableValueCombination[i] && entity1.isStatus() && entity2.isStatus() && !entity1.isEntitiesNewlyCombined() && !entity2.isEntitiesNewlyCombined()){
                entity1.setStatus(false);
                entity2.setStatus(false);
                entity1.setNum(1);
                entity2.setNum(1);
                switch (i){
                    case 0:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 4 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 1:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 8 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 2:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 16 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 3:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 32 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 4:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 64 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 5:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 128 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 6:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 256 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 7:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 512 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
                                break;
                            }
                        }
                        break;
                    case 8:
                        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
                            if(entities.getValue() == 1024 && entities.getNum() == 0 && !entities.isStatus()){
                                entities.setStatus(true);
                                entities.setPosition(playing.getBoard4x4().getTile()[entity2.getTileNum()]);
                                entities.setTileNum(entity2.getTileNum());
                                entities.setNum(1);
                                entities.setEntitiesNewlyCombined(true);
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
