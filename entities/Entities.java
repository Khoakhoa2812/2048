package entities;

import Board.Tile;

import java.awt.*;

public class Entities {
    private int Num = 0;
    private boolean status = true;

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    private double x,y;
    private final int width = 157;
    private final int height = 130;
    private int oldTileNum;
    private int newTileNum;
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

    public int getOldTileNum() {
        return oldTileNum;
    }

    public void setOldTileNum(int oldTileNum) {
        this.oldTileNum = oldTileNum;
    }

    public int getNewTileNum() {
        return newTileNum;
    }

    public void setNewTileNum(int newTileNum) {
        this.newTileNum = newTileNum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Entities(Tile tile){
        setPosition(tile);
        this.oldTileNum = tile.getTileNum();
        this.newTileNum = tile.getTileNum();
    }
    public void EntityRender(Graphics g){
        if(status){
            g.drawImage(imageEntity,(int)Bound.getX(),(int)Bound.getY(),(int)Bound.getWidth(),(int)Bound.getHeight(),null);
        }
    }
    public void update(){
        if(newTileNum != oldTileNum){
            oldTileNum = newTileNum;
        }
    }
}
