package Board;

import entities.Entities;

import java.awt.*;

public class Tile {
    private int tileNum;
    private final int width = 157;
    private final int height = 130;
    private Rectangle Bound = new Rectangle();

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
        if(Bound.contains(entity.getBound().getX()+50,entity.getBound().getY()+50)){
            return true;
        }
        return false;
    }
}
