package gameStates;

import MenuButton.QuitButton;
import MenuButton.StartGameButton;
import gameStates.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Menu implements sceneMethods{
    private StartGameButton startGameButton;
    private QuitButton quitButton;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private static Menu instance;
    public static Menu createInstance(){
        if(instance == null){
            instance = new Menu();
            return instance;
        } else {
            System.out.println("There is a Menu existed");
            return null;
        }
    }
    public Menu(){
        initComponent();
    }
    public void initComponent(){
        startGameButton = StartGameButton.createInstance();
        quitButton = QuitButton.createInstance();
    }
    public void MouseClicked(int x, int y){
        if(startGameButton.getBound().contains(x,y)){
            gameScenes.setGameScenes(gameScenes.PLAYING);
        } else if(quitButton.getBound().contains(x,y)){
            System.exit(0);
        }
    }
    @Override
    public void render(Graphics g, Image img) {
        g.drawImage(img,0,0,640,740,null);
        startGameButton.render(g);
        quitButton.render(g);
    }
}
