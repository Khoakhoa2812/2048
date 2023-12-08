package entities;

import Board.Tile;

public class Tile8 extends Entities{
    public Tile8(Tile tile,int No) {
        super(tile,No);
        setValue(8);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/8.png")));
    }
}
