package Feature;

import entities.Entities;
import gameStates.Playing;

import java.awt.*;

public class Reset {
    private Playing playing;
    public Reset(Playing playing){
        this.playing = playing;
        resetBound = new Rectangle(180,30,50,50);
    }
    private static Reset instance;
    public static Reset createInstance(Playing playing){
        if(instance == null){
            instance = new Reset(playing);
            return instance;
        }
        return null;
    }
    private Image resetButtonImage;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Rectangle resetBound;
    public void render(Graphics g){
        resetButtonImage = t.getImage(getClass().getResource("/resources/reset.png"));
        g.drawImage(resetButtonImage,180,30,50,50,null);
    }
    public void resetMatch(int x, int y){
        if(resetBound.contains(x,y)){
            for(Entities entities: playing.getEntitiesManager().getEntitiesList()){
                entities.setStatus(false);
                entities.setNum(0);
                entities.setEntitiesNewlyCreated(false);
                entities.setEntitiesNewlyCombined(false);
                entities.setEntitiesNewlyDeleted(false);
                entities.setMoveCompleted(false);
            }
            playing.getScore().setCurrentScore(0);
        }
    }
}
