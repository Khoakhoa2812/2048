package entities;

import Board.Tile;

import java.awt.*;

public class Entities {
    private int Num = 0;
    private int No;
    private boolean status = true;

    public boolean isMoveCompleted() {
        return isMoveCompleted;
    }

    public void setMoveCompleted(boolean moveCompleted) {
        isMoveCompleted = moveCompleted;
    }
    private boolean isEntitiesNewlyCreated = false;
    private boolean isEntitiesNewlyCombined = false;
    private boolean isEntitiesNewlyDeleted = false;

    public boolean isEntitiesNewlyDeleted() {
        return isEntitiesNewlyDeleted;
    }

    public void setEntitiesNewlyDeleted(boolean entitiesNewlyDeleted) {
        isEntitiesNewlyDeleted = entitiesNewlyDeleted;
    }

    public boolean isEntitiesNewlyCombined() {
        return isEntitiesNewlyCombined;
    }

    public void setEntitiesNewlyCombined(boolean entitiesNewlyCombined) {
        isEntitiesNewlyCombined = entitiesNewlyCombined;
    }

    public boolean isEntitiesNewlyCreated() {
        return isEntitiesNewlyCreated;
    }

    public void setEntitiesNewlyCreated(boolean entitiesNewlyCreated) {
        isEntitiesNewlyCreated = entitiesNewlyCreated;
    }

    private boolean isMoveCompleted= false;
    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    private double x,y;
    private final int width = 158;
    private final int height = 158;
    private int TileNum;
    private int newTileNum;
    private Tile tile;
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
    public void move(String Direction, Tile tile){
        if(Bound.getX() != tile.getBound().getX() || Bound.getY() != tile.getBound().getY()){
            switch (Direction){
                case "UP":
                    Bound.setBounds((int)Bound.getX(),(int)Bound.getY()-80,width,height);
                    if(Bound.getX() == tile.getBound().getX() && Bound.getY() == tile.getBound().getY()){
                        TileNum = TileNum-4;
                    }
                    break;
                case "DOWN":
                    Bound.setBounds((int)Bound.getX(),(int)Bound.getY()+80,width,height);
                    if(Bound.getX() == tile.getBound().getX() && Bound.getY() == tile.getBound().getY()){
                        TileNum = TileNum+4;
                    }
                    break;
                case "LEFT":
                    Bound.setBounds((int)Bound.getX()-80,(int)Bound.getY(),width,height);
                    if(Bound.getX() == tile.getBound().getX() && Bound.getY() == tile.getBound().getY()){
                        TileNum = TileNum-1;
                    }
                    break;
                case "RIGHT":
                    Bound.setBounds((int)Bound.getX()+80,(int)Bound.getY(),width,height);
                    if(Bound.getX() == tile.getBound().getX() && Bound.getY() == tile.getBound().getY()){
                        TileNum = TileNum+1;
                    }
                    break;
            }
        }
    }
    public void setValue(int value) {
        this.value = value;
    }

    public int getNo() {
        return No;
    }

    public Rectangle getBound() {
        return Bound;
    }

    public int getTileNum() {
        return TileNum;
    }

    public void setTileNum(int tileNum) {
        this.TileNum = tileNum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Tile getTile() {
        return tile;
    }

    public Entities(Tile tile, int No){
        this.tile = tile;
        setPosition(tile);
        this.TileNum = tile.getTileNum();
        this.No = No;
    }
    public void EntityRender(Graphics g){
        if(status){
            g.drawImage(imageEntity,(int)Bound.getX(),(int)Bound.getY(),(int)Bound.getWidth(),(int)Bound.getHeight(),null);
        }
    }
    public void update(){
    }
}
