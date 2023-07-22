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
    private Image Lose_word;
    private Image Lose_background;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public void render(Graphics g){
        Lose_background = t.getImage(getClass().getResource("/resources/youlose_background.png"));
        Lose_word = t.getImage(getClass().getResource("/resources/youlose_word.png"));
        if(isLose){
            g.drawImage(Lose_word,120,150,400,400,null);
            g.drawImage(Lose_background,0,100,640,540,null);
        }
    }
    private boolean isLose = false;

    public void gameLose(){
        if(playing.getEntitiesManager().checkStream()){
            isLose = true;
        }
    }
    public void update(){
        gameLose();
    }
}
