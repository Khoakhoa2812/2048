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
//        for (int i = 0; i < availableValueCombination.length; i++) {
//            if (playing.getCalculation().doCalculation(entity1, entity2) == availableValueCombination[i]
//                    && entity1.isStatus() && entity2.isStatus() && !entity1.isEntitiesNewlyCombined()
//                    && !entity2.isEntitiesNewlyCombined()) {
//                entity1.setStatus(false);
//                entity2.setStatus(false);
//                entity1.setEntitiesNewlyDeleted(true);
//                entity2.setEntitiesNewlyDeleted(true);
//                entity1.setNum(1);
//                entity2.setNum(1);
//                switch (i) {
//                    case 0:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 4 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(4);
//                                break;
//                            }
//                        }
//                        break;
//                    case 1:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 8 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(8);
//                                break;
//                            }
//                        }
//                        break;
//                    case 2:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 16 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(16);
//                                break;
//                            }
//                        }
//                        break;
//                    case 3:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 32 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(32);
//                                break;
//                            }
//                        }
//                        break;
//                    case 4:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 64 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(64);
//                                break;
//                            }
//                        }
//                        break;
//                    case 5:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 128 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(128);
//                                break;
//                            }
//                        }
//                        break;
//                    case 6:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 256 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(256);
//                                break;
//                            }
//                        }
//                        break;
//                    case 7:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 512 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(512);
//                                break;
//                            }
//                        }
//                        break;
//                    case 8:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 1024 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(1024);
//                                break;
//                            }
//                        }
//                        break;
//                    case 9:
//                        for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
//                            if (entities.getValue() == 2048 && entities.getNum() == 0 && !entities.isStatus()) {
//                                entities.setStatus(true);
//                                entities.setPosition(
//                                        playing.getBoard4x4().getTile()[entity2.getTileNum() / 4][entity2.getTileNum()
//                                                % 4]);
//                                entities.setTileNum(entity2.getTileNum());
//                                entities.setNum(1);
//                                entities.setEntitiesNewlyCombined(true);
//                                playing.getScore().addScore(1024);
//                                break;
//                            }
//                        }
//                        break;
//                }
//                break;
//            }
//        }
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
                System.out.println(availableValueCombination[combinationIndex]);
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
    public boolean checkCombination() {
        if (playing.getEntitiesManager().checkStream()) {
            for (int i = 0; i < playing.getBoard4x4().getTile().length; i++) {
                for (Entities entities : playing.getEntitiesManager().getEntitiesList()) {
                    if (entities.isStatus()) {
                        if (i == 0) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus()
                                            && (entities1.getTileNum() == i + 1 || entities1.getTileNum() == i + 4)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i > 0 && i < 3) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus() && (entities1.getTileNum() == i + 1
                                            || entities1.getTileNum() == i + 4 || entities1.getTileNum() == i - 1)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i == 3) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus()
                                            && (entities1.getTileNum() == i - 1 || entities1.getTileNum() == i + 4)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i == 4 || i == 8) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus() && (entities1.getTileNum() == i + 4
                                            || entities1.getTileNum() == i - 4 || entities1.getTileNum() == i + 1)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if ((i > 4 && i < 7) || (i > 8 && i < 11)) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus() && (entities1.getTileNum() == i + 1
                                            || entities1.getTileNum() == i + 4 || entities1.getTileNum() == i - 1
                                            || entities1.getTileNum() == i - 4)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i == 7 || i == 11) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus() && (entities1.getTileNum() == i + 4
                                            || entities1.getTileNum() == i - 4 || entities1.getTileNum() == i - 1)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i == 12) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus()
                                            && (entities1.getTileNum() == i + 1 || entities1.getTileNum() == i - 4)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i == 15) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus()
                                            && (entities1.getTileNum() == i - 1 || entities1.getTileNum() == i - 4)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        } else if (i > 12 && i < 15) {
                            if (entities.getTileNum() == i) {
                                for (Entities entities1 : playing.getEntitiesManager().getEntitiesList()) {
                                    if (entities1.isStatus() && (entities1.getTileNum() == i + 1
                                            || entities1.getTileNum() == i - 4 || entities1.getTileNum() == i - 1)) {
                                        if (entities.getValue() == entities1.getValue()) {
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
