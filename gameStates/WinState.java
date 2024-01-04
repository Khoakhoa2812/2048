package gameStates;

import Manager.World;

import java.awt.*;

public class WinState {
    private World w;
    private static WinState instance;
    public WinState(World w){
        this.w = w;
    }
    public static WinState createInstance(World w){
        if(instance == null){
            instance = new WinState(w);
            return instance;
        }
        return null;
    }
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image win_word = t.getImage(getClass().getResource("/resources/youwin_word.png"));
    private Image win_background = t.getImage(getClass().getResource("/resources/youlose_background.png"));
    public void render(Graphics g){
        g.drawImage(win_background,0,100,640,640,null);
        g.drawImage(win_word,-80,30,800,800,null);
    }
}
