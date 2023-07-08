package Calculation;

import gameStates.Playing;

public class TempoValue {
    private Playing playing;
    public TempoValue(Playing playing){
        this.playing = playing;
    }
    private static TempoValue instance;
    public static TempoValue createInstance(Playing playing){
        if(instance == null){
            instance = new TempoValue(playing);
            return instance;
        }
        return null;
    }
    private String currDirection;

    public String getCurrDirection() {
        return currDirection;
    }

    public void assignTempDirection(String tempDirection){
        currDirection = tempDirection;
    }
}
