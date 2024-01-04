package gameStates;

import java.awt.*;

public class LoseState {
    private static LoseState instance;
    public LoseState(){}
    public static LoseState createInstance(){
        if(instance == null){
            instance = new LoseState();
            return instance;
        }
        return null;
    }
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image Lose_word = t.getImage(getClass().getResource("/resources/youlose_word.png"));
    private Image Lose_background =  t.getImage(getClass().getResource("/resources/youlose_background.png"));
    public void render(Graphics g){
        g.drawImage(Lose_background,0,100,640,640,null);
        g.drawImage(Lose_word,-80,30,800,800,null);
    }
}
