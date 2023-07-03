package entities;

import Board.Tile;

import java.awt.*;

public class Entities {
    private double x,y;
    private final int width = 157;
    private final int height = 130;
    private Rectangle Bound;

    public Image getImageEntity() {
        return imageEntity;
    }

    public void setImageEntity(Image imageEntity) {
        this.imageEntity = imageEntity;
    }

    private int value;

    public Toolkit getT() {
        return t;
    }

    private Image imageEntity;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public int getValue() {
        return value;
    }
    public void setPosition(Tile tile){
        Bound = new Rectangle((int)tile.getBound().getX(),(int)tile.getBound().getY(),width,height);
    }
    public void updatePosition(Tile tile){
        Bound.setBounds((int)tile.getBound().getX(),(int)tile.getBound().getY(),width,height);
    }
    public void setValue(int value) {
        this.value = value;
    }

    public Rectangle getBound() {
        return Bound;
    }
    public Entities(Tile tile){
        setPosition(tile);
    }
    public void EntityRender(Graphics g){
        g.drawImage(imageEntity,(int)Bound.getX(),(int)Bound.getY(),(int)Bound.getWidth(),(int)Bound.getHeight(),null);
    }
}
