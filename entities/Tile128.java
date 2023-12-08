package entities;

import Board.Tile;

public class Tile128 extends Entities{
    public Tile128(Tile tile,int No) {
        super(tile, No);
        setValue(128);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/128.png")));
    }
}
