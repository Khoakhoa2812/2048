package Board;

import entities.Entities;

import java.awt.*;

public class Tile {
    private int tileNum;
    private final int width = 158;
    private final int height = 158;
    private Rectangle Bound = new Rectangle();
    private int valueHold;

    public int getValueHold() {
        return valueHold;
    }

    public void setValueHold(int valueHold) {
        this.valueHold = valueHold;
    }

    public int getTileNum() {
        return tileNum;
    }

    public Rectangle getBound() {
        return Bound;
    }

    public Tile(int tileNum){
        this.tileNum = tileNum;
    }
    public void setPosition(double x, double y){
        Bound.setBounds((int)x,(int)y,width,height);
    }
    public void render(Graphics g){
        g.fillRect((int)Bound.getX(),(int)Bound.getY(),(int)Bound.getWidth(),(int)Bound.getHeight());
    }
    public boolean isOccupied(Entities entity){
        if(Bound.getX() == entity.getBound().getX() && Bound.getY() == entity.getBound().getY()){
            return true;
        }
        return false;
    }
}
