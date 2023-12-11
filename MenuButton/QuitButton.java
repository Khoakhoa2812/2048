package MenuButton;

import java.awt.*;

public class QuitButton extends MenuButton{
    private static QuitButton instance;
    public static QuitButton createInstance(){
        if(instance == null){
            instance = new QuitButton();
            return instance;
        } else {
            return null;
        }
    }
    public void setBound(){
        setBound(new Rectangle(400,600,124,60));
    }
    public QuitButton(){
        setButtonImage(getT().getImage(getClass().getResource("/resources/quit.png")));
        setBound();
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(getButtonImage(),400,600,124,60,null);
    }
}
