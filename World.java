import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;

public class World extends JPanel implements Runnable{
    private int width = 640;
    private int height = 640;
    private double FPS_SET = 60.0;
    private double UPS_SET = 60.0;
    private Image[] images = new Image[2];
    private Toolkit t = Toolkit.getDefaultToolkit();
    Thread gameThread = new Thread(this);
    public void initImage(){
        images[0] = t.getImage(getClass().getResource("/resource/menu"));
        images[1] = t.getImage(getClass().getResource("/resource/field"));
    }
    public void updates(){

    }
    public World(){
        setPreferredSize(new Dimension(width,height));
        initImage();
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
