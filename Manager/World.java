package Manager;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

import Player.MouseListeners;
import gameStates.*;
import gameStates.Menu;

public class World extends JPanel implements Runnable{
    private int width = 640;
    private int height = 740;
    private double FPS_SET = 60.0;
    private MouseListeners mouseListeners;
    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    private double UPS_SET = 60.0;
    private Menu menu;
    private Playing playing;
    private WinState winState;
    private LoseState loseState;
    private Image[] images = new Image[2];
    private Toolkit t = Toolkit.getDefaultToolkit();
    Thread gameThread = new Thread(this);
    public void initImage(){
        images[0] = t.getImage(getClass().getResource("/resources/menu.png"));
        images[1] = t.getImage(getClass().getResource("/resources/field.png"));
    }
    public void initInput(){
        mouseListeners = new MouseListeners(this);
        addMouseListener(mouseListeners);
        addMouseMotionListener(mouseListeners);
        setFocusable(true);
        requestFocus();
    }
    public void initState(){
        menu = Menu.createInstance();
        playing = Playing.createInstance();
        winState = WinState.createInstance(this);
        loseState = LoseState.createInstance();
    }
    public void updates(){
        playing.update();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        render(g);
    }
    public void render(Graphics g){
        switch (gameScenes.GameScenes){
            case MENU:
                menu.render(g,images[0]);
                break;
            case PLAYING:
                playing.render(g,images[1]);
                break;
            case WIN:
                winState.render(g);
                break;
            case LOSE:
                loseState.render(g);
                break;
        }
    }
    public World(){
        setPreferredSize(new Dimension(width,height));
        initImage();
        initState();
        initInput();
        startThread();
    }
    public void startThread(){
        gameThread.start();
    }
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastUpdate = System.nanoTime();
        int updates = 0;
        int frames = 0;
        long lastTimeCheck = System.currentTimeMillis();
        long now;
        int FPScount = 0;
        int UPScount = 0;
        while (gameThread != null){
            now = System.nanoTime();
            if (now - lastUpdate >= timePerFrame) {
                lastFrame = now;
                frames++;
                repaint();
            }
            //update game
            if (now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
                updates();
            }
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }
}
