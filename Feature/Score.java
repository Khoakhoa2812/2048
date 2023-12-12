package Feature;

import gameStates.Playing;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Integer> scoreStack = new ArrayList<>();
    public void addScore(int add){
        score += add;
    }

    public void setCurrentScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public List<Integer> getScoreStack() {
        return scoreStack;
    }

    public void render(Graphics g){
        ScoreBox = t.getImage(getClass().getResource("/resources/ScoreBox2.0.png"));
        g.drawImage(ScoreBox,400,10,200,80,null);
        scoreDisplay(g);
    }
    public void scoreDisplay(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Serif",Font.BOLD,40));
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.format("%d",score),setAlignment(),75);
    }
    public int setAlignment(){
        if(score < 10){
            return 550;
        } else if(score>9 && score<100){
            return 525;
        } else if(score>99 && score<1000){
            return 500;
        } else if(score>999 && score<10000){
            return 475;
        } else if(score>9999 && score<100000){
            return 450;
        }
        return 0;
    }
}
