package Feature;

import gameStates.Playing;

import java.awt.*;

public class Score {
    private Playing playing;
    public Score(Playing playing){
        this.playing = playing;
    }
    private static Score instance;
    public static Score createInstance(Playing playing){
        if(instance == null){
            instance = new Score(playing);
            return instance;
        }
        return null;
    }
    private Image ScoreBox;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private int score = 0;
    public void addScore(int add){
        score += add;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void render(Graphics g){
        ScoreBox = t.getImage(getClass().getResource("/resources/ScoreBox.png"));
        g.drawImage(ScoreBox,400,10,200,80,null);
        scoreDisplay(g);
    }
    public void scoreDisplay(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Arial",Font.BOLD,42));
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("%d",score),setAlignment(),80);
    }
    public int setAlignment(){
        if(score < 10){
            return 550;
        } else if(score >9 && score<100){
            return 525;
        } else if(score>99 && score<1000){
            return 500;
        } else if(score>999 && score<10000){
            return 475;
        }
        return 0;
    }
}
