package gameStates;

import java.awt.*;

public class Menu implements sceneMethods{
    private Image menu;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public Menu(){
        menu = t.getImage(getClass().getResource("/resource/menu.png"));
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(menu,0,0,640,640,null);
    }
}
