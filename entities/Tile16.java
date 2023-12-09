package entities;

import Board.Tile;

public class Tile16 extends Entities{
    public Tile16(Tile tile, int No) {
        super(tile,No);
        setValue(16);
        initImage();
    }
    public void initImage(){
        setImageEntity(getT().getImage(getClass().getResource("/resources/16.png")));
    }
}
