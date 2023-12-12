package Win_Lose;

import gameStates.Playing;

import java.awt.*;

public class Lose {
    private Playing playing;
    public Lose(Playing playing){
        this.playing = playing;
    }
    private static Lose instance;
    public static Lose createInstance(Playing playing){
        if(instance == null){
            instance = new Lose(playing);
            return instance;
        }
        return null;
    }
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image Lose_word = t.getImage(getClass().getResource("/resources/youlose_word.png"));
    private Image Lose_background =  t.getImage(getClass().getResource("/resources/youlose_background.png"));
    public void render(Graphics g){
        if(isLose){
            g.drawImage(Lose_background,0,100,640,640,null);
            g.drawImage(Lose_word,-80,30,800,800,null);
        }
    }
    private boolean isLose = false;

    public void gameLose(){
        if(playing.getTileCombination().checkCombination()){
            isLose = true;
        }
    }

    public void setLose(boolean lose) {
        isLose = lose;
    }

    public void update(){
        gameLose();
    }
}
