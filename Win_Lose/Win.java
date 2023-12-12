package Win_Lose;

import entities.Entities;
import entities.Tile2048;
import gameStates.Playing;

import java.awt.*;

public class Win {
    private Playing playing;
    public Win(Playing playing){
        this.playing = playing;
//        testWin();
    }
    private static Win instance;
    public static Win createInstance(Playing playing){
        if(instance == null){
            instance = new Win(playing);
            return instance;
        }
        return null;
    }
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image win_word = t.getImage(getClass().getResource("/resources/youwin_word.png"));
    private Image win_background = t.getImage(getClass().getResource("/resources/youlose_background.png"));
    private boolean win = false;
    public void WinMatch(){
        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
            if(entities.getValue() == 2048 && entities.isStatus()){
                win = true;
            }
        }
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void render(Graphics g){
        if(win){
            g.drawImage(win_background,0,100,640,640,null);
            g.drawImage(win_word,-80,30,800,800,null);
        }
    }
    public void update(){
        WinMatch();
    }
    public void testWin(){
        for(Entities entities:playing.getEntitiesManager().getEntitiesList()){
            if(entities.getValue() == 2048){
                entities.setStatus(true);
            }
        }
    }
}
