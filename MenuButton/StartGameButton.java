package MenuButton;

import gameStates.gameScenes.*;

import java.awt.*;

public class StartGameButton extends MenuButton {
    private static StartGameButton instance;
    public static StartGameButton createInstance(){
        if(instance == null){
            instance = new StartGameButton();
            return instance;
        } else {
            return null;
        }
    }
    public void setBound(){
        setBound(new Rectangle(100,600,124,60));
    }
    public StartGameButton(){
        setButtonImage(getT().getImage(getClass().getResource("/resources/start button.png")));
        setBound();
    }

    @Override
    public void render(Graphics g) {
       g.drawImage(getButtonImage(),100,600,124,60,null);
    }

}
